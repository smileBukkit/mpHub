package pl.inder00.minepixel.hub.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import pl.inder00.minepixel.hub.data.Config;
import pl.inder00.minepixel.hub.objects.Server;
import pl.inder00.minepixel.hub.util.Util;

public class HubInventory {
	
	public static Inventory getInventory() {
		Config cfg = Config.getInst();
		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, cfg.guiName);
		for(Server s : Server.getServers()) {
			inv.setItem(s.getInventorySlot(), Util.serverItemStack(s));
		}
		return inv;
	}

}
