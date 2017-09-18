package pl.inder00.minepixel.hub.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import pl.inder00.minepixel.hub.data.Config;
import pl.inder00.minepixel.hub.inventory.HubInventory;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		if(
				action == Action.LEFT_CLICK_AIR
				|| action == Action.LEFT_CLICK_BLOCK 
				|| action == Action.RIGHT_CLICK_AIR 
				|| action == Action.RIGHT_CLICK_BLOCK
		) {
			Config cfg = Config.getInst();
			if(p.getItemInHand().getType() == Material.getMaterial(cfg.item) && p.getItemInHand().getItemMeta().getDisplayName()
					.equalsIgnoreCase(cfg.name)) {
				p.openInventory(HubInventory.getInventory());
			}
			
		}
	}

}
