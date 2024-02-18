package com.ryankshah.skyrimcraft.event;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
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
            while (KeyEvents.SPELL_SLOT_1_KEY.get().consumeClick()) {
                ISpell spell = mc.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell1();
                if(spell != null) {
                    final CastSpell castSpell = new CastSpell(spell.getID());
                    PacketDistributor.SERVER.noArg().send(castSpell);
                } else
                    mc.player.displayClientMessage(Component.translatable("skyrimcraft.spell.noselect"), false);
                return;
            }
            while (KeyEvents.SPELL_SLOT_2_KEY.get().consumeClick()) {
                ISpell spell = mc.player.getData(PlayerAttachments.KNOWN_SPELLS).getSelectedSpell2();
                if(spell != null) {
                    final CastSpell castSpell = new CastSpell(spell.getID());
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