package net.enderboy500.bellum.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FireChargeItem.class)
public abstract class FireChargeItemMixin extends Item {
    public FireChargeItemMixin(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);
        if (!world.isClientSide()) {
            world.levelEvent(null, 1018, user.blockPosition(), 0);
            Vec3 vec3d = user.getViewVector(1.0F).normalize().scale(2);
            SmallFireball smallFireballEntity = new SmallFireball(world, user.getX(), user.getY(), user.getZ(), vec3d);
            smallFireballEntity.setPos(smallFireballEntity.getX(), user.getEyeY(), smallFireballEntity.getZ());
            world.addFreshEntity(smallFireballEntity);
            stack.consume(1, user);
            user.getCooldowns().addCooldown(this.getDefaultInstance(), 40);
        }
        return InteractionResult.SUCCESS;
    }
}
