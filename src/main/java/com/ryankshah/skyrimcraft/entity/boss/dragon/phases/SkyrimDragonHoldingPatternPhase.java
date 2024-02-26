package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SkyrimDragonHoldingPatternPhase extends AbstractSkyrimDragonPhaseInstance {
    private static final TargetingConditions NEW_TARGET_TARGETING = TargetingConditions.forCombat().ignoreLineOfSight();
    @Nullable
    private Path currentPath;
    @Nullable
    private Vec3 targetLocation;
    private boolean clockwise;

    public SkyrimDragonHoldingPatternPhase(SkyrimDragon pDragon) {
        super(pDragon);
    }

    @Override
    public SkyrimDragonPhase<SkyrimDragonHoldingPatternPhase> getPhase() {
        return SkyrimDragonPhase.HOLDING_PATTERN;
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    @Override
    public void doServerTick() {
        double d0 = this.targetLocation == null ? 0.0 : this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
        if (d0 < 100.0 || d0 > 22500.0 || this.dragon.horizontalCollision || this.dragon.verticalCollision) {
            this.findNewTarget();
        }
    }

    /**
     * Called when this phase is set to active
     */
    @Override
    public void begin() {
        this.currentPath = null;
        this.targetLocation = null;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    @Override
    public Vec3 getFlyTargetLocation() {
        return this.targetLocation;
    }

    private void findNewTarget() {
        if (this.currentPath != null && this.currentPath.isDone()) {
            BlockPos blockpos = this.dragon
                    .level()
                    .getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos(EndPodiumFeature.getLocation(this.dragon.getFightOrigin())));
            int i = 0;
            if (this.dragon.getRandom().nextInt(i + 3) == 0) {
                this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.LANDING_APPROACH);
                return;
            }

            Player player = this.dragon
                    .level()
                    .getNearestPlayer(NEW_TARGET_TARGETING, this.dragon, (double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
            double d0;
            if (player != null) {
                d0 = blockpos.distToCenterSqr(player.position()) / 512.0;
            } else {
                d0 = 64.0;
            }

            if (player != null && (this.dragon.getRandom().nextInt((int)(d0 + 2.0)) == 0 || this.dragon.getRandom().nextInt(i + 2) == 0)) {
                this.strafePlayer(player);
                return;
            }
        }

        if (this.currentPath == null || this.currentPath.isDone()) {
            int j = 2; // findClosestNode
            int k = j;
            if (this.dragon.getRandom().nextInt(8) == 0) {
                this.clockwise = !this.clockwise;
                k = j + 6;
            }

            if (this.clockwise) {
                ++k;
            } else {
                --k;
            }

//            if (this.dragon.getDragonFight() != null && this.dragon.getDragonFight().getCrystalsAlive() >= 0) {
//                k %= 12;
//                if (k < 0) {
//                    k += 12;
//                }
//            } else {
            k -= 12;
            k &= 7;
            k += 12;
//            }

            this.currentPath = this.dragon.findPath(j, k, null);
            if (this.currentPath != null) {
                this.currentPath.advance();
            }
        }

        this.navigateToNextPathNode();
    }

    private void strafePlayer(Player pPlayer) {
        this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.STRAFE_PLAYER);
        this.dragon.getPhaseManager().getPhase(SkyrimDragonPhase.STRAFE_PLAYER).setTarget(pPlayer);
    }

    private void navigateToNextPathNode() {
        if (this.currentPath != null && !this.currentPath.isDone()) {
            Vec3i vec3i = this.currentPath.getNextNodePos();
            this.currentPath.advance();
            double d0 = (double)vec3i.getX();
            double d1 = (double)vec3i.getZ();

            double d2;
            do {
                d2 = (double)((float)vec3i.getY() + this.dragon.getRandom().nextFloat() * 20.0F);
            } while(d2 < (double)vec3i.getY());

            this.targetLocation = new Vec3(d0, d2, d1);
        }
    }
}