package com.iridium.iridiumskyblock.configs;

import com.iridium.iridiumskyblock.XMaterial;

import java.util.HashMap;
import java.util.Map;

public class BlockValues {
    public Map<XMaterial, Double> blockvalue = new HashMap<XMaterial, Double>() {{
        put(XMaterial.EMERALD_BLOCK, 20.00);
        put(XMaterial.DIAMOND_BLOCK, 10.00);
        put(XMaterial.GOLD_BLOCK, 5.00);
        put(XMaterial.IRON_BLOCK, 3.00);
        put(XMaterial.HOPPER, 10.00);
        put(XMaterial.BEACON, 100.00);
    }};
    public Map<String, Double> spawnervalue = new HashMap<String, Double>() {{
        put("PIG", 100.00);
        put("IRON_GOLEM", 1000.00);
    }};
}
