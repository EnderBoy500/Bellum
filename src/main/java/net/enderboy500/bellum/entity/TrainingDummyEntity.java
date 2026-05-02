package net.enderboy500.bellum.entity;

import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.content.BellumEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class TrainingDummyEntity extends LivingEntity {
    public float rotation;
    public float stored_damage;

    public TrainingDummyEntity(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();
        getStoredDamage();
        if (!this.level().isClientSide()) {
            this.setRotation(this.getRotation());
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putFloat("Rotation", rotation);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        setRotation(valueInput.getFloatOr("Rotation", 180));
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    private boolean swapItem(Player player, EquipmentSlot equipmentSlot, ItemStack itemStack, InteractionHand interactionHand) {
        ItemStack itemStack2 = this.getItemBySlot(equipmentSlot);
        if (player.hasInfiniteMaterials() && itemStack2.isEmpty() && !itemStack.isEmpty()) {
            this.setItemSlot(equipmentSlot, itemStack.copyWithCount(1));
            return true;
        } else if (itemStack.isEmpty() || itemStack.getCount() <= 1) {
            this.setItemSlot(equipmentSlot, itemStack);
            player.setItemInHand(interactionHand, itemStack2);
            return true;
        } else if (!itemStack2.isEmpty()) {
            return false;
        } else {
            this.setItemSlot(equipmentSlot, itemStack.split(1));
            return true;
        }
    }


    @Override
    public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand interactionHand) {
/*        ItemStack itemStack = player.getItemInHand(interactionHand);
        EquipmentSlot equipmentSlot = this.getEquipmentSlotForItem(itemStack);
        if (itemStack.isEmpty()) {
            if (equipmentSlot == EquipmentSlot.HEAD && this.hasItemInSlot(equipmentSlot)) {
                this.swapItem(player, equipmentSlot, itemStack, interactionHand);
            }
            if (equipmentSlot == EquipmentSlot.CHEST && this.hasItemInSlot(equipmentSlot)) {
                this.swapItem(player, equipmentSlot, itemStack, interactionHand);
            }
            return InteractionResult.SUCCESS;
        } else {
            this.swapItem(player, equipmentSlot, itemStack, interactionHand);
            return InteractionResult.SUCCESS;
        }*/
        return InteractionResult.PASS;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createLivingAttributes().add(Attributes.STEP_HEIGHT, 0.0).add(Attributes.KNOCKBACK_RESISTANCE, 10);
    }

    public void getStoredDamage() {
        if (lastHurt > 0) stored_damage = lastHurt;
    }

    @Override
    public boolean isDeadOrDying() {
        return false;
    }

    @Override
    public boolean isAutoSpinAttack() {
        return false;
    }

    @Override
    protected boolean isImmobile() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    public boolean isEffectiveAi() {
        return false;
    }

    @Override
    public boolean attackable() {
        return false;
    }

    @Override
    protected void actuallyHurt(ServerLevel serverLevel, DamageSource damageSource, float f) {
        super.actuallyHurt(serverLevel, damageSource, f);
        this.lastHurt = f;
        Bellum.LOGGER.info(String.valueOf(getDamageAfterArmorAbsorb(damageSource, lastHurt)));
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f) {
        return super.hurtServer(serverLevel, damageSource, f);
    }

    @Override
    public boolean hurtClient(DamageSource damageSource) {
        return false;
    }


    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }
}
