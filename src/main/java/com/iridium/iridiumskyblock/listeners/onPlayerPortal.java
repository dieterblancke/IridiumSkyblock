package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.iridium.iridiumskyblock.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class onPlayerPortal implements Listener {

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent e) {
        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
            if (IridiumSkyblock.getConfiguration().netherIslands) {
                User u = User.getUser(e.getPlayer());
                Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getFrom());
                if (island != null) {
                    if (island.getPermissions((u.islandID == island.getId() || island.isCoop(u.getIsland())) ? (island.isCoop(u.getIsland()) ? Role.Member : u.getRole()) : Role.Visitor).useNetherPortal || u.bypassing) {
                        if (e.getFrom().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld())) {
                            e.setTo(island.getNetherhome());
                        } else {
                            e.setTo(island.getHome());
                        }
                    } else {
                        e.setCancelled(true);
                    }
                }
            } else {
                e.setCancelled(true);
            }
        }
    }
}
