package pl.inder00.minepixel.hub.data;

import java.util.Collections;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import pl.inder00.minepixel.hub.mpHub;
import pl.inder00.minepixel.hub.objects.Server;

public class Config {
	
	//=========================================================================
	//Variables
	private static Config inst;
	public FileConfiguration cfg = mpHub.getInst().getConfig();
	
	//Plugin
	public int item;
	public String name;
	public List<String> lore;
	public int slot;
	public boolean join_give;
	public String guiName;
	public String currentServer;
	
	public String[] enable;
	public String[] disable;
	public String[] full;
	public String[] current;
	
	//=========================================================================
	
	//=========================================================================
	//Reload
	public void reload(){
		mpHub.getInst().reloadConfig();
		this.cfg = mpHub.getInst().getConfig();
		load();
	}
	//=========================================================================
	
	//=========================================================================
	//Load
	public void load(){
		this.currentServer = cfg.getString("config.currentServer");
		this.item = cfg.getInt("config.item-select.id");
		this.name = cfg.getString("config.item-select.name").replace("&", "§");
		this.lore = cfg.getStringList("config.item-select.lore");
		this.slot = cfg.getInt("config.item-select.slot")-1;
		this.join_give = cfg.getBoolean("config.item-select.join-give");
		this.guiName = cfg.getString("status.guiName").replace("&", "§");
		
		this.enable = cfg.getString("status.enable").split(":");
		this.disable = cfg.getString("status.disable").split(":");
		this.current = cfg.getString("status.current").split(":");
		this.full = cfg.getString("status.full").split(":");
		
		for(String s : cfg.getConfigurationSection("servers").getKeys(false)) {
			new Server(cfg.getString("servers."+s+".serverName")
					, cfg.getString("servers."+s+".version")
					, cfg.getString("servers."+s+".itemName")
					, cfg.getStringList("servers."+s+".itemLore")
					, cfg.getInt("servers."+s+".maxPlayers")
					, cfg.getString("servers."+s+".ip")
					, cfg.getInt("servers."+s+".port"));
		}
		
	}
	//=========================================================================
	
	//=========================================================================
	//Instance
	public static Config getInst(){
		if(inst == null) return new Config();
		return inst;
	}
	public Config(){
		inst = this;
	}
	//=========================================================================

}

