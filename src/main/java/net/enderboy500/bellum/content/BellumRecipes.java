package net.enderboy500.bellum.content;

import net.enderboy500.bellum.recipe.KunaiTippingRecipe;
import net.enderboy500.enderlib.helper.RegistryHelper;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class BellumRecipes {
    public static final RecipeSerializer<KunaiTippingRecipe> KUNAI_TIPPING = (RecipeSerializer<KunaiTippingRecipe>) RegistryHelper.registerRecipeSerializer("kunai_tipping", new CustomRecipe.Serializer<>(KunaiTippingRecipe::new));

    public static void loadRecipes() {}
}
