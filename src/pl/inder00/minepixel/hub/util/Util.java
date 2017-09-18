package pl.inder00.minepixel.hub.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.minepixel.hub.data.Config;
import pl.inder00.minepixel.hub.objects.Server;

public class Util {
	
	@SuppressWarnings("deprecation")
	public static ItemStack serverItemStack(Server s) {
		Config cfg = Config.getInst();
		ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(cfg.disable[0])), 1, Short.parseShort(cfg.disable[1]));
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', cfg.disable[2])+" "+s.getItemName());
		if(s.getServerEnabled() == false) {
			item = new ItemStack(Material.getMaterial(Integer.parseInt(cfg.disable[0])), 1, Short.parseShort(cfg.disable[1]));
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', cfg.disable[2])+" "+s.getItemName());
		} else if(cfg.currentServer.equalsIgnoreCase(s.getServerName())) {
			item = new ItemStack(Material.getMaterial(Integer.parseInt(cfg.current[0])), 1, Short.parseShort(cfg.current[1]));
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', cfg.current[2])+" "+s.getItemName());
		} else if(s.isFull()){
			item = new ItemStack(Material.getMaterial(Integer.parseInt(cfg.full[0])), 1, Short.parseShort(cfg.full[1]));
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', cfg.full[2])+" "+s.getItemName());
		} else if(!s.isFull() && s.getServerEnabled()){
			item = new ItemStack(Material.getMaterial(Integer.parseInt(cfg.enable[0])), 1, Short.parseShort(cfg.enable[1]));
			itemmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', cfg.enable[2])+" "+s.getItemName());
		}
		List<String> loreOutput  = new ArrayList<String>();
		for(String loreInput : s.getItemLore()) {
			loreOutput.add(ChatColor.translateAlternateColorCodes('&', loreInput
					.replace("{ONLINE}", ""+s.getPlayerCount())
					.replace("{MAX}", ""+s.getMaxPlayerCount())
					.replace("{VERSION}", s.getFromVersion())));
		}
		itemmeta.setLore(loreOutput);
		item.setItemMeta(itemmeta);
		return item;
	}

}
