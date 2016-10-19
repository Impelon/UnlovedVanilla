package de.impelon.unlovedvanilla.items;

import de.impelon.unlovedvanilla.UnlovedVanillaMain;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemManager {
	
	public static final Item LOCK = new ItemLock();
	
	public static void registerItems() {
		GameRegistry.register(LOCK, new ResourceLocation(UnlovedVanillaMain.MODID, LOCK.getUnlocalizedName().substring(5)));
	}
	
	public static void setItemModels() {
		ModelLoader.setCustomModelResourceLocation(LOCK, 0, new ModelResourceLocation(UnlovedVanillaMain.MODID + ":" + LOCK.getUnlocalizedName().substring(5), "inventory"));
	}
	
	public static void registerCraftingRecipes() {
		if (UnlovedVanillaMain.config.get("crafting", "EnableLockCrafting", false).getBoolean())
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(LOCK, 2),
					" N ",
					"N N",
					"GTG",
					Character.valueOf('T'), Blocks.TRIPWIRE_HOOK,
					Character.valueOf('G'), "ingotGold",
					Character.valueOf('N'), "nuggetGold"
			));
	}

}
