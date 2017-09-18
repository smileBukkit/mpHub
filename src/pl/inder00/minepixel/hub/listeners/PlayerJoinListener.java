package pl.inder00.minepixel.hub.listeners;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.minepixel.hub.data.Config;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Config cfg = Config.getInst();
		if(!cfg.join_give) return;
		Player p = e.getPlayer();
		ItemStack item = new ItemStack(Material.getMaterial(cfg.item), 1);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(cfg.name);
		List<String> loreOutput  = new ArrayList<String>();
		for(String loreInput : cfg.lore) {
			loreOutput.add(ChatColor.translateAlternateColorCodes('&', loreInput
					.replace("{NICKNAME}", p.getName())));
		}
		itemmeta.setLore(loreOutput);
		item.setItemMeta(itemmeta);
		p.getInventory().setItem(cfg.slot, item);
	}

}
