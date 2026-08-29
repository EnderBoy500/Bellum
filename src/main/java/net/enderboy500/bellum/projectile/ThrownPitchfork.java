package net.enderboy500.bellum.projectile;

import net.enderboy500.bellum.content.BellumEntities;
import net.enderboy500.bellum.content.BellumItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class ThrownPitchfork extends AbstractArrow {
    private static final EntityDataAccessor<Byte> ID_LOYALTY;
    private static final EntityDataAccessor<Boolean> ID_FOIL;
    private boolean dealtDamage = false;
    public int clientSideReturnTridentTickCount;

    public ThrownPitchfork(EntityType<? extends ThrownPitchfork> entityType, Level level) {
        super(entityType, level);
    }

    protected ThrownPitchfork(Level level, LivingEntity livingEntity, ItemStack itemStack) {
        super(BellumEntities.PITCHFORK_ENTITY, livingEntity, level, itemStack, (ItemStack) null);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(itemStack));
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
    }

    protected ThrownPitchfork(Level level, double d, double e, double f, ItemStack itemStack) {
        super(BellumEntities.PITCHFORK_ENTITY, d, e, f, level, itemStack, itemStack);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(itemStack));
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_LOYALTY, (byte) 0);
        builder.define(ID_FOIL, false);
    }

    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        
        int i = this.entityData.get(ID_LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                Level var4 = this.level();
                if (var4 instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel) var4;
                    if (this.pickup == Pickup.ALLOWED) {
                        this.spawnAtLocation(serverLevel, this.getPickupItem(), 0.1F);
                    }
                }

                this.discard();
            } else {
                if (!(entity instanceof Player) && this.position().distanceTo(entity.getEyePosition()) < (double) entity.getBbWidth() + (double) 1.0F) {
                    this.discard();
                    return;
                }

                this.setNoPhysics(true);
                Vec3 vec3 = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015 * (double) i, this.getZ());
                double d = 0.05 * (double) i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(d)));
                if (this.clientSideReturnTridentTickCount == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
                }

                ++this.clientSideReturnTridentTickCount;
            }
        }

        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }

    protected @Nullable EntityHitResult findHitEntity(Vec3 vec3, Vec3 vec32) {
        return this.dealtDamage ? null : super.findHitEntity(vec3, vec32);
    }

    protected Collection<EntityHitResult> findHitEntities(Vec3 vec3, Vec3 vec32) {
        EntityHitResult entityHitResult = this.findHitEntity(vec3, vec32);
        return entityHitResult != null ? List.of(entityHitResult) : List.of();
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 3.5F;
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.damageSources().trident(this, (Entity) (entity2 == null ? this : entity2));
        Level var7 = this.level();
        if (var7 instanceof ServerLevel serverLevel) {
            f = EnchantmentHelper.modifyDamage(serverLevel, this.getWeaponItem(), entity, damageSource, f);
        }

        this.dealtDamage = true;
        if (entity.hurtOrSimulate(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            var7 = this.level();
            if (var7 instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel) var7;
                EnchantmentHelper.doPostAttackEffectsWithItemSourceOnBreak(serverLevel, entity, damageSource, this.getWeaponItem(), (item) -> this.kill(serverLevel));
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                this.doKnockback(livingEntity, damageSource);
                this.doPostHurtEffects(livingEntity);
            }
        }

        this.deflect(ProjectileDeflection.REVERSE, entity, this.owner, false);
        this.setDeltaMovement(this.getDeltaMovement().multiply(0.02, 0.2, 0.02));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    protected void hitBlockEnchantmentEffects(ServerLevel serverLevel, BlockHitResult blockHitResult, ItemStack itemStack) {
        Vec3 vec3 = blockHitResult.getBlockPos().clampLocationWithin(blockHitResult.getLocation());
        Entity var6 = this.getOwner();
        LivingEntity var10002;
        if (var6 instanceof LivingEntity livingEntity) {
            var10002 = livingEntity;
        } else {
            var10002 = null;
        }

        EnchantmentHelper.onHitBlock(serverLevel, itemStack, var10002, this, (EquipmentSlot) null, vec3, serverLevel.getBlockState(blockHitResult.getBlockPos()), (item) -> this.kill(serverLevel));
    }

    public ItemStack getWeaponItem() {
        return this.getPickupItemStackOrigin();
    }

    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(BellumItems.PITCHFORK);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }

    }

    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        this.dealtDamage = valueInput.getBooleanOr("DealtDamage", false);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(this.getPickupItemStackOrigin()));
    }

    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putBoolean("DealtDamage", this.dealtDamage);
    }

    private byte getLoyaltyFromItem(ItemStack itemStack) {
        Level var3 = this.level();
        if (var3 instanceof ServerLevel serverLevel) {
            return (byte) Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverLevel, itemStack, this), 0, 127);
        } else {
            return 0;
        }
    }

    public void tickDespawn() {
        int i = this.entityData.get(ID_LOYALTY);
        if (this.pickup != Pickup.ALLOWED || i <= 0) {
            super.tickDespawn();
        }

    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public boolean shouldRender(double d, double e, double f) {
        return true;
    }

    static {
        ID_LOYALTY = SynchedEntityData.defineId(ThrownPitchfork.class, EntityDataSerializers.BYTE);
        ID_FOIL = SynchedEntityData.defineId(ThrownPitchfork.class, EntityDataSerializers.BOOLEAN);
    }
}
