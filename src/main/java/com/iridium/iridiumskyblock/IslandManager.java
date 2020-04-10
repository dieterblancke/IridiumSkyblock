package com.iridium.iridiumskyblock;

import com.iridium.iridiumskyblock.configs.Config;
import com.iridium.iridiumskyblock.configs.Schematics;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class IslandManager {

    public Map<Integer, Island> islands = new HashMap<>();
    public Map<String, User> users = new HashMap<>();

    public transient Map<Integer, List<Integer>> islandCache;

    int length = 1;
    int current = 0;

    public Direction direction = Direction.NORTH;
    public Location nextLocation;

    public int nextID = 1;

    public IslandManager() {
        makeWorld();
        nextLocation = new Location(getWorld(), 0, 0, 0);
    }

    public World getWorld() {
        return Bukkit.getWorld(IridiumSkyblock.getConfiguration().worldName);
    }

    public World getNetherWorld() {
        return Bukkit.getWorld(IridiumSkyblock.getConfiguration().worldName + "_nether");
    }

    public Island createIsland(Player player) {
        User user = User.getUser(player);
        if (user.lastCreate != null && new Date().before(user.lastCreate) && IridiumSkyblock.getConfiguration().createCooldown && !user.bypassing) {
            //The user cannot create an island
            long time = (user.lastCreate.getTime() - System.currentTimeMillis()) / 1000;
            int day = (int) TimeUnit.SECONDS.toDays(time);
            int hours = (int) Math.floor(TimeUnit.SECONDS.toHours(time - day * 86400));
            int minute = (int) Math.floor((time - day * 86400 - hours * 3600) / 60.00);
            int second = (int) Math.floor((time - day * 86400 - hours * 3600) % 60.00);
            player.sendMessage(Utils.color(IridiumSkyblock.getMessages().createCooldown.replace("%days%", day + "").replace("%hours%", hours + "").replace("%minutes%", minute + "").replace("%seconds%", second + "").replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, IridiumSkyblock.getConfiguration().regenCooldown);
        user.lastCreate = c.getTime();

        Location pos1 = nextLocation.clone().subtract(IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(1).size / 2.00, 0, IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(1).size / 2.00);
        Location pos2 = nextLocation.clone().add(IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(1).size / 2.00, 0, IridiumSkyblock.getUpgrades().sizeUpgrade.upgrades.get(1).size / 2.00);
        Location center = nextLocation.clone().add(0, 100, 0);
        Location home = nextLocation.clone();

        Location netherhome = home.clone();

        if (IridiumSkyblock.getConfiguration().netherIslands) {
            netherhome.setWorld(IridiumSkyblock.getIslandManager().getNetherWorld());
        }
        Island island = new Island(player, pos1, pos2, center, home, netherhome, nextID);
        islands.put(nextID, island);

        user.islandID = nextID;
        user.role = Role.Owner;

        if (IridiumSkyblock.getInstance().schems.size() == 1) {
            for (Schematics.FakeSchematic schematic : IridiumSkyblock.getInstance().schems.keySet()) {
                island.setSchematic(schematic.name);
                island.setHome(island.getHome().add(schematic.x, schematic.y, schematic.z));
                island.setNetherhome(island.getNetherhome().add(schematic.x, schematic.y, schematic.z));
            }
            island.pasteSchematic(player, false);
        } else {
            player.openInventory(island.getSchematicSelectGUI().getInventory());
        }

        switch (direction) {
            case NORTH:
                nextLocation.add(IridiumSkyblock.getConfiguration().distance, 0, 0);
                break;
            case EAST:
                nextLocation.add(0, 0, IridiumSkyblock.getConfiguration().distance);
                break;
            case SOUTH:
                nextLocation.subtract(IridiumSkyblock.getConfiguration().distance, 0, 0);
                break;
            case WEST:
                nextLocation.subtract(0, 0, IridiumSkyblock.getConfiguration().distance);
                break;
        }

        current++;

        if (current == length) {
            current = 0;
            direction = direction.next();
            if (direction == Direction.SOUTH || direction == Direction.NORTH) {
                length++;
            }
        }

        IridiumSkyblock.getInstance().saveConfigs();

        nextID++;

        return island;
    }

    private void makeWorld() {
        makeWorld(Environment.NORMAL, IridiumSkyblock.getConfiguration().worldName);
        if (IridiumSkyblock.getConfiguration().netherIslands)
            makeWorld(Environment.NETHER, IridiumSkyblock.getConfiguration().worldName + "_nether");
    }

    private void makeWorld(Environment env, String name) {
        WorldCreator wc = new WorldCreator(name);
        wc.type(WorldType.FLAT);
        wc.generateStructures(false);
        wc.generator(new SkyblockGenerator());
        wc.environment(env);
        wc.createWorld();
    }

    public Island getIslandViaLocation(Location loc) {
        if (islandCache == null) islandCache = new HashMap<>();
        if (loc == null) return null;
        int hash = loc.getChunk().hashCode();
        if (islandCache.containsKey(hash)) {
            for (int id : islandCache.get(hash)) {
                if (getIslandViaId(id).isInIsland(loc)) {
                    return getIslandViaId(id);
                }
            }
        }
        if (loc.getWorld().equals(getWorld()) || loc.getWorld().equals(getNetherWorld())) {
            for (Island island : islands.values()) {
                if (island.isInIsland(loc)) {
                    if (islandCache.containsKey(hash)) {
                        islandCache.get(hash).add(island.getId());
                    } else {
                        islandCache.put(hash, new ArrayList<>(island.getId()));
                    }
                    return island;
                }
            }
        }
        return null;
    }

    public Island getIslandViaId(int i) {
        return islands.get(i);
    }

    public boolean isIslandWorld(World world) {
        if (world == null) return false;
        final String name = world.getName();
        final Config config = IridiumSkyblock.getConfiguration();
        return (name.equals(config.worldName) || name.equals(config.worldName + "_nether"));
    }
}
