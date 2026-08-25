package net.enderboy500.bellum.projectile;

import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.content.BellumEntities;
import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.content.BellumParticleTypes;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
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

public class ThrownAnchor extends AbstractArrow {
    private static final EntityDataAccessor<Boolean> ID_FOIL;
    private static final EntityDataAccessor<ItemStack> ANCHOR;
    private static final EntityDataAccessor<Integer> RETURN_SLOT;
    private static final EntityDataAccessor<Boolean> REELING;
    private static final EntityDataAccessor<Boolean> REAPING;
    private static final EntityDataAccessor<Boolean> SHOCKWAVE;
    private static final EntityDataAccessor<Identifier> CHAIN;
    private boolean dealtDamage = false;
    public int clientSideReturnTridentTickCount;
    public int returnTimer = 0;

    public ThrownAnchor(EntityType<? extends ThrownAnchor> entityType, Level level) {
        super(entityType, level);
    }

    protected ThrownAnchor(Level level, LivingEntity livingEntity, ItemStack itemStack) {
        super(BellumEntities.ANCHOR_ENTITY, livingEntity, level, itemStack, (ItemStack) null);
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
        entityData.set(ANCHOR, itemStack);
        setReeling(ItemUtils.hasEnchantment(itemStack, "reeling"));
        setReaping(ItemUtils.hasEnchantment(itemStack, "reaping"));
        setShockwave(ItemUtils.hasEnchantment(itemStack, "shockwave"));
        if (itemStack.has(BellumDataComponents.ANCHOR_CHAIN)) setChain(itemStack.get(BellumDataComponents.ANCHOR_CHAIN));
        else setChain(Bellum.DEFAULT_ANCHOR_CHAIN);
    }

    protected ThrownAnchor(Level level, double d, double e, double f, ItemStack itemStack) {
        super(BellumEntities.ANCHOR_ENTITY, d, e, f, level, itemStack, itemStack);
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
        this.entityData.set(ANCHOR, itemStack);
        setReeling(ItemUtils.hasEnchantment(itemStack, "reeling"));
        setReaping(ItemUtils.hasEnchantment(itemStack, "reaping"));
        setShockwave(ItemUtils.hasEnchantment(itemStack, "shockwave"));
        if (itemStack.has(BellumDataComponents.ANCHOR_CHAIN)) setChain(itemStack.get(BellumDataComponents.ANCHOR_CHAIN));
        else setChain(Bellum.DEFAULT_ANCHOR_CHAIN);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_FOIL, false);
        builder.define(ANCHOR, new ItemStack(BellumItems.ANCHOR));
        builder.define(RETURN_SLOT, 0);
        builder.define(REELING, false);
        builder.define(REAPING, false);
        builder.define(SHOCKWAVE, false);
        builder.define(CHAIN, Bellum.DEFAULT_ANCHOR_CHAIN);
    }

    public boolean hasReeling() {
        return entityData.get(REELING);
    }

    public void setReeling(boolean b) {
        entityData.set(REELING, b);
    }

    public boolean hasReaping() {
        return entityData.get(REAPING);
    }

    public void setReaping(boolean b) {
        entityData.set(REAPING, b);
    }

    public boolean hasShockwave() {
        return entityData.get(SHOCKWAVE);
    }

    public void setShockwave(boolean b) {
        entityData.set(SHOCKWAVE, b);
    }

    public Identifier getChain() {
        return entityData.get(CHAIN);
    }

    public void setChain(Identifier identifier) {
        entityData.set(CHAIN, identifier);
    }

    public void tick() {
        int i = 3;
        Entity entity = this.getOwner();
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        if (returnTimer <= 12) returnTimer++;
        else if (!isInGround()){
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

        if (this.isInGround() && !this.dealtDamage && hasShockwave()) {
            float radius = 5f;
            // impact
            if (entity.level().isClientSide()) {
                Minecraft.getInstance().level.addParticle(BellumParticleTypes.SHOCKWAVE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
            for (LivingEntity hitLivingEntity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(radius))) {
                    this.hurtMarked = true;
                    Vec3 distance = hitLivingEntity.getOnPos().offset(0, (int) (hitLivingEntity.getBbHeight() / 2f), 0).getCenter().subtract(this.getOnPos().getCenter());
                    Vec3 footDistance = hitLivingEntity.getOnPos().getBottomCenter().subtract(this.getOnPos().getCenter());
                    if (footDistance.y > distance.y) {
                        distance = footDistance;
                    }
                    float proximity = (float) Mth.lerp(Mth.clamp(distance.length() / radius, 0, 1), 1, 0);
                    Vec3 direction = distance.normalize().scale(proximity * 2);
                    hitLivingEntity.addDeltaMovement(new Vec3(direction.x, direction.y, direction.z));
                    hitLivingEntity.fallDistance = 0;
            }
            this.dealtDamage = true;
        }

        if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
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
                if (hasReeling() && isInGround()) {
                    double d = 2;
                    if (entity == null) {
                        this.dealtDamage = true;
                        return;
                    }
                    float e = (float) (d / 5f);
                    Vec3 vec3d = this.getOnPos().getCenter().subtract(entity.getEyePosition());
                    entity.setDeltaMovement(entity.getDeltaMovement().scale(0.95).add(vec3d.normalize().scale(e)));
                    entity.fallDistance = 0;
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
        float f = entity.isInWaterOrRain() ? 9 + 5 : 9;
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
                if (hasReaping()) {
                    Vec3 dir = entityHitResult.getLocation().subtract(entity2.getOnPos().getCenter()).normalize().scale(2);
                    dir = entity2.getOnPos().getCenter().subtract(entityHitResult.getLocation()).scale(2 / 10f);
                    entity.setDeltaMovement(dir.x, dir.y, dir.z);
                    entity.hurtMarked = true;
                }
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

    @Override
    protected boolean tryPickup(Player player) {
        boolean var10000;
        switch (this.pickup.ordinal()) {
            case 0 -> var10000 = false;
            case 1 -> var10000 = addItem(player);
            case 2 -> var10000 = player.hasInfiniteMaterials();
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        return var10000;
    }

    protected boolean addItem(Player player) {
        if (player.getInventory().getSlot(entityData.get(RETURN_SLOT).intValue()).get().isEmpty()) {
            player.getInventory().setItem(entityData.get(RETURN_SLOT).intValue(), this.getPickupItem());
            return true;
        } else if (player.addItem(this.getPickupItem())){
            return true;
        } else {
            player.drop(this.getPickupItem(), true);
            return true;
        }
    }

    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(BellumItems.HELLFORK);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    public int getReturnSlot() {
        return this.entityData.get(RETURN_SLOT).intValue();
    }

    public void setReturnSlot(int i) {
        this.entityData.set(RETURN_SLOT, i);
    }

    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }

    }

    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        this.dealtDamage = valueInput.getBooleanOr("DealtDamage", false);
    }

    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putBoolean("DealtDamage", this.dealtDamage);
    }



    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public boolean shouldRender(double d, double e, double f) {
        return true;
    }

    public static EntityDataAccessor<ItemStack> getAnchor() {
        return ANCHOR;
    }

    static {
        ID_FOIL = SynchedEntityData.defineId(ThrownAnchor.class, EntityDataSerializers.BOOLEAN);
        ANCHOR = SynchedEntityData.defineId(ThrownAnchor.class, EntityDataSerializers.ITEM_STACK);
        RETURN_SLOT = SynchedEntityData.defineId(ThrownAnchor.class, EntityDataSerializers.INT);
        REELING = SynchedEntityData.defineId(ThrownAnchor.class, EntityDataSerializers.BOOLEAN);
        REAPING = SynchedEntityData.defineId(ThrownAnchor.class, EntityDataSerializers.BOOLEAN);
        SHOCKWAVE = SynchedEntityData.defineId(ThrownAnchor.class, EntityDataSerializers.BOOLEAN);
        CHAIN = SynchedEntityData.defineId(ThrownAnchor.class, BellumEntities.IDENTIFIER);
    }
}
