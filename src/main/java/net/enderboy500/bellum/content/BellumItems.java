package net.enderboy500.bellum.content;

import io.github.ciph3rj.cipherlib.helper.RegistryHelper;
import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import net.enderboy500.bellum.projectile.KunaiItem;
import net.enderboy500.bellum.item.ScytheItem;
import net.enderboy500.bellum.item.SickleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public class BellumItems {
    public static final Item WOODEN_SICKLE = register("wooden_sickle", properties -> new SickleItem(ToolMaterial.WOOD, 2.5f, -2.6f, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item STONE_SICKLE = register("stone_sickle", properties -> new SickleItem(ToolMaterial.STONE, 2.5f, -2.6f, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item COPPER_SICKLE = register("copper_sickle", properties -> new SickleItem(ToolMaterial.COPPER, 2.5f, -2.6f, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item IRON_SICKLE = register("iron_sickle", properties -> new SickleItem(ToolMaterial.IRON, 2.5f, -2.6f, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item GOLDEN_SICKLE = register("golden_sickle", properties -> new SickleItem(ToolMaterial.GOLD, 2.5f, -2.6f, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item DIAMOND_SICKLE = register("diamond_sickle", properties -> new SickleItem(ToolMaterial.DIAMOND, 2.5f, -2.6f, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item NETHERITE_SICKLE = register("netherite_sickle", properties -> new SickleItem(ToolMaterial.NETHERITE, 2.5f, -2.6f, properties),
            new Item.Properties().stacksTo(1).fireResistant().component(CipherLibComponents.HAS_SWEEP_ATTACK, true));

    public static final Item WOODEN_SCYTHE = register("wooden_scythe", properties -> new ScytheItem(ToolMaterial.WOOD, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item STONE_SCYTHE = register("stone_scythe", properties -> new ScytheItem(ToolMaterial.STONE, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item COPPER_SCYTHE = register("copper_scythe", properties -> new ScytheItem(ToolMaterial.COPPER, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item IRON_SCYTHE = register("iron_scythe", properties -> new ScytheItem(ToolMaterial.IRON, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item GOLDEN_SCYTHE = register("golden_scythe", properties -> new ScytheItem(ToolMaterial.GOLD, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item DIAMOND_SCYTHE = register("diamond_scythe", properties -> new ScytheItem(ToolMaterial.DIAMOND, properties),
            new Item.Properties().stacksTo(1).component(CipherLibComponents.HAS_SWEEP_ATTACK, true));
    public static final Item NETHERITE_SCYTHE = register("netherite_scythe", properties -> new ScytheItem(ToolMaterial.NETHERITE, properties),
            new Item.Properties().stacksTo(1).fireResistant().component(CipherLibComponents.HAS_SWEEP_ATTACK, true));

    public static final Item WOODEN_DAGGER = register("wooden_dagger", Item::new, new Item.Properties().sword(ToolMaterial.WOOD, 2.25f, -2));
    public static final Item COPPER_DAGGER = register("copper_dagger", Item::new, new Item.Properties().sword(ToolMaterial.COPPER, 2, -1.8f));
    public static final Item STONE_DAGGER = register("stone_dagger", Item::new, new Item.Properties().sword(ToolMaterial.STONE, 2, -2));
    public static final Item GOLDEN_DAGGER = register("golden_dagger", Item::new, new Item.Properties().sword(ToolMaterial.GOLD, 2.25f, -1.8f));
    public static final Item IRON_DAGGER = register("iron_dagger", Item::new, new Item.Properties().sword(ToolMaterial.IRON, 2.75f, -2));
    public static final Item DIAMOND_DAGGER = register("diamond_dagger", Item::new, new Item.Properties().sword(ToolMaterial.DIAMOND, 1.5f, -2));
    public static final Item NETHERITE_DAGGER = register("netherite_dagger", Item::new, new Item.Properties().sword(ToolMaterial.NETHERITE, 1.25f, -2));

    public static final Item WOODEN_KUNAI = KunaiItem.registerKunai("wooden_kunai", 3.5f);
    public static final Item COPPER_KUNAI = KunaiItem.registerKunai("copper_kunai", 4f);
    public static final Item STONE_KUNAI = KunaiItem.registerKunai("stone_kunai", 4f);
    public static final Item GOLDEN_KUNAI = KunaiItem.registerKunai("golden_kunai", 4.25f);
    public static final Item IRON_KUNAI = KunaiItem.registerKunai("iron_kunai", 4.5f);
    public static final Item DIAMOND_KUNAI = KunaiItem.registerKunai("diamond_kunai", 4.75f);
    public static final Item NETHERITE_KUNAI = KunaiItem.registerKunai("netherite_kunai", 5f);

//    public static final Item TRAINING_DUMMY = register("training_dummy", TrainingDummyItem::new, new Item.Properties());

    public static Item register(String id, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        return RegistryHelper.registerItem(id, itemFactory, settings);
    }

    public static void loadItems() {}
}
