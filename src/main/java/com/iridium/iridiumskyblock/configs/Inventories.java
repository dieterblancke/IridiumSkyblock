package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.XMaterial;

import java.util.*;

public class Inventories {


    public String upgradeGUITitle = "&7Upgrade";
    public String boosterGUITitle = "&7Booster";
    public String confirmationGUITitle = "&7Are you sure?";
    public String missionsGUITitle = "&7Missions";
    public String membersGUITitle = "&7Members";
    public String coopGUITitle = "&7Co-op Islands";
    public String islandMenuGUITitle = "&7Menu";
    public String warpGUITitle = "&7Warps";
    public String topGUITitle = "&7Top Islands";
    public String borderColorGUITitle = "&7Border Color";
    public String permissionsGUITitle = "&7Permissions";
    public String schematicselectGUITitle = "&7Select an Island";
    public String bankGUITitle = "&7Island Bank";
    public String visitGUITitle = "&7Visit an Island";
    public String shopGUITitle = "&7Island Shop";
    public String biomeGUITitle = "&7Island Biome";

    public int upgradeGUISize = 27;
    public int boosterGUISize = 27;
    public int missionsGUISize = 27;
    public int membersGUISize = 27;
    public int coopGUISize = 27;
    public int islandMenuGUISize = 45;
    public int warpGUISize = 27;
    public int topGUISize = 27;
    public int borderColorGUISize = 27;
    public int permissionsGUISize = 27;
    public int schematicselectGUISize = 27;
    public int bankGUISize = 27;
    public int visitGUISize = 54;
    public int shopGUISize = 54;
    public int biomeGUISize = 54;

    //Boosters
    public Item spawner = new Item(XMaterial.SPAWNER, 1, "&b&lIncreased Mobs", Arrays.asList("&7Are your spawners too slow? Buy this", "&7booster and increase spawner rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{spawnerbooster_minutes} minutes and {spawnerbooster_seconds}seconds", "&b&l * &7Booster Cost: &b{spawnerbooster_crystalcost} Crystals and ${spawnerbooster_vaultcost}", "", "&b&l[!] &bRight Click to Purchase this Booster."));
    public Item farming = new Item(XMaterial.WHEAT, 1, "&b&lIncreased Crops", Arrays.asList("&7Are your crops too slow? Buy this", "&7booster and increase crop growth rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{farmingbooster_minutes} minutes and {farmingbooster_seconds}seconds", "&b&l * &7Booster Cost: &b{farmingbooster_crystalcost} Crystals and ${farmingbooster_vaultcost}", "", "&b&l[!] &bRight Click to Purchase this Booster."));
    public Item exp = new Item(XMaterial.EXPERIENCE_BOTTLE, 1, "&b&lIncreased Experience", Arrays.asList("&7Takes too long to get exp? Buy this", "&7booster and exp rates x2.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{expbooster_minutes} minutes and {expbooster_seconds}seconds", "&b&l * &7Booster Cost: &b{expbooster_crystalcost} Crystals and ${expbooster_vaultcost}", "", "&b&l[!] &bRight Click to Purchase this Booster."));
    public Item flight = new Item(XMaterial.FEATHER, 1, "&b&lIncreased Flight", Arrays.asList("&7Tired of falling off your island? Buy this", "&7booster and allow all members to fly.", "", "&b&lInformation:", "&b&l * &7Time Remaining: &b{flightbooster_minutes} minutes and {flightbooster_seconds}seconds", "&b&l * &7Booster Cost: &b{flightbooster_crystalcost} Crystals and ${flightbooster_vaultcost}", "", "&b&l[!] &bRight Click to Purchase this Booster."));
    //Upgrade
    public Item size = new Item(XMaterial.GRASS_BLOCK, 1, "&b&lIsland Size", Arrays.asList("&7Need more room to expand? Buy this", "&7upgrade to increase your island size.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{sizelevel}", "&b&l * &7Current Size: &b{sizeblocks}x{sizeblocks} Blocks", "&b&l * &7Upgrade Cost: &b{sizecrystalscost} Crystals and ${sizevaultcost}", "&b&lLevels:", "&b&l * &7Level 1: &b50x50 Blocks", "&b&l * &7Level 2: &b100x100 Blocks", "&b&l * &7Level 3: &b150x150 Blocks", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));
    public Item member = new Item(XMaterial.ARMOR_STAND, 1, "&b&lMember Count", Arrays.asList("&7Need more members? Buy this", "&7upgrade to increase your member count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{memberlevel}", "&b&l * &7Current Members: &b{membercount} Members", "&b&l * &7Upgrade Cost: &b{membercrystalscost} Crystals and ${membervaultcost}", "&b&lLevels:", "&b&l * &7Level 1: &b9 Members", "&b&l * &7Level 2: &b18 Members", "&b&l * &7Level 3: &b27 Members", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));
    public Item warp = new Item(XMaterial.END_PORTAL_FRAME, 1, "&b&lIsland Warps", Arrays.asList("&7Need more island warps? Buy this", "&7upgrade to increase your warp count.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{warplevel}", "&b&l * &7Current Warps: &b{warpcount} Warps", "&b&l * &7Upgrade Cost: &b{warpcrystalscost} Crystals and ${warpvaultcost}", "&b&lLevels:", "&b&l * &7Level 1: &b2 Warps", "&b&l * &7Level 2: &b5 Warps", "&b&l * &7Level 3: &b9 Warps", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));
    public Item ores = new Item(XMaterial.DIAMOND_ORE, 1, "&b&lIsland Generator", Arrays.asList("&7Want to improve your generator? Buy this", "&7upgrade to increase your island generator.", "", "&b&lInformation:", "&b&l * &7Current Level: &b{oreslevel}", "&b&l * &7Upgrade Cost: &b{orescrystalscost} Crystals and ${oresvaultcost}", "", "&b&l[!] &bLeft Click to Purchase this Upgrade"));

    //Bank
    public Item experience = new Item(XMaterial.EXPERIENCE_BOTTLE, 11, 1, "&b&lIsland Experience", Arrays.asList("&7{experience} Experience", "&b&l[!] &bLeft click to withdraw", "&b&l[!] &bRight click to deposit"));
    public Item crystals = new Item(XMaterial.NETHER_STAR, 13, 1, "&b&lIsland Crystals", Arrays.asList("&7{crystals} Crystals", "&b&l[!] &bLeft click to withdraw", "&b&l[!] &bRight click to deposit"));
    public Item money = new Item(XMaterial.PAPER, 15, 1, "&b&lIsland Money", Arrays.asList("&7${money}", "&b&l[!] &bLeft click to withdraw", "&b&l[!] &bRight click to deposit"));

    public Item crystal = new Item(XMaterial.NETHER_STAR, 1, "&b*** &b&lIsland Crystal &b***", Arrays.asList("", "&b&l[!] &bto Redeem, go to your Island Bank"));

    public Item background = new Item(XMaterial.BLACK_STAINED_GLASS_PANE, 1, " ", new ArrayList<>());

    public Item nextPage = new Item(XMaterial.LIME_STAINED_GLASS_PANE, 1, "&a&lNext Page", new ArrayList<>());

    public Item previousPage = new Item(XMaterial.RED_STAINED_GLASS_PANE, 1, "&c&lPrevious Page", new ArrayList<>());

    public Item biome = new Item(XMaterial.GRASS_BLOCK, 1, "&b&l{biome} Biome", new ArrayList<>());

    public Item back = new Item(XMaterial.NETHER_STAR, 1, "&c&lBack", new ArrayList<>());

    public Item islandmember = new Item(XMaterial.PLAYER_HEAD, 1, "&b&l{player}", "{player}", Arrays.asList("&bRole: {role}", "", "&b&l[!] &bLeft Click to {demote}" + " this Player.", "&b&l[!] &bRight Click to Promote this Player."));
    public Item islandcoop = new Item(XMaterial.PLAYER_HEAD, 1, "&b&l{player}", "{player}", Arrays.asList("&b&l * &7Island: &b{name}", "&b&l * &7Rank: &b{rank}", "&b&l * &7Value: &b{value}", "", "&b&l[!] &bLeft Click to Teleport to this island.", "&b&l[!] &bRight Click to un co-op this island."));
    public Item islandRoles = new Item(XMaterial.RED_STAINED_GLASS_PANE, 1, "&b&l{role}", Collections.emptyList());
    public Item islandPermissionAllow = new Item(XMaterial.LIME_STAINED_GLASS_PANE, 1, "&b&l{permission}", Collections.emptyList());
    public Item islandPermissionDeny = new Item(XMaterial.RED_STAINED_GLASS_PANE, 1, "&b&l{permission}", Collections.emptyList());
    public Item islandWarp = new Item(XMaterial.YELLOW_STAINED_GLASS_PANE, 1, "&b&l{warp}", Arrays.asList("", "&b&l[!] &bLeft Click to Teleport to this warp.", "&b&l[!] &bRight Click to Delete to warp."));
    public Item topisland = new Item(XMaterial.PLAYER_HEAD, 1, "&b&l{player}", "{player}", Arrays.asList("&b&l * &7Island: &b{name}", "", "&b&l * &7Emerald Blocks: &b{EMERALD_BLOCK_amount}", "&b&l * &7Diamond Blocks: &b{DIAMOND_BLOCK_amount}", "&b&l * &7Gold Blocks: &b{GOLD_BLOCK_amount}", "&b&l * &7Iron Blocks: &b{IRON_BLOCK_amount}", "&b&l * &7Hopper Blocks: &b{HOPPER_amount}", "&b&l * &7Beacon Blocks: &b{BEACON_amount}", "", "&b&l * &7Rank: &b{rank}", "&b&l * &7Value: &b{value}", "", "&b&l[!] &bLeft Click to Teleport to this island."));
    public Item visitisland = new Item(XMaterial.PLAYER_HEAD, 1, "&b&l{player}", "{player}", Arrays.asList("&b&l * &7Island: &b{name}", "&b&l * &7Rank: &b{rank}", "&b&l * &7Value: &b{value}", "&b&l * &7Votes: &b{votes}", "", "&b&l[!] &bLeft Click to Teleport to this island.", "&b&l[!] &bRight Click to (un)vote for this island."));


    public Item red = new Item(XMaterial.RED_STAINED_GLASS_PANE, 1, "&c&lRed", new ArrayList<>());
    public Item green = new Item(XMaterial.LIME_STAINED_GLASS_PANE, 1, "&a&lGreen", new ArrayList<>());
    public Item blue = new Item(XMaterial.BLUE_STAINED_GLASS_PANE, 1, "&b&lBlue", new ArrayList<>());
    public Item off = new Item(XMaterial.WHITE_STAINED_GLASS_PANE, 1, "&f&lOff", new ArrayList<>());

    public Map<Item, String> menu = new HashMap<Item, String>() {{
        put(new Item(XMaterial.WHITE_BED, 13, 1, "&b&lIsland Home", Collections.singletonList("&7Teleport to your island home")), "is home");
        put(new Item(XMaterial.PLAYER_HEAD, 14, 1, "&b&lIsland Members", "Peaches_MLG", Collections.singletonList("&7View your island Members.")), "is members");
        put(new Item(XMaterial.GRASS_BLOCK, 36, 1, "&b&lIsland Regen", Collections.singletonList("&7Regenerate your island.")), "is regen");
        put(new Item(XMaterial.PLAYER_HEAD, 21, 1, "&b&lIsland Upgrades", "ABigDwarf", Collections.singletonList("&7Upgrade your island.")), "is upgrade");
        put(new Item(XMaterial.IRON_SWORD, 22, 1, "&b&lIsland Missions", Collections.singletonList("&7View island missions.")), "is missions");
        put(new Item(XMaterial.EXPERIENCE_BOTTLE, 23, 1, "&b&lIsland Boosters", Collections.singletonList("&7Boost your island.")), "is booster");
        put(new Item(XMaterial.BOOK, 31, 1, "&b&lIsland Permissions", Collections.singletonList("&7Change island permissions.")), "is permissions");
        put(new Item(XMaterial.DIAMOND, 0, 1, "&b&lIsland Top", Collections.singletonList("&7View top islands.")), "is top");
        put(new Item(XMaterial.END_PORTAL_FRAME, 20, 1, "&b&lIsland Warps", Collections.singletonList("&7View your island warps.")), "is warps");
        put(new Item(XMaterial.BEACON, 24, 1, "&b&lIsland Border", Collections.singletonList("&7Change your island border.")), "is border");
        put(new Item(XMaterial.NAME_TAG, 32, 1, "&b&lIsland Coop", Collections.singletonList("&7View your Co-op Islands.")), "is coop");
        put(new Item(XMaterial.GOLD_INGOT, 30, 1, "&b&lIsland Bank", Collections.singletonList("&7View your Island Bank.")), "is bank");
        put(new Item(XMaterial.PLAYER_HEAD, 12, 1, "&b&lIsland Biome", "BlockminersTV", Collections.singletonList("&7Change your island biome.")), "is biome");
        put(new Item(XMaterial.BARRIER, 44, 1, "&b&lIsland Delete", Collections.singletonList("&7Delete your island.")), "is delete");
    }};

    public static class Item {

        public XMaterial material;
        public int amount;
        public String title;
        public String headOwner;
        public List<String> lore;
        public Integer slot;

        public Item(XMaterial material, int amount, String title, List<String> lore) {
            this.material = material;
            this.amount = amount;
            this.lore = lore;
            this.title = title;
        }

        public Item(XMaterial material, int slot, int amount, String title, List<String> lore) {
            this.material = material;
            this.amount = amount;
            this.lore = lore;
            this.title = title;
            this.slot = slot;
        }

        public Item(XMaterial material, int slot, int amount, String title, String headOwner, List<String> lore) {
            this.material = material;
            this.amount = amount;
            this.lore = lore;
            this.title = title;
            this.headOwner = headOwner;
            this.slot = slot;
        }

        public Item(XMaterial material, int amount, String title, String headOwner, List<String> lore) {
            this.material = material;
            this.amount = amount;
            this.lore = lore;
            this.title = title;
            this.headOwner = headOwner;
        }
    }
}
