package pl.inder00.minepixel.hub;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import pl.inder00.minepixel.hub.channels.BungeeCord;
import pl.inder00.minepixel.hub.commands.HubCommand;
import pl.inder00.minepixel.hub.data.Config;
import pl.inder00.minepixel.hub.data.FileManager;
import pl.inder00.minepixel.hub.listeners.InventoryClickListener;
import pl.inder00.minepixel.hub.listeners.PlayerInteractListener;
import pl.inder00.minepixel.hub.listeners.PlayerJoinListener;
import pl.inder00.minepixel.hub.objects.Server;

public class mpHub extends JavaPlugin implements PluginMessageListener {

	private static mpHub inst;
	public static mpHub getInst(){
		return inst;
	}
	
	
	public void onEnable() {
		inst = this;
		FileManager.check();
		Config.getInst().load();
		getServer().getMessenger().registerOutgoingPluginChannel(this, BungeeCord.PLUGIN_CHANNEL);
		getServer().getMessenger().registerIncomingPluginChannel(this, BungeeCord.PLUGIN_CHANNEL, this);	
		new HubCommand(this);
		
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
		
		
		Server.updatePlayerCount();
		Server.updateServersStatus();
		if(Server.getUsingServername(Config.getInst().currentServer) == null) {
			Bukkit.getConsoleSender().sendMessage("§cAktualny serwer nie zostal zdefiniowany w config.yml");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		Bukkit.getConsoleSender().sendMessage("§aWladowano mpHub (§c"+Server.serverAmount()+"§a serwerow)");
	}
	
	
	public void onDisable() {
	}
	
    public void onPluginMessageReceived(String channel, Player p, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
       
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
       
        if (subchannel != null && subchannel.equals("PlayerCount")) {
        	String server = in.readUTF();
        	int playerCount = in.readInt();
            Server.getUsingServername(server).setPlayerCount(playerCount);
            
        }
       
    }

	
	

}
