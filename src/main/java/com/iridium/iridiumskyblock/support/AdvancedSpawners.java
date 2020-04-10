package com.iridium.iridiumskyblock.support;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import gcspawners.ASAPI;
import org.bukkit.block.CreatureSpawner;

public class AdvancedSpawners {

    public static boolean enabled = false;

    public AdvancedSpawners() {
        IridiumSkyblock.getInstance().getLogger().info("AdvancedSpawners support loaded");
        enabled = true;
    }

    public static int getSpawnerAmount(CreatureSpawner spawner) {
        return ASAPI.getSpawnerAmount(spawner.getBlock());
    }
}
