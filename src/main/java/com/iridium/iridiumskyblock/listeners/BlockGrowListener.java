package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.XBlock;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.material.Crops;

public class BlockGrowListener implements Listener {

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        try {
            final Block block = event.getBlock();
            final Location location = block.getLocation();

            final Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(location);
            if (island == null) return;
            if (island.getFarmingBooster() == 0) return;

            final Material material = block.getType();
            if (!XBlock.isCrops(material)) return;

            event.setCancelled(true);

            final Crops crops = new Crops(CropState.RIPE);
            final BlockState blockState = block.getState();
            blockState.setData(crops);
            blockState.update();
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }
}
