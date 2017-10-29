package de.gomze.blockyourcommand.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.gomze.blockyourcommand.utils.Config;

public class BlockYourCommand implements CommandExecutor {
	
	Config config = new Config();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		List<String> list = config.getList("blockedCMDs");
		if(args[0].equalsIgnoreCase("add")) {
			if(p.hasPermission(config.getString("AddPerm"))) {
				if(list.contains(args[1])) {
					p.sendMessage("§cThis command is already blocked!");
				} else {
					config.blockedCMDs.add(args[1]);
					config.addString(args[1]);
					p.sendMessage("§aCommand §e" + args[1] + " §awas added to the blacklist!");
				}
			}
		} else if(args[0].equalsIgnoreCase("remove")) {
			if(p.hasPermission(config.getString("RemovePerm"))) {
				if(list.contains(args[1])) {
					config.blockedCMDs.remove(args[1]);
					config.removeString(args[1]);
					p.sendMessage("§aCommand §e" + args[1] + " §awas removed from the blacklist!");
				} else {
					p.sendMessage("§cThis command is not blocked!");
				}
			}
		} else {
			if(p.hasPermission(config.getString("AddPerm")) || p.hasPermission(config.getString("RemovePerm"))) {
				p.sendMessage("§cPlease use /blockyourcommand <add/remove> <cmd>");
			}
		}
		return false;
	}

}
