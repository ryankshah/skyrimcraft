package com.ryankshah.skyrimcraft.init;

import com.google.common.collect.ImmutableSet;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VillagerInit
{
    public static final DeferredRegister<PoiType> POIS = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, Skyrimcraft.MODID);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(Registries.VILLAGER_PROFESSION, Skyrimcraft.MODID);

    // TODO: add professions for warrior, cook (oven), alchemist (alchemy table) and skyrim-blacksmith (forge)

    public static final Supplier<PoiType> WARRIOR_POI = POIS.register("warrior_poi", () -> new PoiType(ImmutableSet.copyOf(BlockInit.WEAPON_RACK.get().getStateDefinition().getPossibleStates()), 1, 8));
    public static final Supplier<VillagerProfession> WARRIOR = PROFESSIONS.register("warrior", () -> new VillagerProfession("warrior", p -> p.value().equals(WARRIOR_POI.get()), p -> p.value().equals(WARRIOR_POI.get()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_WEAPONSMITH));
}