package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.configs.Missions.Mission;
import com.iridium.iridiumskyblock.configs.Missions.MissionData;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Crops;

import java.util.List;
import java.util.Map;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        try {
            final Player player = event.getPlayer();
            final User user = User.getUser(player);
            final Block block = event.getBlock();
            final Location location = block.getLocation();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            final Island island = islandManager.getIslandViaLocation(location);
            if (island == null) {
                final World world = location.getWorld();
                if (world == null) return;

                final World islandWorld = islandManager.getWorld();
                if (islandWorld == null) return;

                final World islandNetherWorld = islandManager.getNetherWorld();
                if (islandNetherWorld == null) return;

                final String worldName = world.getName();
                if (worldName.equals(islandWorld.getName()) || worldName.equals(islandNetherWorld.getName())) {
                    if (!user.bypassing)
                        event.setCancelled(true);
                }
                return;
            }

            final Material material = block.getType();
            final XMaterial xmaterial = XMaterial.matchXMaterial(material);
            if (IridiumSkyblock.getConfiguration().limitedBlocks.containsKey(xmaterial)) {
                final int max = IridiumSkyblock.getConfiguration().limitedBlocks.get(xmaterial);
                if (island.valuableBlocks.getOrDefault(xmaterial.name(), 0) >= max) {
                    player.sendMessage(Utils.color(IridiumSkyblock.getMessages().blockLimitReached
                        .replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    event.setCancelled(true);
                    return;
                }
            }

            if (user.islandID == island.getId()) {
                for (Mission mission : IridiumSkyblock.getMissions().missions) {
                    final Map<String, Integer> levels = island.getMissionLevels();
                    levels.putIfAbsent(mission.name, 1);

                    final MissionData level = mission.levels.get(levels.get(mission.name));
                    if (level == null) continue;
                    if (level.type != MissionType.BLOCK_PLACE) continue;

                    final List<String> conditions = level.conditions;

                    if (
                            conditions.isEmpty()
                            ||
                            conditions.contains(xmaterial.name())
                            ||
                            conditions.contains(((Crops) block.getState().getData()).getState().toString())
                    )
                        island.addMission(mission.name, 1);
                }
            }

            if (!(island.getPermissions(user).placeBlocks || user.bypassing))
                event.setCancelled(true);
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMonitorBlockPlace(BlockPlaceEvent event) {
        try {
            final Block block = event.getBlock();
            final Location location = block.getLocation();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            final Island island = islandManager.getIslandViaLocation(location);
            if (island == null) return;

            if (!Utils.isBlockValuable(block)) return;

            final Material material = block.getType();
            final XMaterial xmaterial = XMaterial.matchXMaterial(material);
            island.valuableBlocks.compute(xmaterial.name(), (name, original) -> {
                if (original == null) return 1;
                return original + 1;
            });
            if (island.updating)
                island.tempValues.add(location);
            island.calculateIslandValue();
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}
