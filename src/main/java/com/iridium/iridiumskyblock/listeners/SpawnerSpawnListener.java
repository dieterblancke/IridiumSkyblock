package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class SpawnerSpawnListener implements Listener {

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        try {
            final Location location= event.getLocation();
            final Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(location);
            if (island == null) return;

            if (island.getSpawnerBooster() == 0) return;

            final IridiumSkyblock plugin = IridiumSkyblock.getInstance();
            final CreatureSpawner spawner = event.getSpawner();
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> spawner.setDelay(spawner.getDelay() / 2), 0);
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

}
