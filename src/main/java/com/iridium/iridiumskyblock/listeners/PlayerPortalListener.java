package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.XMaterial;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.lang.reflect.InvocationTargetException;

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

            if (XMaterial.ISFLAT)
                event.setCanCreatePortal(true);
            else {
                try {
                    PlayerPortalEvent.class.getMethod("useTravelAgent", boolean.class).invoke(event, true);
                    Class.forName("org.bukkit.TravelAgent")
                            .getMethod("setCanCreatePortal", boolean.class)
                            .invoke(PlayerPortalEvent.class.getMethod("getPortalTravelAgent").invoke(event), true);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            final World world = event.getFrom().getWorld();
            if (world == null) return;

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
