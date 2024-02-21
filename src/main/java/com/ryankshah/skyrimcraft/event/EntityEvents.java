package com.ryankshah.skyrimcraft.event;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.goal.UndeadFleeGoal;
import com.ryankshah.skyrimcraft.item.SkyrimArmor;
import com.ryankshah.skyrimcraft.network.character.AddToTargetingEntities;
import com.ryankshah.skyrimcraft.network.character.UpdateCurrentTarget;
import com.ryankshah.skyrimcraft.network.skill.AddXpToSkill;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents
{
//    private static List<EntityType<?>> pickPocketableEntities = StreamSupport.stream(PickpocketLootTables.getPickpocketableEntities().spliterator(), false).collect(Collectors.toList());

    @SubscribeEvent
    public static void entitySetAttackTarget(LivingAttackEvent event) {
        if(event.getSource().getDirectEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getSource().getDirectEntity();
            Character character = Character.get(player);
            List<Integer> targetingEntities = character.getTargets();
            if (!targetingEntities.contains(event.getEntity().getId()) //&& cap.getTargetingEntities().size() <= 10
                    && event.getEntity().isAlive()) {
                targetingEntities.add(event.getEntity().getId());
                final AddToTargetingEntities targets = new AddToTargetingEntities(event.getEntity().getId());
                character.addTarget(event.getEntity().getId());
                PacketDistributor.PLAYER.with(player).send(targets);
            }
        }
    }

    /**
     * Used to add panic AI task for undead mobs (for UndeadFleeEffect to activate)
     */
    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event) {
//        if(pickPocketableEntities.contains(event.getEntity().getType())) {
//            event.getEntity().addTag(ModEntityType.PICKPOCKET_TAG);
//        }

        if(event.getEntity() instanceof Monster) {
            Monster monsterEntity = (Monster) event.getEntity();
            if(monsterEntity.getMobType() == MobType.UNDEAD) {
                monsterEntity.goalSelector.addGoal(0, new UndeadFleeGoal(monsterEntity, 16.0F, 0.8D, 1.33D));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityHit(LivingHurtEvent event) {
        if(event.getSource().getEntity() instanceof Player) {
            Player playerEntity = (Player) event.getSource().getEntity();

            if (event.getEntity() != null) {
                if (playerEntity.hasEffect(ModEffects.ETHEREAL.get()))
                    playerEntity.removeEffect(ModEffects.ETHEREAL.get());

                if (playerEntity.getMainHandItem().getItem() instanceof ProjectileWeaponItem) {
                    final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.ARCHERY.getID(), (int)event.getAmount());
                    PacketDistributor.SERVER.noArg().send(xpToSkill);
                } else if(playerEntity.getMainHandItem().getItem() instanceof SwordItem) {
                    final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.ONE_HANDED.getID(), (int)event.getAmount());
                    PacketDistributor.SERVER.noArg().send(xpToSkill);
                }

//                } else if(playerEntity.getMainHandItem().getItem() instanceof SkyrimTwoHandedWeapon) {
//                    Networking.sendToServer(new PacketAddXpToSkillOnServer(SkillRegistry.TWO_HANDED.getID(), (int)event.getAmount()));
//                }

                if (event.getEntity().isAlive()) {
                    final UpdateCurrentTarget target = new UpdateCurrentTarget(event.getEntity().getId());
                    PacketDistributor.SERVER.noArg().send(target);
                } else {
                    final UpdateCurrentTarget target = new UpdateCurrentTarget(-1);
                    PacketDistributor.SERVER.noArg().send(target);
                }
            }
        } else if(event.getEntity() instanceof Player) {
            Player playerEntity = (Player) event.getEntity();
            if(playerEntity.isDamageSourceBlocked(event.getSource())) {
                final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.BLOCK.getID(), SkillRegistry.BASE_BLOCK_XP);
                PacketDistributor.SERVER.noArg().send(xpToSkill);
            }

            if(playerEntity.getArmorValue() > 0) {
                // minecraft armors will default to light armor for now.
                // TODO: Check all the slots and check the type of armor (perhaps leather, iron and gold will be
                //       classed as "light armor" with netherite as the heavy armor for default mc.
                //       All other mod armors outside of skyrim will be classed as light armor. Perhaps instead,
                //       there may be a different way we can define these...
                AtomicInteger heavySlots = new AtomicInteger();
                for (Iterator<ItemStack> it = playerEntity.getArmorSlots().iterator(); it.hasNext(); ) {
                    ItemStack itemStack = it.next();
                    if(itemStack.getItem() instanceof ArmorItem) {
                        ArmorItem item = (ArmorItem) itemStack.getItem();
                        if ((item instanceof SkyrimArmor && ((SkyrimArmor) item).isHeavy()) || item.getMaterial() == ArmorMaterials.NETHERITE)
                            heavySlots.set(heavySlots.get() + 1);
                    }
                }

                if(heavySlots.get() >= 3) {
                    final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.HEAVY_ARMOR.getID(), (int) (playerEntity.getArmorValue() * playerEntity.getArmorCoverPercentage()));
                    PacketDistributor.SERVER.noArg().send(xpToSkill);
                } else {
                    final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.LIGHT_ARMOR.getID(), (int) (playerEntity.getArmorValue() * playerEntity.getArmorCoverPercentage()));
                    PacketDistributor.SERVER.noArg().send(xpToSkill);
                }
            }
        }
    }

}