package com.ryankshah.skyrimcraft.character.magic.entity;

import com.ryankshah.skyrimcraft.goal.ConjuredFollowOwnerGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class LightballEntity extends PathfinderMob implements OwnableEntity
{
    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(
            LightballEntity.class, EntityDataSerializers.OPTIONAL_UUID
    );

    private LivingEntity owner;

    public LightballEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);


        this.owner = pLevel.getPlayerByUUID(this.getEntityData().get(DATA_OWNERUUID_ID).get());

        this.moveTo(owner.getX(), owner.getY(), owner.getZ(), owner.getYRot(), owner.getXRot());
        this.setPos(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(Vec3.ZERO);

        this.getNavigation().setCanFloat(true);
        this.setInvulnerable(true);
    }

    public LightballEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel, LivingEntity owner) {
        this(pEntityType, pLevel);
        this.owner = owner;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(5, new ConjuredFollowOwnerGoal(this, owner, 2.0D, 10.0f, 2.0f, true));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.1D).add(Attributes.FOLLOW_RANGE, 12.0D);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_OWNERUUID_ID, Optional.empty());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.getOwnerUUID() != null) {
            pCompound.putUUID("Owner", this.getOwnerUUID());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        UUID uuid;
        if (pCompound.hasUUID("Owner")) {
            uuid = pCompound.getUUID("Owner");
        } else {
            String s = pCompound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }

        if (uuid != null) {
            try {
                this.setOwnerUUID(uuid);
            } catch (Throwable ignored) {
            }
        }
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID pUuid) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(pUuid));
    }
}
