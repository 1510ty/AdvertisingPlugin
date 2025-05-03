package com.mc1510ty.AdvertisingPlugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AdvertisingBroadcaster {

    private final JavaPlugin plugin;
    private final List<String> ads;
    private int index = 0;

    public AdvertisingBroadcaster(JavaPlugin plugin, List<String> ads) {
        this.plugin = plugin;
        this.ads = ads;
    }

    public void start(long intervalTicks) {
        if (ads.isEmpty()) {
            plugin.getLogger().warning("宣伝メッセージが設定されていません！");
            plugin.getLogger().warning("Promotional message not set!");
            return;
        }

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Bukkit.broadcastMessage(ads.get(index));
            index = (index + 1) % ads.size();
        }, 1L, intervalTicks);

        plugin.getLogger().info("Spigotバージョンで開始します");
        plugin.getLogger().info("Start with the Spigot version");
    }
}
