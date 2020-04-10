package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinLeaveListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        try {
            final Player player = event.getPlayer();
            final IridiumSkyblock plugin = IridiumSkyblock.getInstance();
            if (player.isOp()) {
                final String latest = plugin.getLatest();
                if (plugin.getLatest() != null
                        && IridiumSkyblock.getConfiguration().notifyAvailableUpdate
                        && !latest.equals(plugin.getDescription().getVersion())) {
                    final String prefix = IridiumSkyblock.getConfiguration().prefix;
                    player.sendMessage(Utils.color(prefix + " &7This message is only seen by opped players."));
                    player.sendMessage(Utils.color(prefix + " &7Newer version available: " + latest));
                }
            }

            final User user = User.getUser(player);
            user.name = player.getName();

            if (user.flying && (user.getIsland() == null || user.getIsland().getFlightBooster() == 0)) {
                player.setAllowFlight(false);
                player.setFlying(false);
                user.flying = false;
            }
            user.bypassing = false;

            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            final Island island = islandManager.getIslandViaLocation(player.getLocation());
            if (island != null)
                Bukkit.getScheduler().runTaskLater(plugin, () -> island.sendBorder(player), 1);
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}
