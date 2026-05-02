package net.enderboy500.bellum.content;

import net.enderboy500.bellum.projectile.ThrownKunaiEntity;
import net.enderboy500.enderlib.helper.RegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class BellumEntities {
/*    public static final EntityType<TrainingDummyEntity> TRAINING_DUMMY = RegistryHelper.registerEntity("training_dummy", EntityType.Builder.of(
            TrainingDummyEntity::new, MobCategory.MISC).sized(1, 2));*/
    public static final EntityType<ThrownKunaiEntity> THROWN_KUNAI = RegistryHelper.registerEntity("thrown_kunai", EntityType.Builder.of(ThrownKunaiEntity::new, MobCategory.MISC).sized(0.5f, 0.5f));

    public static void loadEntities() {}
}
