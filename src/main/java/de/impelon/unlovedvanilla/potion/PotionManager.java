package de.impelon.unlovedvanilla.potion;

import de.impelon.unlovedvanilla.UnlovedVanillaMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PotionManager {
	
	public static PotionType long_luck = null;
	public static PotionType strong_luck = null;
	public static PotionType unluck = null;
	public static PotionType long_unluck = null;
	public static PotionType strong_unluck = null;
	public static PotionType levitation = null;
	public static PotionType strong_levitation = null;
	public static PotionType long_levitation = null;
	public static PotionType blindness = null;
	public static PotionType long_blindness = null;
	public static PotionType super_strong_poison = null;
	
	public static void registerPotionTypes() {
		if (UnlovedVanillaMain.config.get("potions", "EnableAdvancedLuck", true).getBoolean()) {
			long_luck = registerPotionType(new PotionType("luck", new PotionEffect(MobEffects.LUCK, 24000)), "long_luck");
			strong_luck = registerPotionType(new PotionType("luck", new PotionEffect(MobEffects.LUCK, 5000, 54)), "strong_luck");
		}
		
		unluck = registerPotionType(new PotionType("unluck", new PotionEffect(MobEffects.UNLUCK, 6000)), "unluck");
		if (UnlovedVanillaMain.config.get("potions", "EnableAdvancedUnluck", true).getBoolean()) {
			long_unluck = registerPotionType(new PotionType("unluck", new PotionEffect(MobEffects.UNLUCK, 24000)), "long_unluck");
			strong_unluck = registerPotionType(new PotionType("unluck", new PotionEffect(MobEffects.UNLUCK, 5000, 54)), "strong_unluck");
		}
		
		levitation = registerPotionType(new PotionType("levitation", new PotionEffect(MobEffects.LEVITATION, 3600)), "levitation");
		if (UnlovedVanillaMain.config.get("potions", "EnableAdvancedLevitation", true).getBoolean()) {
			long_levitation =  registerPotionType(new PotionType("levitation", new PotionEffect(MobEffects.LEVITATION, 9600)), "long_levitation");
			strong_levitation =  registerPotionType(new PotionType("levitation", new PotionEffect(MobEffects.LEVITATION, 1800, 1)), "strong_levitation");
		}
		
		blindness = registerPotionType(new PotionType("blindness", new PotionEffect(MobEffects.BLINDNESS, 3600)), "blindness");
		if (UnlovedVanillaMain.config.get("potions", "EnableAdvancedBlindness", true).getBoolean()) {
			long_blindness = registerPotionType(new PotionType("blindness", new PotionEffect(MobEffects.BLINDNESS, 9600)), "long_blindness");
		}
		
		if (UnlovedVanillaMain.config.get("potions", "EnableSuperStrongPoison", true).getBoolean()) {
			super_strong_poison = registerPotionType(new PotionType("poison", new PotionEffect(MobEffects.POISON, 1800, 54)), "super_strong_poison");
		}
	}
	
	public static void registerBrewingRecipes() {
		if (UnlovedVanillaMain.config.get("potions", "EnableLuckBrewing", true).getBoolean())
			BrewingRecipeRegistry.addRecipe(getRecipe(PotionTypes.AWKWARD, 
				new ItemStack(Items.FISH, 1, 2), 
				PotionType.getPotionTypeForName("luck")));
		if (long_luck != null)
			BrewingRecipeRegistry.addRecipe(getRecipe(PotionType.getPotionTypeForName("luck"), 
				new ItemStack(Items.REDSTONE), 
				long_luck));	
		if (strong_luck != null)
			BrewingRecipeRegistry.addRecipe(getRecipe(PotionType.getPotionTypeForName("luck"), 
				new ItemStack(Items.GLOWSTONE_DUST), 
				strong_luck));
		
		if (UnlovedVanillaMain.config.get("potions", "EnableUnluckBrewing", true).getBoolean() && unluck != null) {
			BrewingRecipeRegistry.addRecipe(getRecipe(PotionType.getPotionTypeForName("luck"), 
				new ItemStack(Items.FERMENTED_SPIDER_EYE), 
				unluck));
			if (long_unluck != null)
				BrewingRecipeRegistry.addRecipe(getRecipe(long_luck, 
					new ItemStack(Items.FERMENTED_SPIDER_EYE), 
					long_unluck));
			if (strong_unluck != null)
				BrewingRecipeRegistry.addRecipe(getRecipe(strong_luck, 
					new ItemStack(Items.FERMENTED_SPIDER_EYE), 
					strong_unluck));
		}
		if (long_unluck != null)
			BrewingRecipeRegistry.addRecipe(getRecipe(unluck, 
				new ItemStack(Items.REDSTONE), 
				long_unluck));
		if (strong_unluck != null)
			BrewingRecipeRegistry.addRecipe(getRecipe(unluck, 
				new ItemStack(Items.GLOWSTONE_DUST), 
				strong_unluck));

		if (UnlovedVanillaMain.config.get("potions", "EnableLevitationBrewing", true).getBoolean() && levitation != null) {
			BrewingRecipeRegistry.addRecipe(getRecipe(PotionTypes.LEAPING, 
				new ItemStack(Blocks.CHORUS_FLOWER), 
				levitation));
			if (long_levitation != null)
				BrewingRecipeRegistry.addRecipe(getRecipe(PotionTypes.LONG_LEAPING, 
					new ItemStack(Blocks.CHORUS_FLOWER), 
					long_levitation));
			if (strong_levitation != null)
				BrewingRecipeRegistry.addRecipe(getRecipe(PotionTypes.STRONG_LEAPING, 
					new ItemStack(Blocks.CHORUS_FLOWER), 
					strong_levitation));
		}
		if (long_levitation != null)
			BrewingRecipeRegistry.addRecipe(getRecipe(levitation, 
				new ItemStack(Items.REDSTONE), 
				long_levitation));
		if (strong_levitation!= null)
			BrewingRecipeRegistry.addRecipe(getRecipe(levitation, 
				new ItemStack(Items.GLOWSTONE_DUST), 
				strong_levitation));
		
		if (UnlovedVanillaMain.config.get("potions", "EnableBlindnessBrewing", true).getBoolean() && blindness != null) {
			BrewingRecipeRegistry.addRecipe(getRecipe(PotionTypes.NIGHT_VISION, 
				new ItemStack(Items.FERMENTED_SPIDER_EYE), 
				blindness));
			if (long_blindness != null)
				BrewingRecipeRegistry.addRecipe(getRecipe(PotionTypes.LONG_NIGHT_VISION, 
					new ItemStack(Items.FERMENTED_SPIDER_EYE), 
					long_blindness));
		}
		if (long_blindness != null)
			BrewingRecipeRegistry.addRecipe(getRecipe(blindness, 
				new ItemStack(Items.REDSTONE), 
				long_blindness));
		
		if (super_strong_poison != null)
			BrewingRecipeRegistry.addRecipe(getRecipe(PotionTypes.STRONG_POISON,
				new ItemStack(Items.POISONOUS_POTATO),
				super_strong_poison));
	}
	
	private static BrewingRecipeWithPotions getRecipe(PotionType in, ItemStack ingredient, PotionType out) {
		return new BrewingRecipeWithPotions(
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), in), 
				ingredient, 
				PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), out));
	}
	
	private static PotionType registerPotionType(PotionType type, String name) {
		return GameRegistry.register(type.setRegistryName(UnlovedVanillaMain.MODID, name));
	}

}
