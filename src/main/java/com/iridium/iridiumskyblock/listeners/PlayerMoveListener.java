package com.iridium.iridiumskyblock.listeners;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.spawn.EssentialsSpawn;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        try {
            final Player player = event.getPlayer();
            final Location location = player.getLocation();

            if (location.getY() < 0 && IridiumSkyblock.getConfiguration().voidTeleport) {
                final IslandManager islandManager = IridiumSkyblock.getIslandManager();
                final Island island = islandManager.getIslandViaLocation(location);
                final World world = location.getWorld();
                if (world == null) return;

                if (island != null) {
                    if (world.getName().equals(islandManager.getWorld().getName()))
                        island.teleportHome(player);
                    else
                        island.teleportNetherHome(player);
                } else {
                    final User user = User.getUser(player);
                    if (user.getIsland() != null) {
                        if (world.getName().equals(islandManager.getWorld().getName()))
                            user.getIsland().teleportHome(player);
                        else if (world.getName().equals(islandManager.getNetherWorld().getName()))
                            user.getIsland().teleportNetherHome(player);
                    } else if (islandManager.isIslandWorld(world)) {
                        if (Bukkit.getPluginManager().isPluginEnabled("EssentialsSpawn")) {
                            final PluginManager pluginManager = Bukkit.getPluginManager();
                            final EssentialsSpawn essentialsSpawn = (EssentialsSpawn) pluginManager.getPlugin("EssentialsSpawn");
                            final Essentials essentials = (Essentials) pluginManager.getPlugin("Essentials");
                            if (essentials != null && essentialsSpawn != null)
                                player.teleport(essentialsSpawn.getSpawn(essentials.getUser(player).getGroup()));
                        } else
                            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                    }
                }
            }
            final User user = User.getUser(player);
            final Island island = user.getIsland();
            if (island == null) return;

            if (user.flying
                    && (!island.isInIsland(location) || island.getFlightBooster() == 0)
                    && !player.getGameMode().equals(GameMode.CREATIVE)
                    && !(player.hasPermission("IridiumSkyblock.Fly")
                        || player.hasPermission("iridiumskyblock.fly"))) {
                player.setAllowFlight(false);
                player.setFlying(false);
                user.flying = false;
                player.sendMessage(Utils.color(IridiumSkyblock.getMessages().flightDisabled
                        .replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}
