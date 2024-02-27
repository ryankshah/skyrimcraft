package com.ryankshah.skyrimcraft.character.skill;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

import java.util.AbstractMap;

public abstract class Skill //implements Registry<ISkill>
{
    private Player player;
    private int identifier;
    private String name;
    private String description;
    private int level;
    private int totalXp;
    private float xpProgress;

    private float skillUseMultiplier;
    public int skillUseOffset;
    public float skillImproveMultiplier;
    public int skillImproveOffset;

//    public static Codec<Skill> SKILL_CODEC = RecordCodecBuilder.create(skill -> skill.group(
//            Codec.INT.fieldOf("identifier").forGetter(Skill::getIdentifier),
//            Codec.STRING.fieldOf("name").forGetter(Skill::getName),
//            Codec.STRING.fieldOf("description").forGetter(Skill::getDescription),
//            Codec.INT.fieldOf("level").forGetter(Skill::getLevel),
//            Codec.FLOAT.fieldOf("skillUseMultiplier").forGetter(Skill::getSkillUseMultiplier),
//            Codec.INT.fieldOf("skillUseOffset").forGetter(Skill::getSkillUseOffset),
//            Codec.FLOAT.fieldOf("skillImproveMultiplier").forGetter(Skill::getSkillImproveMultiplier),
//            Codec.INT.fieldOf("skillImproveOffset").forGetter(Skill::getSkillImproveOffset)
//    ).apply(skill, Skill::new));

    // Main constructor to use

    public Skill(int id, String name) {
        this.identifier = id;
        this.name = name;
        this.description = getDescription();
        this.level = getDefaultLevel();
        this.totalXp = getTotalXp();
        this.xpProgress = getXpProgress();
        this.skillUseMultiplier = getSkillUseMultiplier();
        this.skillUseOffset = getSkillUseOffset();
        this.skillImproveMultiplier = getSkillImproveMultiplier();
        this.skillImproveOffset = getSkillImproveOffset();
    }

    public abstract AbstractMap.SimpleEntry<Integer, Integer> getIconUV();

    public Skill(int identifier, String name, String description, int baseLevel, float skillUseMultiplier, int skillUseOffset, float skillImproveMultiplier, int skillImproveOffset) {
        this(identifier, name, description, baseLevel, 0, 0, skillUseMultiplier, skillUseOffset, skillImproveMultiplier, skillImproveOffset);
    }

    public Skill(int identifier, String name, String description, int level, int totalXp, float xpProgress, float skillUseMultiplier, int skillUseOffset, float skillImproveMultiplier, int skillImproveOffset) {
        this.identifier = identifier;
        this.name = name;
        this.description = description;
        this.level = level;
        this.totalXp = totalXp;
        this.xpProgress = xpProgress;
        this.skillUseMultiplier = skillUseMultiplier;
        this.skillUseOffset = skillUseOffset;
        this.skillImproveMultiplier = skillImproveMultiplier;
        this.skillImproveOffset = skillImproveOffset;
    }

    // Dummy constructor
    public Skill(Skill skill) {
        this(skill.identifier, skill.name, skill.description, skill.level, skill.totalXp, skill.xpProgress, skill.skillUseMultiplier, skill.skillUseOffset, skill.skillImproveMultiplier, skill.skillImproveOffset);
    }

    public int getDefaultLevel() {
        return 15;
    }

    /**
     * Get the ID of the skill
     *
     * @return skill ID
     */
    public int getID() {
        return identifier;
    }

    /**
     * Get the skill instance
     *
     * @return ISkill instance
     */
    public Skill getSkill() {
        return this;
    }

    /**
     * Get the name of the skill
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the description of the skill
     *
     * @return name
     */
    public abstract String getDescription();

    /**
     * Set the player who the skill instance belongs to
     *
     * @param playerEntity
     */
    public void setPlayer(Player playerEntity) {
        this.player = playerEntity;
    }

    /**
     * Get the player entity who has the skill
     * @return {@link Player}
     */
    public Player getPlayer() {
        return this.player;
    }

    public abstract float getSkillImproveMultiplier();

    public abstract float getSkillUseMultiplier();

    public int getIdentifier() {
        return identifier;
    }

    public abstract int getSkillImproveOffset();

    public abstract int getSkillUseOffset();

    ///
    /// EXPERIENCE-RELATED FIELDS
    ///

    public int getLevel() { return this.level; }

    public void setLevel(int level) { this.level = level; }

    public int getTotalXp() {
        return this.totalXp;
    }

    public float getXpProgress() { return this.xpProgress; }

    private void giveXpLevels(int levels) {
        this.level += levels;
        if (this.level < 0) {
            this.level = 0;
            this.totalXp = 0;
        }
    }

    // xp progress calculation taken from https://en.uesp.net/wiki/Skyrim:Leveling
    public Skill giveExperiencePoints(int baseXp) {
        // full calculation: `Skill Use Mult * (base XP * skill specific multipliers) + Skill Use Offset` -- TODO: add in skill specific multipliers
        // minecraft progress calc : (float)amount / (float)this.getXpNeededForNextLevel();
        float xpToAdd = skillUseMultiplier * (baseXp) + skillUseOffset;
        this.xpProgress += xpToAdd / (float)this.getXpNeededForNextLevel();
        this.totalXp = (int)clamp(this.totalXp + xpToAdd, 0, Integer.MAX_VALUE);

        if(xpProgress < 0.0F) {
            float f = xpProgress * (float)this.getXpNeededForNextLevel();
            if (level > 0) {
                this.giveXpLevels(-1);
                xpProgress = 1.0F + f / (float)this.getXpNeededForNextLevel();
            } else {
                giveXpLevels(-1);
                xpProgress = 0.0F;
            }
        }

        if(xpProgress >= 1.0F) {
            xpProgress = (xpProgress - 1.0F) * (float)this.getXpNeededForNextLevel();
            this.giveXpLevels(1);
            xpProgress /= (float)this.getXpNeededForNextLevel();
        }
        return this;
    }

    // Taken from https://en.uesp.net/wiki/Skyrim:Leveling
    public double getXpNeededForNextLevel() {
        return level == 0 ? 0 : skillImproveMultiplier * Math.pow((level), 1.95) + skillImproveOffset;
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public class Perk
    {
        protected String name;
        protected Skill skillRequired;
        protected int levelRequirement;
        protected Perk[] parents;

        public Perk(String name, Skill skillRequired, int levelRequirement, Perk[] parents) {
            this.name = name;
            this.skillRequired = skillRequired;
            this.levelRequirement = levelRequirement;
            this.parents = parents;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append("id: ").append(identifier).append(", ");
        sb.append("name: ").append(name).append(", ");
        sb.append("description: ").append(description).append(", ");
        sb.append("level: ").append(level).append(", ");
        sb.append("totalXp: ").append(totalXp).append(", ");
        sb.append("xpProgress: ").append(xpProgress).append(", ");
        sb.append("skillUseMultiplier: ").append(skillUseMultiplier).append(", ");
        sb.append("skillUseOffset: ").append(skillUseOffset).append(", ");
        sb.append("skillImproveMultiplier: ").append(skillImproveMultiplier).append(", ");
        sb.append("skillImproveOffset: ").append(skillImproveOffset);
        sb.append("]");
        return sb.toString();
    }
}