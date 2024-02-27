package com.ryankshah.skyrimcraft.data.loot_table.predicate;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.data.loot_table.condition.MatchSkillLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class SkillPredicate
{
    private static final Map<ResourceLocation, Function<JsonObject, SkillPredicate>> custom_predicates = new java.util.HashMap<>();
    private static final Map<ResourceLocation, java.util.function.Function<JsonObject, SkillPredicate>> unmod_predicates = java.util.Collections.unmodifiableMap(custom_predicates);
    public static final SkillPredicate ANY = new SkillPredicate();

    public static final Codec<SkillPredicate> CODEC = RecordCodecBuilder.create(
            p_298173_ -> p_298173_.group(
                    SkillRegistry.SKILLS_REGISTRY.byNameCodec().fieldOf("skill").forGetter(SkillPredicate::getSkill),
                    Codec.FLOAT.fieldOf("successChance").forGetter(SkillPredicate::getSuccessChance)
            ).apply(p_298173_, SkillPredicate::new)
    );

    private final Skill skill;
    private final float successChance;
    //private final NBTPredicate nbt;
    private Random random = new Random();

    public SkillPredicate() {
        this.skill = null;
        this.successChance = 1.0f;
        //this.nbt = NBTPredicate.ANY;
    }

    public SkillPredicate(Skill skill, float successChance) { //NBTPredicate nbt
        this.skill = skill;
        this.successChance = successChance;
        //this.nbt = nbt;
    }

    public Skill getSkill() {
        return this.skill;
    }

    public float getSuccessChance() {
        return this.successChance;
    }

    public boolean matches(Skill skill, float successChance) {
        if(this == ANY)
            return true;
        else if(this.skill != null && skill.getID() != this.skill.getID())
            return false;
        else if(this.skill != null && skill.getLevel() > this.skill.getLevel())
            return false;
        else {
            float rand = random.nextFloat();
            if (successChance > 1f) {
                successChance = 1f;
            }
            if (rand <= successChance)
                return true;
        }
        return false;
    }

    public static SkillPredicate fromJson(@Nullable JsonElement p_192492_0_) {
        if (p_192492_0_ != null && !p_192492_0_.isJsonNull()) {
            JsonObject jsonobject = GsonHelper.convertToJsonObject(p_192492_0_, "item");
            if (jsonobject.has("type")) {
                final ResourceLocation rl = new ResourceLocation(GsonHelper.getAsString(jsonobject, "type"));
                if (custom_predicates.containsKey(rl)) return custom_predicates.get(rl).apply(jsonobject);
                else throw new JsonSyntaxException("There is no SkillPredicate of type "+rl);
            }

            Skill skill = null;
            if(!jsonobject.has("skill"))
                throw new JsonSyntaxException("There is no skill specified!");
            else {
                JsonObject skillObj = GsonHelper.getAsJsonObject(jsonobject, "skill");
                int id = GsonHelper.getAsInt(skillObj, "id");
                if(SkillRegistry.SKILLS_REGISTRY.stream().noneMatch(s -> s.getID() == id))
                    throw new JsonSyntaxException("There is no skill that exists with id: " + id);
                int level = GsonHelper.getAsInt(skillObj, "level");

//                skill = SkillRegistry.SKILLS_REGISTRY
            }

            float successChance = GsonHelper.getAsFloat(jsonobject, "successChance");

            return new SkillPredicate(skill, successChance);
        } else {
            return ANY;
        }
    }

    public JsonElement serializeToJson() {
        if (this == ANY) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();
            JsonObject skillObject = new JsonObject();
            if (this.skill != null) {
                skillObject.addProperty("id", this.skill.getID());
                skillObject.addProperty("level", this.skill.getLevel());
                jsonobject.add("skill", skillObject);
            }
            jsonobject.addProperty("successChance", this.successChance);

            return jsonobject;
        }
    }

    public static void register(ResourceLocation name, java.util.function.Function<JsonObject, SkillPredicate> deserializer) {
        custom_predicates.put(name, deserializer);
    }

    public static Map<ResourceLocation, java.util.function.Function<JsonObject, SkillPredicate>> getPredicates() {
        return unmod_predicates;
    }

    public static class Builder {
        private Skill skill;
        private float successChance;

        private Builder() {
        }

        public static SkillPredicate.Builder skill() {
            return new SkillPredicate.Builder();
        }

        public SkillPredicate.Builder of(Skill skill, float successChance) {
            this.skill = skill;
            this.successChance = successChance;
            return this;
        }

        public SkillPredicate build() {
            return new SkillPredicate(skill, successChance);
        }
    }
}
