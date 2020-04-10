package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.IslandManager;
import com.iridium.iridiumskyblock.User;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class EntityPickupItemListener implements Listener {

    @EventHandler
    public void onEntityPickupItem(PlayerPickupItemEvent event) {
        try {
            final Item item = event.getItem();
            final Location location = item.getLocation();
            final IslandManager islandManager = IridiumSkyblock.getIslandManager();
            final Island island = islandManager.getIslandViaLocation(location);
            if (island == null) return;

            final Player player = event.getPlayer();
            final User user = User.getUser(player);
            if (!(island.getPermissions(user).pickupItems || user.bypassing))
                event.setCancelled(true);
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }
}
