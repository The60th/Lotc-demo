package events;

import org.bukkit.Material;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hoes {
    //Valid hoes
    public static ArrayList<Material> nicksHoes = new ArrayList<>(Arrays.asList(Material.DIAMOND_HOE, Material.WOODEN_HOE,
            Material.IRON_HOE, Material.GOLDEN_HOE, Material.NETHERITE_HOE, Material.STONE_HOE));

    //Hoe to drop amount mappings.
    public static Map<Material, Integer> hoeMappings = new HashMap<Material, Integer>() {{
        put(Material.WOODEN_HOE, 1);
        put(Material.STONE_HOE, 1);
        put(Material.IRON_HOE, 2);
        put(Material.GOLDEN_HOE, 2);
        put(Material.DIAMOND_HOE, 3);
        put(Material.NETHERITE_HOE, 3);
    }};

    //Drop mappings
    public static Map<Material, Material> dropMappings = new HashMap<Material, Material>() {{
        put(Material.WHEAT, Material.WHEAT);
        put(Material.CARROTS, Material.CARROT);
        put(Material.POTATOES, Material.POTATO);
        put(Material.BEETROOTS, Material.BEETROOT);
    }};

    //seed drop mappings
    public static Map<Material, Material> seedMappings = new HashMap<Material, Material>() {{
        put(Material.WHEAT, Material.WHEAT_SEEDS);
        put(Material.CARROTS, Material.CARROT);
        put(Material.POTATOES, Material.POTATO);
        put(Material.BEETROOTS, Material.BEETROOT_SEEDS);
    }};

}
