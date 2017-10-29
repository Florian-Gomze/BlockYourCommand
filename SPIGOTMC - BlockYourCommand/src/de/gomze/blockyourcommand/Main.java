package de.gomze.blockyourcommand;

import org.bukkit.plugin.java.JavaPlugin;

import de.gomze.blockyourcommand.commands.BlockYourCommand;
import de.gomze.blockyourcommand.listener.Chat;
import de.gomze.blockyourcommand.utils.Config;

public class Main extends JavaPlugin {
	
	Config config = new Config();
	
	@Override
	public void onEnable() {
		this.register();
		this.init();
		super.onEnable();
	}
	
	private void register() {
		this.getServer().getPluginManager().registerEvents(new Chat(), this);
		
		this.getCommand("blockyourcommand").setExecutor(new BlockYourCommand());
	}
	
	private void init() {
		config.create();
	}

}