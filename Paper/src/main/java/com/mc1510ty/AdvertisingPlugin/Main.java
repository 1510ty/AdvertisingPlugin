package com.mc1510ty.AdvertisingPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        FileConfiguration config = getConfig();
        long interval = config.getLong("ads.interval", 600L);
        List<String> messages = config.getStringList("ads.messages");

        AdvertisingBroadcaster broadcaster = new AdvertisingBroadcaster(this, messages);
        broadcaster.start(interval);
    }
}
