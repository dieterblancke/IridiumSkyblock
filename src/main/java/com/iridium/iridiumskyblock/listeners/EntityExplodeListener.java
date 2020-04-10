package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Map;
import java.util.UUID;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        try {
            final Entity entity = event.getEntity();
            final Location location = entity.getLocation();
            final World world = location.getWorld();
            if (world == null) return;

            final IslandManager islandManager = IridiumSkyblock.getIslandManager();

            final World islandWorld = islandManager.getWorld();
            if (islandWorld == null) return;

            final World islandNetherWorld = islandManager.getNetherWorld();
            if (islandNetherWorld == null) return;

            final String worldName = world.getName();
            if (!(worldName.equals(islandWorld.getName()) || worldName.equals(islandNetherWorld.getName()))) return;

            if (!IridiumSkyblock.getConfiguration().allowExplosions)
                event.setCancelled(true);
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMonitorEntityExplode(EntityExplodeEvent event) {
        try {
            final Entity entity = event.getEntity();
            final Location location = entity.getLocation();
            final UUID uuid = entity.getUniqueId();

            final Map<UUID, Island> entities = IridiumSkyblock.getInstance().entities;
            Island island = entities.get(uuid);
            if (island != null && island.isInIsland(location)) {
                event.setCancelled(true);
                entity.remove();
                entities.remove(uuid);
                return;
            }

            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            island = islandManager.getIslandViaLocation(location);
            if (island == null) return;

            final IridiumSkyblock plugin = IridiumSkyblock.getInstance();
            for (Block block : event.blockList()) {
                if (!island.isInIsland(block.getLocation())) {
                    final BlockState state = block.getState();
                    IridiumSkyblock.nms.setBlockFast(block, 0, (byte) 0);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> state.update(true, true));
                }

                if (!Utils.isBlockValuable(block)) continue;

                if (!(block.getState() instanceof CreatureSpawner)) {
                    final Material material = block.getType();
                    final XMaterial xmaterial = XMaterial.matchXMaterial(material);
                    island.valuableBlocks.computeIfPresent(xmaterial.name(), (name, original) -> original - 1);
                }

                if (island.updating)
                    island.tempValues.remove(block.getLocation());
            }
            island.calculateIslandValue();
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}
