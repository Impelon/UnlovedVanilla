package de.impelon.unlovedvanilla;

import de.impelon.unlovedvanilla.proxies.CommonProxy;
import de.impelon.unlovedvanilla.update.VersionChecker;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="unlovedvanilla", name="UnlovedVanilla", version="1.0")
public class UnlovedVanillaMain {
	
	public static final String MODID = "unlovedvanilla";
	public static final String VERSION = "1.0";
	public static final String PREFIX = "§7[§5U§8nloved§5V§8anilla§7] §r";
	public static final VersionChecker versionChecker = new VersionChecker();
	public static Configuration config;
	
	@Mod.Instance(value="unlovedvanilla")
	public static UnlovedVanillaMain instance;
	
	@SidedProxy(clientSide="de.impelon.unlovedvanilla.proxies.CombinedClientProxy", serverSide="de.impelon.unlovedvanilla.proxies.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent ev) {
		proxy.preInit(ev);
	}
	
	@EventHandler
	public static void load(FMLInitializationEvent ev) {
		proxy.load(ev);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent ev) {
		proxy.postInit(ev);
	}

}
