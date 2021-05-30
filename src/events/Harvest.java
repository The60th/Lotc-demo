package events;

import main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Harvest implements Listener {
    private Main main;
    private Hoes hoes = new Hoes();

    public Harvest(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        //Check for right click block event, also check for ageable instance otherwise we ignore.
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getBlockData() instanceof Ageable) {
            World world = player.getWorld();
            Location location = player.getLocation();
            //Pull hoes util list of valid items.
            if (hoes.nicksHoes.contains(player.getItemInHand().getType())) {
                //Valid hoe
                ItemStack itemInHand = event.getPlayer().getItemInHand();
                // System.out.println("Valid Hoe: " + itemInHand.getType());
                //Check the clicked block, this function is not needed as above handled.
                if (event.getClickedBlock().getBlockData() instanceof Ageable) {
                    Ageable blockData = (Ageable) event.getClickedBlock().getBlockData();
                    //  System.out.println("Valid Block: " + event.getClickedBlock().getType());

                    Material[] itemsToDrop = new Material[2];
                    boolean fullGrowth = false;
                    //Get crop to seed and item drop mappings from Hoes utils.
                    if (blockData.getAge() == blockData.getMaximumAge()) {
                        itemsToDrop[0] = hoes.dropMappings.get(blockData.getMaterial());
                        itemsToDrop[1] = hoes.seedMappings.get(blockData.getMaterial());
                        fullGrowth = true;
                    } else {
                        itemsToDrop[0] = hoes.seedMappings.get(blockData.getMaterial());
                    }
                    //For each item that we are dropping check its location and generate an item stack to drop it at
                    for (Material item : itemsToDrop) {
                        if (item != null) {
                            Location blockLocation = event.getClickedBlock().getLocation();
                            //System.out.println("Drop test!");
                            ItemStack itemStack = new ItemStack(item);
                            if (fullGrowth) {
                                //   System.out.println("Full growth");
                                //Get the level of fortune and modify drops
                                int amount = hoes.hoeMappings.get(player.getItemInHand().getType());
                                int fortuneModifer = itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                                System.out.println(itemStack);
                                amount = amount + fortuneModifer;
                                itemStack.setAmount(amount);
                            } else {
                                //If not fully grown just drop one seed.
                                itemStack.setAmount(1);
                            }
                            //System.out.println("stacj2" + itemStack);
                            //Drop the item to the player
                            blockLocation.getWorld().dropItemNaturally(blockLocation, itemStack);
                        }
                    }
                    //Get unbreaking modifer
                    int unbreakingModifer = itemInHand.getEnchantmentLevel(Enchantment.DURABILITY);
                    if (unbreakingModifer > 0) {
                        // System.out.println("Unbreaking modifer? " + unbreakingModifer);
                        //Use default minecraft unbreaking calculation of 60+40/(level+1)% according to wiki.
                        int chance = 60 + (40) / (unbreakingModifer + 1);
                        Random random = new Random();

                        if (random.nextInt(100) > chance) {
                            //    System.out.println("Unbreaking prevented damage.");
                        } else {
                            // System.out.println("Triggered");
                            //Increase the item damage by 1 which lowers the durability by 1
                            itemInHand.setDurability((short) (itemInHand.getDurability() + 1));
                            player.setItemInHand(itemInHand);
                        }

                    }
                    //Replace the block with air
                    event.getClickedBlock().setType(Material.AIR);
                    return;
                }
            }
        }
    }

    //Block break event handling.
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        //System.out.println("Break event");
        if (event.getBlock().getBlockData() instanceof Ageable) {
            //System.out.println("ageable");
            if (hoes.nicksHoes.contains(event.getPlayer().getItemInHand().getType())) {
                //System.out.println("Valid hoe.");
                return;
            }
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            event.getPlayer().sendMessage("You need a hoe to harvest crops");
        }
    }


    private class Hoes {
        public Hoes() {

        }

        //Valid hoes
        protected ArrayList<Material> nicksHoes = new ArrayList<>(Arrays.asList(Material.DIAMOND_HOE, Material.WOODEN_HOE,
                Material.IRON_HOE, Material.GOLDEN_HOE, Material.NETHERITE_HOE, Material.STONE_HOE));

        //Hoe to drop amount mappings.
        private Map<Material, Integer> hoeMappings = new HashMap<Material, Integer>() {{
            put(Material.WOODEN_HOE, 1);
            put(Material.STONE_HOE, 1);
            put(Material.IRON_HOE, 2);
            put(Material.GOLDEN_HOE, 2);
            put(Material.DIAMOND_HOE, 3);
            put(Material.NETHERITE_HOE, 3);
        }};

        //Drop mappings
        private Map<Material, Material> dropMappings = new HashMap<Material, Material>() {{
            put(Material.WHEAT, Material.WHEAT);
            put(Material.CARROTS, Material.CARROT);
            put(Material.POTATOES, Material.POTATO);
            put(Material.BEETROOTS, Material.BEETROOT);
        }};

        //seed drop mappings
        private Map<Material, Material> seedMappings = new HashMap<Material, Material>() {{
            put(Material.WHEAT, Material.WHEAT_SEEDS);
            put(Material.CARROTS, Material.CARROT);
            put(Material.POTATOES, Material.POTATO);
            put(Material.BEETROOTS, Material.BEETROOT_SEEDS);
        }};

    }
}
