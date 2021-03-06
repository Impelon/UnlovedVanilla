package de.impelon.unlovedvanilla.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import de.impelon.unlovedvanilla.UnlovedVanillaMain;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class VersionChecker implements Runnable {

	private static boolean isLatestVersion = false;
	private static String latestVersion = "";
	private static String url = "";

	@Override
	public void run() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/Impelon/UnlovedVanilla/1.9.4/src/main/resources/mcmod.info").openStream(), "UTF-8"));
			StringBuilder modinfo = new StringBuilder();
			String ln;
			while ((ln = in.readLine()) != null)
				modinfo.append(ln);
			
			int verindex = modinfo.indexOf("\"version\"");
			if (verindex != -1) {
				String verstr = modinfo.substring(verindex + 9, modinfo.indexOf(",", verindex));
				latestVersion = verstr.substring(verstr.indexOf('\"') + 1, verstr.lastIndexOf('\"'));
			}
			int urlindex = modinfo.indexOf("\"url\"");
			if (urlindex != -1) {
				String urlstr = modinfo.substring(urlindex + 5, modinfo.indexOf(",", urlindex));
				url = urlstr.substring(urlstr.indexOf('\"') + 1, urlstr.lastIndexOf('\"'));
			}
		} catch (IOException ex) {
			latestVersion = UnlovedVanillaMain.VERSION;
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		isLatestVersion = UnlovedVanillaMain.VERSION.equals(this.getLatestVersion());
	}

	public boolean isLatestVersion() {
		return isLatestVersion;
	}

	public String getLatestVersion() {
		return latestVersion;
	}
	
	public String getUrl() {
		return url;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onEvent(PlayerTickEvent ev) {
		if (ev.player.worldObj.isRemote) {
			if (isLatestVersion()) {
				MinecraftForge.EVENT_BUS.unregister(this);
				return;
			}
			Style linkStyle = new Style().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, getUrl()));
			TextComponentString warning = new TextComponentString(UnlovedVanillaMain.PREFIX +
					new TextComponentTranslation("msg.outdated.txt").getFormattedText() + " �o(" +
					new TextComponentTranslation("msg.currentversion.txt").getUnformattedText() + UnlovedVanillaMain.VERSION + " �o" +
					new TextComponentTranslation("msg.latestversion.txt").getUnformattedText() + getLatestVersion() + "�o)");
			warning.setStyle(linkStyle);
			ev.player.addChatMessage(warning);
			MinecraftForge.EVENT_BUS.unregister(this);
		}
	}

}
