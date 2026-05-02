package net.enderboy500.bellum.projectile;

import net.enderboy500.bellum.content.BellumEntities;
import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
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

public class ThrownKunaiEntity extends AbstractArrow implements InventoryCarrier {
    private final SimpleContainer INVENTORY = new SimpleContainer(1);
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ThrownKunaiEntity.class, EntityDataSerializers.BOOLEAN);
    private boolean dealtDamage = false;

    public ThrownKunaiEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    ThrownKunaiEntity(Level level, LivingEntity livingEntity, ItemStack itemStack) {
        super(BellumEntities.THROWN_KUNAI, livingEntity, level, itemStack, null);
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
    }

    ThrownKunaiEntity(Level level, double d, double e, double f, ItemStack itemStack) {
        super(BellumEntities.THROWN_KUNAI, d, e, f, level, itemStack, itemStack);
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_FOIL, false);
    }

    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!(entity instanceof Player) && this.position().distanceTo(entity.getEyePosition()) < (double)entity.getBbWidth() + (double)1.0F) {
                this.discard();
                return;
            }
        }

        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        ItemStack itemStack = getInventory().getItem(0).copy();
        assert this.getInventory().getItem(0).get(BellumDataComponents.KUNAI_ATTACK_DAMAGE) != null;
        float f = this.getInventory().getItem(0).has(BellumDataComponents.KUNAI_ATTACK_DAMAGE) ? this.getInventory().getItem(0).get(BellumDataComponents.KUNAI_ATTACK_DAMAGE) : 3;
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.damageSources().trident(this, (entity2 == null ? this : entity2));
        Level var7 = this.level();
        if (var7 instanceof ServerLevel serverLevel) {
            f = EnchantmentHelper.modifyDamage(serverLevel, this.getInventory().getItem(0), entity, damageSource, f);
        }

        if (itemStack.has(BellumDataComponents.KUNAI_EFFECT) && entity instanceof LivingEntity livingEntity) {
            for (MobEffectInstance effectInstance : itemStack.get(BellumDataComponents.KUNAI_EFFECT).effects()) {
                livingEntity.addEffect(effectInstance);
            }
        }

        this.dealtDamage = true;
        if (entity.hurtOrSimulate(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

/*            var7 = this.level();
            if (var7 instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)var7;
                EnchantmentHelper.doPostAttackEffectsWithItemSourceOnBreak(serverLevel, entity, damageSource, this.getWeaponItem(), (item) -> this.kill(serverLevel));
            }*/

            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)entity;
                this.doKnockback(livingEntity, damageSource);
                this.doPostHurtEffects(livingEntity);
            }
        }

        this.deflect(ProjectileDeflection.REVERSE, entity, this.owner, false);
        this.setDeltaMovement(this.getDeltaMovement().multiply(0.02, 0.2, 0.02));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
        if (itemStack.has(BellumDataComponents.KUNAI_EFFECT)) itemStack.remove(BellumDataComponents.KUNAI_EFFECT);
        if (itemStack.getCustomName() == null) itemStack.set(DataComponents.ITEM_NAME, Component.translatable("item.bellum.kunai"));
        level().addFreshEntity(new ItemEntity(level(), blockPosition().getX(), blockPosition().getY(), blockPosition().getZ(), itemStack));
        discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        ItemStack itemStack = getInventory().getItem(0).copy();
        if (itemStack.has(BellumDataComponents.KUNAI_EFFECT)) itemStack.remove(BellumDataComponents.KUNAI_EFFECT);
        if (itemStack.getCustomName() == null) itemStack.set(DataComponents.ITEM_NAME, BellumItems.KUNAI.getName());
        level().addFreshEntity(new ItemEntity(level(), blockPosition().getX(), blockPosition().getY(), blockPosition().getZ(), itemStack));
        discard();
    }

    @Override
    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getInventory().getItem(0));
    }

    @Override
    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }

    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        this.dealtDamage = valueInput.getBooleanOr("DealtDamage", false);
        this.readInventoryFromTag(valueInput);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putBoolean("DealtDamage", this.dealtDamage);
        this.writeInventoryToTag(valueOutput);
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) super.tickDespawn();
    }

    @Override
    public boolean shouldRender(double d, double e, double f) {
        return true;
    }

    @Override
    public @Nullable ItemStack getWeaponItem() {
        return BellumItems.KUNAI.getDefaultInstance();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    @Override
    public SimpleContainer getInventory() {
        return this.INVENTORY;
    }
}
