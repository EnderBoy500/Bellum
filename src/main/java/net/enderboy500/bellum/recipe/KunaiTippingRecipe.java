package net.enderboy500.bellum.recipe;

import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.content.BellumRecipes;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.enderboy500.bellum.util.BellumTags;
import net.enderboy500.bellum.util.component.KunaiEffectComponent;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class KunaiTippingRecipe extends CustomRecipe {
    private static final Ingredient POTION;

    public KunaiTippingRecipe(CraftingBookCategory category) {
        super(category);
    }

    public boolean matches(CraftingInput craftingInput, Level level) {
        int count = 0;
        int kunaiCount = 0;
        boolean hasModifier = false;

        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack itemStack = craftingInput.getItem(i);
            if (!itemStack.isEmpty()) {
                if (itemStack.is(BellumTags.KUNAIS)) {
                    kunaiCount++;
                } else {
                    if (hasModifier || !POTION.test(itemStack)) return false;
                    hasModifier = true;
                }

                ++count;
            }
        }

        return kunaiCount == 8 && hasModifier && count == 9;
    }

    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        ItemStack outputStack = BellumItems.IRON_KUNAI.getDefaultInstance();

        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack stack = craftingInput.getItem(i);
            if (!stack.isEmpty()) {
                outputStack = stack.getItem().getDefaultInstance().copy();
                outputStack.setCount(8);
                i = craftingInput.size();
            }
        }

        for (int i = 0; i < craftingInput.size(); ++i) {
            ItemStack stack = craftingInput.getItem(i);
            if (!stack.isEmpty()) {
                if (POTION.test(stack)) {
                    List<MobEffectInstance> effectInstances = new ArrayList<>();
                    for (MobEffectInstance mobEffectInstance : stack.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
                        effectInstances.add(new MobEffectInstance(mobEffectInstance.getEffect(), mobEffectInstance.getDuration() / 8, mobEffectInstance.getAmplifier()));
                    }
                    outputStack.set(BellumDataComponents.KUNAI_EFFECT, new KunaiEffectComponent(effectInstances));
                    outputStack.set(DataComponents.ITEM_NAME, Component.translatable("item.bellum.tipped_kunai"));

                    i = craftingInput.size();
                }
            }
        }


        return outputStack;
    }

    static {
        POTION = Ingredient.of(Items.POTION);
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return BellumRecipes.KUNAI_TIPPING;
    }
}
