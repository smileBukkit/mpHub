package pl.inder00.minepixel.hub.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;

import net.md_5.bungee.api.ChatColor;
import pl.inder00.minepixel.hub.mpHub;
import pl.inder00.minepixel.hub.channels.BungeeCord;


public class Server {
	
	private static ArrayList<Server> servers = new ArrayList<Server>();
	
	private String serverName;
	private String fromVersion;
	private String itemName;
	private List<String> itemLore;
	private int maxPlayerCount;
	private int inventorySlot;
	private int playerCount = 0;
	private boolean serverEnabled;
	private String ipAdress;
	private int port;
	
	public static int serverAmount() {
		return servers.size();
	}
	public static ArrayList<Server> getServers(){
		return servers;
	}
	
	public Server(String serverName, String fromVersion, String itemName, List<String> itemLore, int maxPlayerCount
			,String ip, int port) {
		this.serverName = serverName;
		this.fromVersion = fromVersion;
		this.itemName = ChatColor.translateAlternateColorCodes('&', itemName);
		this.itemLore = itemLore;
		this.maxPlayerCount = maxPlayerCount;
		this.inventorySlot = servers.size();
		this.serverEnabled = false;
		this.ipAdress = ip;
		this.port = port;
		if(servers.size() < 5) servers.add(this);
	}
	public static Server getUsingServername(String servername) {
		for(Server s : servers) {
			if(s.getServerName().equalsIgnoreCase(servername))
				return s;
		}
		return null;
	}
	public static Server getUsingItemName(String itemname) {
		for(Server s : servers) {
			if(s.getItemName().equalsIgnoreCase(itemname))
				return s;
		}
		return null;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getFromVersion() {
		return fromVersion;
	}

	public void setFromVersion(String fromVersion) {
		this.fromVersion = fromVersion;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<String> getItemLore() {
		return itemLore;
	}

	public void setItemLore(List<String> itemLore) {
		this.itemLore = itemLore;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	
	@SuppressWarnings("deprecation")
	public static void updatePlayerCount() {
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(mpHub.getInst(), new Runnable(){
			public void run(){
				if(Bukkit.getOnlinePlayers().size() == 0) return;
				for(Server s : servers) {
					if(!s.getServerEnabled()) return;
					BungeeCord.playerCount(s.getServerName());
				}
			}
		}, 0, 20*5);
	}
	@SuppressWarnings("deprecation")
	public static void updateServersStatus() {
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(mpHub.getInst(), new Runnable(){
			public void run(){
				for(Server s : servers) {
					s.setServerEnabled(BungeeCord.isOnline(s.getIpAdress(), s.getPort()));
				}
			}
		}, 0, 20*60*5);
	}
	public static void reload() {
		if(Bukkit.getOnlinePlayers().size() == 0) return;
		for(Server s : servers) {
			BungeeCord.playerCount(s.getServerName());
			s.setServerEnabled(BungeeCord.isOnline(s.getIpAdress(), s.getPort()));
		}
	}
	public int getMaxPlayerCount() {
		return maxPlayerCount;
	}
	public void setMaxPlayerCount(int maxPlayerCount) {
		this.maxPlayerCount = maxPlayerCount;
	}
	public boolean isFull() {
		if(this.getPlayerCount() >= this.getMaxPlayerCount()) return true;
		return false;
	}
	public int getInventorySlot() {
		return inventorySlot;
	}
	public void setInventorySlot(int inventorySlot) {
		this.inventorySlot = inventorySlot;
	}
	public boolean getServerEnabled() {
		return serverEnabled;
	}
	public void setServerEnabled(boolean serverEnabled) {
		this.serverEnabled = serverEnabled;
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

}
