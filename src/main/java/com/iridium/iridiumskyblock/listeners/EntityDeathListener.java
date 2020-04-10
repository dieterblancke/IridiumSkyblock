package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.configs.Missions.Mission;
import com.iridium.iridiumskyblock.configs.Missions.MissionData;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.MissionType;
import com.iridium.iridiumskyblock.User;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.Map;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        try {
            final LivingEntity entity = event.getEntity();
            final Player killer = entity.getKiller();
            if (killer == null) return;

            final User user = User.getUser(killer);
            final Island island = user.getIsland();
            if (island == null) return;

            final Location location = killer.getLocation();
            final World world = location.getWorld();
            if (world == null) return;

            final IslandManager islandManager = IridiumSkyblock.getIslandManager();

            final World islandWorld = islandManager.getWorld();
            if (islandWorld == null) return;

            final World islandNetherWorld = islandManager.getNetherWorld();
            if (islandNetherWorld == null) return;

            final String worldName = world.getName();
            if (!(worldName.equals(islandWorld.getName()) || worldName.equals(islandNetherWorld.getName()))) return;

            for (Mission mission : IridiumSkyblock.getMissions().missions) {
                final Map<String, Integer> levels = island.getMissionLevels();
                levels.putIfAbsent(mission.name, 1);

                final MissionData level = mission.levels.get(levels.get(mission.name));
                if (level.type != MissionType.ENTITY_KILL) return;

                final List<String> conditions = level.conditions;
                if (conditions.isEmpty() || conditions.contains(entity.toString()))
                    island.addMission(mission.name, 1);
            }

            if (island.getExpBooster() != 0)
                event.setDroppedExp(event.getDroppedExp() * 2);
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}
