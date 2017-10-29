package de.gomze.blockyourcommand.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	
	public List<String> blockedCMDs = new ArrayList<String>();
	File folder = new File("plugins//BlockYourCommand");
	File file = new File("plugins//BlockYourCommand//config.yml");
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	private boolean exists() {
		File file = new File("plugins//BlockYourCommand//config.yml");
		if(file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void create() {
		if(!this.exists()) {
			try {
				folder.mkdirs();
				file.createNewFile();
				cfg.set("ByPassPerm", "blockyourcmd.bypass");
				cfg.set("AddPerm", "blockyourcmd.add");
				cfg.set("RemovePerm", "blockyourcmd.remove");
				cfg.set("NotAllowed", "&cYou are not allowed to use this command.");
				blockedCMDs.add("bukkit");
				blockedCMDs.add("help");
				cfg.set("blockedCMDs", blockedCMDs);
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getString(String path) {
		return cfg.getString(path).replace('&', '§');
	}
	
	public List<String> getList(String path) {
		return cfg.getStringList(path);
	}
	
	public void addString(String string) {
		List<String> list = this.getList("blockedCMDs");
		list.add(string.replaceAll("/", ""));
		cfg.set("blockedCMDs", list);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeString(String string) {
		List<String> list = this.getList("blockedCMDs");
		list.remove(string.replaceAll("/", ""));
		cfg.set("blockedCMDs", list);
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
