package com.ryankshah.skyrimcraft.event;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.network.spell.CastSpell;
import com.ryankshah.skyrimcraft.screen.SkyrimMenuScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InputEvents
{
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {

        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            Minecraft mc = Minecraft.getInstance();

            while (KeyEvents.MENU_KEY.get().consumeClick()) {
                mc.setScreen(new SkyrimMenuScreen());
                return;
            }
            while (KeyEvents.SPELL_SLOT_1_KEY.get().consumeClick()) { // TODO: Check if `isDown` for continuous cast?
                Character character = Character.get(mc.player);
                Spell spell = character.getSelectedSpell1();
                if (spell != null) {
                    final CastSpell castSpell = new CastSpell(SpellRegistry.SPELLS_REGISTRY.getResourceKey(spell).get());
                    PacketDistributor.SERVER.noArg().send(castSpell);
                } else
                    mc.player.displayClientMessage(Component.translatable("skyrimcraft.spell.noselect"), false);
                return;
            }
            while (KeyEvents.SPELL_SLOT_2_KEY.get().consumeClick()) {
                Character character = Character.get(mc.player);
                Spell spell = character.getSelectedSpell2();
                if (spell != null) {
                    final CastSpell castSpell = new CastSpell(SpellRegistry.SPELLS_REGISTRY.getResourceKey(spell).get());
                    PacketDistributor.SERVER.noArg().send(castSpell);
                } else
                    mc.player.displayClientMessage(Component.translatable("skyrimcraft.spell.noselect"), false);
                return;
            }
            while (KeyEvents.PICKPOCKET_KEY.get().consumeClick()) {
//                if (mc.crosshairPickEntity instanceof LivingEntity && mc.player.isCrouching()) {
//                    LivingEntity entity = (LivingEntity) mc.crosshairPickEntity;
//
//                    if(entity.getTags().contains(ModEntityType.PICKPOCKET_TAG)) {
//                        Networking.sendToServer(new PacketHandlePickpocketOnServer(entity));
//                    }
//                }
            }
        }
    }
}