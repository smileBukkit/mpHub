package pl.inder00.minepixel.hub.channels;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import pl.inder00.minepixel.hub.mpHub;

public class BungeeCord {
	
    public static final String PLUGIN_CHANNEL = "BungeeCord";

    public static void connect(Player player, String target) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(array);
        try {
            output.writeUTF("Connect");
            output.writeUTF(target);
        } catch (IOException e) {
        	e.printStackTrace();
        	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Nie mozna polaczyc sie do serwera "+target+" ("+e.getCause().getMessage()+")");
        }

        player.sendPluginMessage(mpHub.getInst(), PLUGIN_CHANNEL, array.toByteArray());
    }
    public static void playerCount(String target) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(array);
        try {
            output.writeUTF("PlayerCount");
            output.writeUTF(target);
        } catch (IOException e) {
        	e.printStackTrace();
        	Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Nie mozna okreslic ilosci graczy na serwerze "+target+" ("+e.getCause().getMessage()+")");
        }

        Bukkit.getOnlinePlayers().iterator().next().sendPluginMessage(mpHub.getInst(), PLUGIN_CHANNEL, array.toByteArray());
    }
    
    public static boolean isOnline(String ip, int port) {
    	try {
    		Socket sock = new Socket();
    		sock.setSoTimeout(10);
    		sock.connect(new InetSocketAddress(ip, port), 10);
    		sock.close();
    		return true;
    	} catch (ConnectException e) {
    		return false;
    	} catch (UnknownHostException e) {
    		return false;
    	} catch (IOException e) {
    		return false;
    	}
    }
	
	

}
