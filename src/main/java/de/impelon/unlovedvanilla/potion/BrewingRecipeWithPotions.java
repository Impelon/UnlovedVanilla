package de.impelon.unlovedvanilla.potion;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BrewingRecipeWithPotions implements IBrewingRecipe {

	protected ItemStack in = null;
	protected ItemStack out = null;
	protected ItemStack ingredient = null;

	public BrewingRecipeWithPotions(ItemStack in, ItemStack ingredient, ItemStack out) {
		this.in = in;
		this.out = out;
		this.ingredient = ingredient;
	}

	@Override
	public boolean isInput(ItemStack input) {
		return input.hasTagCompound() &&  
				PotionType.getPotionTypeForName(in.getTagCompound().getString("Potion")) == 
				PotionType.getPotionTypeForName(input.getTagCompound().getString("Potion"));
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return ingredient.getItem() == this.ingredient.getItem() &&
				ingredient.getMetadata() == this.ingredient.getMetadata();
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (ingredient != null && input != null && isIngredient(ingredient)) {
			if (input != this.out && isInput(input)) {
				ItemStack out = new ItemStack(input.getItem());
				out.setTagCompound(this.out.getTagCompound());
				return out;
			}
		}
		return null;
	}

}
