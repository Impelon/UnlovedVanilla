package de.impelon.unlovedvanilla.proxies;

import de.impelon.unlovedvanilla.UnlovedVanillaMain;
import de.impelon.unlovedvanilla.items.ItemManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CombinedClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent ev) {
		super.preInit(ev);
		
		ItemManager.setItemModels();
	}
	
	@Override
	public void load(FMLInitializationEvent ev) {
		super.load(ev);
		
		if (UnlovedVanillaMain.config.get("general", "CheckVersion", true).getBoolean())
			MinecraftForge.EVENT_BUS.register(UnlovedVanillaMain.versionChecker);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent ev) {
		super.postInit(ev);
		
		if (UnlovedVanillaMain.config.get("general", "CheckVersion", true).getBoolean())
			new Thread(UnlovedVanillaMain.versionChecker, "Version Check").start();
	}
}
