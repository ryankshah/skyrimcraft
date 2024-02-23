package com.ryankshah.skyrimcraft.entity.npc;

import com.ryankshah.skyrimcraft.init.EntityInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;

public class Khajiit extends AgeableMob
{
    private static final EntityDataAccessor<Boolean> SEX = SynchedEntityData.defineId(Khajiit.class, EntityDataSerializers.BOOLEAN);

    public Khajiit(EntityType<? extends AgeableMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.getNavigation().setCanFloat(true);
        this.setCanPickUpLoot(true);

        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0, 60));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 12.0D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SEX, false);
    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        p_213281_1_.putBoolean("Sex", this.getSex());
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setSex(p_70037_1_.getBoolean("Sex"));
    }

    public boolean getSex() {
        return this.entityData.get(SEX);
    }

    public void setSex(boolean isMale) {
        this.entityData.set(SEX, isMale);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_213386_1_, DifficultyInstance p_213386_2_, MobSpawnType p_213386_3_, SpawnGroupData p_213386_4_, CompoundTag p_213386_5_) {
        this.setSex(random.nextBoolean());
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        double d0 = this.random.nextDouble();

        Khajiit khajiit = new Khajiit(EntityInit.KHAJIIT.get(), pLevel);
        khajiit.finalizeSpawn(pLevel, pLevel.getCurrentDifficultyAt(khajiit.blockPosition()), MobSpawnType.BREEDING, null, null);
        return khajiit;
    }
}
