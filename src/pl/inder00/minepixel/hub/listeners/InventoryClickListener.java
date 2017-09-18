package pl.inder00.minepixel.hub.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import pl.inder00.minepixel.hub.channels.BungeeCord;
import pl.inder00.minepixel.hub.data.Config;
import pl.inder00.minepixel.hub.objects.Server;


public class InventoryClickListener implements Listener {
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.isCancelled()) return;
		Config cfg = Config.getInst();
		if(e.getInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getType().equals(Material.AIR)) return;
		if(e.getInventory().getTitle() !=null && e.getInventory().getTitle().equals(cfg.guiName)) {
			Player p = (Player) e.getWhoClicked();
			ItemStack select = e.getCurrentItem();
			Server s = Server.getUsingItemName(select.getItemMeta().getDisplayName()
					.replace(cfg.enable[2].replace("&", "§"), "")
					.replace(cfg.disable[2].replace("&", "§"), "")
					.replace(cfg.full[2].replace("&", "§"), "")
					.replace(cfg.current[2].replace("&", "§"), "")
					.replace(" ", "")
					);
			p.closeInventory();
			e.setCancelled(true);
			if(s == null) {
				p.sendMessage("§cSerwer "+select.getItemMeta().getDisplayName()
						.replace(cfg.enable[2].replace("&", "§"), "")
						.replace(cfg.disable[2].replace("&", "§"), "")
						.replace(cfg.full[2].replace("&", "§"), "")
						.replace(cfg.current[2].replace("&", "§"), "")
						.replace(" ", "")
						+" §cnie istnieje");
				return;
			}
			if(s.getServerName().equalsIgnoreCase(cfg.currentServer)) {
				p.sendMessage("§cZnajdujesz sie juz na tym serwerze");
				return;
			}
			if(!s.getServerEnabled()) {
				p.sendMessage("§cSerwer jest wylaczony");
				return;
			}
			if(s.isFull()) {
				p.sendMessage("§cSerwer jest pelny");
				return;
			}
			BungeeCord.connect(p, s.getServerName());
			return;
		}
	}

}
