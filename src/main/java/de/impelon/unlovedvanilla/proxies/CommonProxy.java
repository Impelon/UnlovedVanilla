package de.impelon.unlovedvanilla.proxies;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import de.impelon.unlovedvanilla.UnlovedVanillaMain;
import de.impelon.unlovedvanilla.items.ItemManager;
import de.impelon.unlovedvanilla.potion.BrewingRecipeWithPotions;
import de.impelon.unlovedvanilla.potion.PotionManager;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;


public class CommonProxy {
		
	public void preInit(FMLPreInitializationEvent ev) {
		Configuration config = new Configuration(ev.getSuggestedConfigurationFile());
		UnlovedVanillaMain.config = config;
		config.load();
		
		config.get("general", "CheckVersion", true, "Should UnlovedVanilla check for new versions on startup?");
		
		config.get("potions", "EnableAdvancedLuck", true, "Should the advanced versions of the Potion of Luck be avalible? (includes brewing recipes)");
		config.get("potions", "EnableAdvancedUnluck", true, "Should the advanced versions of the Potion of Bad Luck be avalible? (includes brewing recipes)");
		config.get("potions", "EnableAdvancedLevitation", true, "Should the advanced versions of the Potion of Levitation be avalible? (includes brewing recipes)");
		config.get("potions", "EnableSuperStrongPoison", true, "Should the super strong version of the Potion of Poison be avalible? (includes brewing recipe)");
		config.get("potions", "EnableLuckBrewing", true, "Should the recipe for the Potion of Luck be avalible?");
		config.get("potions", "EnableUnluckBrewing", true, "Should the recipe for the Potion of Bad Luck be avalible?");
		config.get("potions", "EnableLevitationBrewing", true, "Should the recipe for the Potion of Levitation be avalible?");
		config.get("potions", "EnableBlindnessBrewing", true, "Should the recipe for the Potion of Blindness be avalible?");
		
		config.get("crafting", "EnableLockCrafting", false, "Should the recipe for the Lock be avalible?");
		
		config.save();
		
		ItemManager.registerItems();
		
	}
	
	public void load(FMLInitializationEvent ev) {
		ItemManager.registerCraftingRecipes();
		
		PotionManager.registerPotionTypes();
		
		PotionManager.registerBrewingRecipes();
	}
	
	public void postInit(FMLPostInitializationEvent ev) {}
	
}
