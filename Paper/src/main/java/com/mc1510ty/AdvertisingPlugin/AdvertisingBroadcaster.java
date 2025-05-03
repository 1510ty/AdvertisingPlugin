package com.mc1510ty.AdvertisingPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Server;
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
            return;
        }

        Server server = plugin.getServer();
        try {
            server.getClass().getMethod("getGlobalRegionScheduler");

            server.getGlobalRegionScheduler().runAtFixedRate(
                    plugin,
                    task -> {
                        Bukkit.broadcastMessage(ads.get(index));
                        index = (index + 1) % ads.size();
                    },
                    1L,
                    intervalTicks
            );

            plugin.getLogger().info("Foliaモードで宣伝を開始しました。");

        } catch (NoSuchMethodException e) {
            Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                Bukkit.broadcastMessage(ads.get(index));
                index = (index + 1) % ads.size();
            }, 1L, intervalTicks);

            plugin.getLogger().info("Paperモードで宣伝を開始しました。");
        }
    }
}
