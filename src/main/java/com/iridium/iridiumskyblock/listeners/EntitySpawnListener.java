package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;

import java.util.UUID;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (!IridiumSkyblock.getConfiguration().blockedEntities.contains(event.getEntityType())) return;

        final Entity entity = event.getEntity();
        final Location location = entity.getLocation();
        final IslandManager islandManager = IridiumSkyblock.getIslandManager();
        final Island island = islandManager.getIslandViaLocation(location);
        if (island == null) return;

        IridiumSkyblock.getInstance().entities.put(entity.getUniqueId(), island);
        monitorEntity(entity);
    }

    @EventHandler
    public void onVehicleSpawn(VehicleCreateEvent event) {
        final Vehicle vehicle = event.getVehicle();
        if (!IridiumSkyblock.getConfiguration().blockedEntities.contains(vehicle.getType())) return;

        final IslandManager islandManager = IridiumSkyblock.getIslandManager();
        final Location location = vehicle.getLocation();
        final Island island = islandManager.getIslandViaLocation(location);
        if (island == null) return;

        IridiumSkyblock.getInstance().entities.put(vehicle.getUniqueId(), island);
        monitorEntity(vehicle);
    }

    public void monitorEntity(Entity entity) {
        if (entity == null) return;
        if (entity.isDead()) return;

        final UUID uuid = entity.getUniqueId();
        final Island startingIsland = IridiumSkyblock.getInstance().entities.get(uuid);
        if (startingIsland.isInIsland(entity.getLocation())) {
            //The entity is still in the island, so make a scheduler to check again
            Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), () -> monitorEntity(entity), 20);
        } else {
            //The entity is not in the island, so remove it
            entity.remove();
            IridiumSkyblock.getInstance().entities.remove(uuid);
        }
    }
}
