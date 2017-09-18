package pl.inder00.minepixel.hub.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.minepixel.hub.mpHub;
import pl.inder00.minepixel.hub.data.Config;
import pl.inder00.minepixel.hub.data.FileManager;
import pl.inder00.minepixel.hub.inventory.HubInventory;
import pl.inder00.minepixel.hub.objects.Server;

public class HubCommand implements CommandExecutor {
	
	public HubCommand(mpHub hub) {
		hub.getCommand("hub").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if(args.length > 0) {
			if(!p.isOp()) {
				p.openInventory(HubInventory.getInventory());
				return  false;
			}
			if(args[0].equalsIgnoreCase("reload")) {
				FileManager.check();
				p.sendMessage("§aPrzeladowywanie...");
				Server.getServers().clear();
				Config.getInst().reload();
				p.sendMessage(" §8* §econfig.yml");
				Server.reload();
				p.sendMessage(" §8* §eBungeeCord Channel (PlayerCount)");
				p.sendMessage(" §8* §eSocket (ServerStatus)");
				p.sendMessage("§aPomylsnie przeladowano");
				return false;
			} else {
				p.sendMessage("§cDostepne komendy: /hub reload");
			}
		} else {
			p.openInventory(HubInventory.getInventory());
			return false;
		}
		return false;
	}

}
