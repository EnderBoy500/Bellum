package net.enderboy500.bellum.content;

import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.projectile.ThrownHellfork;
import net.enderboy500.bellum.projectile.ThrownKunaiEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class BellumEntities {
    public static final EntityType<ThrownKunaiEntity> THROWN_KUNAI = registerEntity("thrown_kunai", EntityType.Builder.of(ThrownKunaiEntity::new, MobCategory.MISC).sized(0.5f, 0.5f));
    public static final EntityType<ThrownHellfork> HELLFORK_ENTITY = registerEntity("hellfork", EntityType.Builder.of(ThrownHellfork::new, MobCategory.MISC).noLootTable().sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20));

    static <T extends Entity> EntityType<T> registerEntity(String path, EntityType.Builder<T> type) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, Bellum.id(path));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }


    public static void loadEntities() {}
}
