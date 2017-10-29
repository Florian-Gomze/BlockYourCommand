package de.gomze.blockyourcommand.listener;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import de.gomze.blockyourcommand.utils.Config;

public class Chat implements Listener {
	
	Config config = new Config();
	List<String> blockedCMDs = config.getList("blockedCMDs");
	
	@EventHandler
	public void onChatWithCMD(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] cmd = e.getMessage().substring(1).split(" ");
		
		if(blockedCMDs.contains(cmd[0].toLowerCase())) {
			if(!p.hasPermission(config.getString("ByPassPerm"))) {
				e.setCancelled(true);
				p.sendMessage(config.getString("NotAllowed"));
			}
		}
	}

}
