package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SoundsInit
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Skyrimcraft.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> SWAMP_AMBIENT = SOUNDS.register("swamp_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Skyrimcraft.MODID, "swamp_ambient")));
    public static final DeferredHolder<SoundEvent, SoundEvent> UNRELENTING_FORCE_CAST = SOUNDS.register("unrelenting_force_cast", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Skyrimcraft.MODID, "unrelenting_force_cast")));

//
//            this.add(
//                new ResourceLocation(Skyrimcraft.MODID, "swamp_ambient"),
//    definition().subtitle("sound." + Skyrimcraft.MODID + ".swamp_ambient").with(
//        sound(new ResourceLocation(Skyrimcraft.MODID, "swamp_ambient"))
//        .weight(4) // has 4/5 = 80% change of playing
//                                .volume(0.75)
//                                .stream()
//                )
//                        );
//        this.add(
//                new ResourceLocation(Skyrimcraft.MODID, "skyrim_caves"),
//    definition().subtitle("sound." + Skyrimcraft.MODID + ".skyrim_caves").with(
//        sound(new ResourceLocation(Skyrimcraft.MODID, "cave_ambience_1"))
//        .weight(2) // has 4/5 = 80% change of playing
//                                .volume(0.75)
//                                .stream(),
//    sound(new ResourceLocation(Skyrimcraft.MODID, "cave_ambience_2"))
//        .weight(2) // has 4/5 = 80% change of playing
//                                .volume(0.75)
//                                .stream(),
//    sound(new ResourceLocation(Skyrimcraft.MODID, "cave_ambience_3"))
//        .weight(2) // has 4/5 = 80% change of playing
//                                .volume(0.75)
//                                .stream(),
//    sound(new ResourceLocation(Skyrimcraft.MODID, "cave_ambience_4"))
//        .weight(2) // has 4/5 = 80% change of playing
//                                .volume(0.75)
//                                .stream(),
//    sound(new ResourceLocation(Skyrimcraft.MODID, "cave_ambience_5"))
//        .weight(2) // has 4/5 = 80% change of playing
//                                .volume(0.75)
//                                .stream()
//                )
//                        );
//        this.add(
//                new ResourceLocation(Skyrimcraft.MODID, "fire_spell_cast"),
//    definition().subtitle("sound." + Skyrimcraft.MODID + ".fire_spell_cast").with(
//        sound(new ResourceLocation(Skyrimcraft.MODID, "fire_spell_cast"))
//        )
//        );
//        this.add(
//                new ResourceLocation(Skyrimcraft.MODID, "unrelenting_force_cast"),
//    definition().subtitle("sound." + Skyrimcraft.MODID + ".unrelenting_force_cast").with(
//        sound(new ResourceLocation(Skyrimcraft.MODID, "unrelenting_force_cast"))
//        )
//        );
//        this.add(
//                new ResourceLocation(Skyrimcraft.MODID, "freeze_spell_hit"),
//    definition().subtitle("sound." + Skyrimcraft.MODID + ".freeze_spell_hit").with(
//        sound(new ResourceLocation(Skyrimcraft.MODID, "freeze_spell_hit"))
//        )
//        );
//        this.add(
//                new ResourceLocation(Skyrimcraft.MODID, "draugr_grunt"),
//    definition().subtitle("sound." + Skyrimcraft.MODID + ".draugr_grunt").with(
//        sound(new ResourceLocation(Skyrimcraft.MODID, "draugr_grunt"))
//        )
//        );
//        this.add(
//                new ResourceLocation(Skyrimcraft.MODID, "draugr_attack"),
//    definition().subtitle("sound." + Skyrimcraft.MODID + ".draugr_attack").with(
//        sound(new ResourceLocation(Skyrimcraft.MODID, "draugr_attack"))
//        )
//        );
}