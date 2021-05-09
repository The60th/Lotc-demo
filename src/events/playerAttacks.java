package events;

import main.Main;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Objects;

public class playerAttacks implements Listener {
    public static ArrayList<Player> stuckPlayers = new ArrayList<>();
    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent event) {
        Entity eattacker = event.getDamager(); //Attacker
        Entity edefender = event.getEntity();

        if (!(eattacker instanceof Player) || !(edefender instanceof Player)) return;



        Player attacker = (Player) eattacker;
        final Player defender = (Player) edefender;
        if(stuckPlayers.contains(defender)){
            final Vector vec = new org.bukkit.util.Vector();
            defender.setVelocity(vec);

            new BukkitRunnable() {
                public void run() {
                    defender.setVelocity(vec);
                }
            }.runTaskLater(Main.getPlugin(), 1l);
        }



        ItemStack item = ((Player) attacker).getItemInHand();
        if (!item.hasItemMeta()) return;
        if (!checkType(item)) return;
        AttributeModifier modifer = (AttributeModifier) Objects.requireNonNull(Objects.requireNonNull(item.getItemMeta()).getAttributeModifiers()).get(Attribute.GENERIC_ATTACK_DAMAGE).toArray()[0];
        //attacker.sendMessage("test:" + modifer.getAmount());
        Material mat = item.getType();
        // attacker.sendMessage("Event damage: " + event.getDamage());
        //TODO replicating vortex bug that causes weapons to do any extra half heart of damage.
        //attacker.sendMessage("if check: " + event.getDamage() +" :: " + (modifer.getAmount()+1-2));
        if (!((event.getDamage()) > (modifer.getAmount() + 1) - .75)) return;
        //attacker.sendMessage("Passes full swing check");

        //Get armour values

        switch (mat) {
            case DIAMOND_AXE:
                //?
                break;
            case IRON_SWORD:
                //Bleed
                int swordProcRate = 10;
                int armourValues = getArmourValues(defender, "Slashing: ");

                if (procHit(swordProcRate,armourValues)) {
                    ((Player) defender).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 25 * 6, 0));
                    ((Player) defender).playSound(defender.getLocation(), Sound.BLOCK_BELL_USE, 3.0f, 2.0f);
                    ((Player) attacker).playSound(attacker.getLocation(), Sound.BLOCK_BELL_USE, 3.0f, 2.0f);

                    defender.sendMessage(ChatColor.RED + "Your are now bleeding");
                    attacker.sendMessage(ChatColor.RED + "Your blows caused your opponent to  start bleeding");
                }
                break;
            case IRON_SHOVEL:
            case IRON_PICKAXE:
                armourValues = getArmourValues(defender, "Blunt: ");
                int stunProcRate = 10;
                if (procHit(stunProcRate,armourValues)) {
                    stuckPlayers.add(defender);
                    new BukkitRunnable() {
                        public void run() {
                            stuckPlayers.remove(defender);
                        }
                    }.runTaskLater(Main.getPlugin(), 60l);
                    ((Player) defender).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 90));
                    ((Player) defender).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 3, 128));

                    ((Player) defender).playSound(defender.getLocation(), Sound.BLOCK_ANVIL_PLACE, 3.0f, 2.0f);
                    ((Player) attacker).playSound(attacker.getLocation(), Sound.BLOCK_ANVIL_PLACE, 3.0f, 2.0f);
                    defender.sendMessage(ChatColor.RED + "You were dazed.");
                    attacker.sendMessage(ChatColor.RED + "Your blows dazed your opponent");
                }
                //Stun.
                break;
            default:

        }

    }

    public int getArmourValues(Player player, String attackType) {
        int bluntTotal = 0;
        int slashTotal = 0;
        PlayerInventory pInv = player.getInventory();
        for (ItemStack item : pInv.getArmorContents()) {
            if (item != null) {
                if (item.hasItemMeta()) {
                    if (item.getItemMeta().hasLore()) {
                        for (String lore : item.getItemMeta().getLore()) {
                            //Type: Blunt: -2
                          //  player.sendMessage("Lore:" + lore);
                            lore = ChatColor.stripColor(lore);
                            String sub = lore.substring(lore.indexOf("Armor: ") + 7);
                         //   player.sendMessage("Sub:"+sub);
                            //Blunt: -2
                            if (sub.contains("Blunt: ")) {
                                String sub1 = sub.substring(sub.indexOf("Blunt: +") + 8);
                              //  player.sendMessage("blunt:" + sub1);
                                bluntTotal = bluntTotal + Integer.parseInt(sub1);
                            } else if (sub.contains("Slashing: ")) {
                                String sub1 = sub.substring(sub.indexOf("Slashing: +") + 11);
                               // player.sendMessage("slashing:" + sub1);
                                slashTotal = slashTotal + Integer.parseInt(sub1);
                            }
                        }
                    }
                }
            }
        }
       // player.sendMessage("slash total: " + slashTotal);
      //  player.sendMessage("blunt total: " + bluntTotal);

        if (attackType.equalsIgnoreCase("Blunt: ")) return slashTotal;
        else return bluntTotal;
    }

    private boolean checkType(ItemStack item) {
        Material mat = item.getType();
        if (mat == Material.IRON_SWORD || mat == Material.IRON_AXE || mat == Material.IRON_PICKAXE || mat == Material.IRON_SHOVEL)
            return true;
        else return false;
    }

    private boolean procHit(int procChance, int armourValues) {
        procChance = procChance+armourValues;
       // Bukkit.broadcastMessage("Proc chance: " + procChance);
        if(procChance < 2) procChance = 2;
        int x = Main.random.nextInt(100);
       // Bukkit.broadcastMessage("Proc: " + x);
        return x <= procChance;
    }


}
