package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.User;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerPortalListener implements Listener {

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        try {
            if (!event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) return;

            if (!IridiumSkyblock.getConfiguration().netherIslands) {
                event.setCancelled(true);
                return;
            }

            final Player player = event.getPlayer();
            final User user = User.getUser(player);
            Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(event.getFrom());
            if (island == null) return;

            if (!(island.getPermissions(user).useNetherPortal || user.bypassing)) {
                event.setCancelled(true);
                return;
            }

            final World world = event.getFrom().getWorld();
            if (world == null) {
                return;
            }

            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            if (world.getName().equals(islandManager.getWorld().getName()))
                event.setTo(island.getNetherhome());
            else
                event.setTo(island.getHome());
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}
