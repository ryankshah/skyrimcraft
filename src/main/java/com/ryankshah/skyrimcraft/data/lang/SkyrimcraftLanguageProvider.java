package com.ryankshah.skyrimcraft.data.lang;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.CreativeTabInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class SkyrimcraftLanguageProvider extends LanguageProvider
{
    public SkyrimcraftLanguageProvider(PackOutput output, String modid, String locale) {
        super(output, modid, locale);

    }
    @Override
    protected void addTranslations() {
        add(CreativeTabInit.SKYRIMCRAFT_INGREDIENT_TAB_TITLE, "Skyrimcraft Ingredients");
        add(CreativeTabInit.SKYRIMCRAFT_BLOCK_TAB_TITLE, "Skyrimcraft Blocks");
        add(CreativeTabInit.SKYRIMCRAFT_FOOD_TAB_TITLE, "Skyrimcraft Food");
        add(CreativeTabInit.SKYRIMCRAFT_COMBAT_TAB_TITLE, "Skyrimcraft Combat");
        add(CreativeTabInit.SKYRIMCRAFT_JEWELLERY_TAB_TITLE, "Skyrimcraft Jewellery");
        add(CreativeTabInit.SKYRIMCRAFT_ALL_TITLE, "Skyrimcraft");
        add(CreativeTabInit.SKYRIMCRAFT_MAGIC_TITLE, "Skyrimcraft Magic");

        // Spellbook
        add("spellbook.tooltip.name", "Grants you use of the %s spell!");
        add("spellbook.tooltip.difficulty", "Spell Difficulty: %s");
        add("spellbook.learn", "You have just learnt %s!");
        add("spellbook.known", "You already know this spell!");

        add("skillbook.tooltip", "Improves %s by one level");
        add("skillbook.fail", "Something has gone wrong with this Skill Book");

        // Shout block
        add("shoutblock.allshoutsknown", "You have no more shouts to learn!");
        add("shoutblock.used", "The power which once resonated within this wall has since departed...");
        add("skyrimcraft.shoutblock.used", "The power which once resonated within this wall has since departed...");

        // Info
        add("spell.noselect", "No spell/shout selected");
        add("skyrimcraft.menu.skills", "Skills");
        add("skyrimcraft.menu.map", "Map");
        add("skyrimcraft.menu.quests", "Quests");
        add("skyrimcraft.menu.items", "Items");
        add("skyrimcraft.menu.magic", "Magic");
        add("skyrimcraft.menu.option.unavailable", "This option is currently unavailable!");
        add("skyrimcraft.menu.option.invalid", "Invalid Option!");
        add("skyrimcraft.menu.option.magic.none", "You have not yet learned any spells/shouts!");

        // Skills
        add("skill.pickpocket.fail", "You fail to pick the %s's pockets!");
        add("skill.pickpocket.success", "You successfully pick the %s's pockets and get some loot!");

        // Mobs
        add("entity.minecraft.villager.skyrimcraft.skyrim_blacksmith", "Blacksmith");
        add("entity.minecraft.villager.skyrimcraft.alchemist", "Alchemist");
        add("entity.minecraft.villager.skyrimcraft.cook", "Food Merchant");
        add("entity.skyrimcraft.sabre_cat", "Sabre Cat");
        add("entity.skyrimcraft.snowy_sabre_cat", "Snowy Sabre Cat");
        add("entity.skyrimcraft.giant", "Giant");
        add("entity.skyrimcraft.mammoth", "Mammoth");
        add("entity.skyrimcraft.blue_butterfly", "Blue Butterfly");
        add("entity.skyrimcraft.monarch_butterfly", "Monarch Butterfly");
        add("entity.skyrimcraft.blue_dartwing", "Blue Dartwing");
        add("entity.skyrimcraft.orange_dartwing", "Orange Dartwing");
        add("entity.skyrimcraft.lunar_moth", "Lunar Moth");
        add("entity.skyrimcraft.torchbug", "Torchbug");
        add("entity.skyrimcraft.dragon", "Dragon");
        add("entity.skyrimcraft.dwarven_spider", "Dwarven Spider");
        add("entity.skyrimcraft.draugr", "Draugr");

        add("entity.skyrimcraft.abecean_longfin", "Abecean Longfin");
        add("entity.skyrimcraft.cyrodilic_spadetail", "Cyrodilic Spadetail");

        add("entity.skyrimcraft.khajiit", "Khajiit");
        add("entity.skyrimcraft.falmer", "Falmer");

        // Damage Source
        add("death.attack.death.skyrimcraft.conjuredfamiliar", "Your conjured %1$s familiar has vanished!");
        add("skyrimcraft.conjuredfamiliar.exists", "You have already conjured a familiar!");

        add("death.attack.death.skyrimcraft.conjuredzombie", "Your conjured zombie has vanished!");
        add("skyrimcraft.conjuredzombie.exists", "You have already conjured a zombie!");

        ItemInit.addItemTranslations(this);
        BlockInit.addBlockTranslations(this);

        add("curios.identifier.circlet", "Circlet");

        // Quests
        add("advancements.skyrimcraft.quest.root.title", "Skyrimcraft");
        add("advancements.skyrimcraft.quest.root.description", "Skyrimcraft Quests");
        add("advancements.skyrimcraft.quest.dragon_slayer.title", "Dragon Slayer");
        add("advancements.skyrimcraft.quest.dragon_slayer.description", "Kill your first Skyrim Dragon");
        add("advancements.skyrimcraft.quest.ebony_dreams.title", "Ebony Dreams");
        add("advancements.skyrimcraft.quest.ebony_dreams.description", "Smelt an Ebony Ingot");
        add("advancements.skyrimcraft.quest.goodbye_webs.title", "Goodbye, Webs");
        add("advancements.skyrimcraft.quest.goodbye_webs.description", "Kill a Spider");

        add("sound." + Skyrimcraft.MODID + ".swamp_ambient", "Skyrim Swamp Ambience");
        add("sound." + Skyrimcraft.MODID + ".skyrim_caves", "Skyrim Caves Ambience");
        add("sound." + Skyrimcraft.MODID + ".fire_spell_cast", "Fire Spell Cast");
        add("sound." + Skyrimcraft.MODID + ".unrelenting_force_cast", "Unrelenting Force");
        add("sound." + Skyrimcraft.MODID + ".freeze_spell_hit", "Freeze Spell Hit");
        add("sound." + Skyrimcraft.MODID + ".draugr_grunt", "Draugr Grunts");
        add("sound." + Skyrimcraft.MODID + ".draugr_attack", "Draugr Attacks");
    }
}