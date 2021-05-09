package commands;

import events.InventoryClick;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static events.InventoryClick.removeTeams;

public class WeaponsKit implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player && s.toLowerCase().equals("kit")){
            Player player = (Player) commandSender;
            if(args.length == 0){
                //player.sendMessage(ChatColor.RED + "" +ChatColor.BOLD + "Wrong command");
                Inventory inv =  Bukkit.createInventory(null,54,ChatColor.ITALIC + "" + ChatColor.DARK_GREEN + "Kit Selector");
                builtInv(inv);
                player.openInventory(inv);
                return true;
            }

            switch (args[0].toLowerCase()){
                case "weapons":
                    giveWeapons(player);
                    return true;
                case "armour":
                    giveSurgePlate(player);
                case "random":
                    int x =1;
                    for(Player online : Bukkit.getOnlinePlayers()){ ;
                        if(x%2 == 0){
                            removeTeams(online);
                            Main.blueTeam.addPlayer(online);
                        }else{
                            removeTeams(online);
                            Main.redTeam.addPlayer(online);
                        }
                        x++;
                    }
                default:
                    return true;
            }
        }
        return false;
    }

    public void populateInv(Inventory inv){
        inv.addItem(createGuiItem(Material.DIAMOND_AXE, "ZZZZZ", "LORE1", "LORE2"));
        inv.addItem(createGuiItem(Material.DIAMOND_AXE, "ZZZZZ", "LORE1", "LORE2"));
        inv.addItem(createGuiItem(Material.DIAMOND_AXE, "ZZZZZ", "LORE1", "LORE2"));
        inv.addItem(createGuiItem(Material.DIAMOND_AXE, "ZZZZZ", "LORE1", "LORE2"));

    }
    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
    public static void giveWeapons(Player player){
        ArrayList<String> itemLore = new ArrayList<>();


        //Long sword
        ItemStack longSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta = longSword.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed",
                -2.5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),"generic.attackDamage",
                7.2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.setDisplayName(ChatColor.AQUA + "Long sword");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Type:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Slashing: +10");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        longSword.setItemMeta(itemMeta);
        player.getInventory().addItem(longSword);

        //Short sword
        ItemStack shortSword = new ItemStack(Material.IRON_SWORD);
        itemMeta = shortSword.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed",
                -1.9, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),"generic.attackDamage",
                4.8, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.setDisplayName(ChatColor.AQUA + "Short sword");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Type:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Slashing: +10");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        shortSword.setItemMeta(itemMeta);
        player.getInventory().addItem(shortSword);

        //Great sword
        ItemStack greatSword = new ItemStack(Material.IRON_SWORD);
        itemMeta = greatSword.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed",
                -3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),"generic.attackDamage",
                8.4, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.setDisplayName(ChatColor.AQUA + "Great Sword");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Type:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Slashing: +10");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        greatSword.setItemMeta(itemMeta);
        player.getInventory().addItem(greatSword);

        //Warhammer
        ItemStack warHammer = new ItemStack(Material.IRON_PICKAXE);
        itemMeta = warHammer.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed",
                -3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),"generic.attackDamage",
                8.4, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.setDisplayName(ChatColor.AQUA + "Warhammer");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Type:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Blunt: +10");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        warHammer.setItemMeta(itemMeta);
        player.getInventory().addItem(warHammer);

        //war axe
        ItemStack warAxe = new ItemStack(Material.IRON_AXE);
        itemMeta = warAxe.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed",
                -2.25, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),"generic.attackDamage",
                6, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.setDisplayName(ChatColor.AQUA + "War Axe");
        itemMeta.setUnbreakable(true);
        warAxe.setItemMeta(itemMeta);
        player.getInventory().addItem(warAxe);

        //Mace
        ItemStack mace = new ItemStack(Material.IRON_SHOVEL);
        itemMeta = mace.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed",
                -2.5, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(),"generic.attackDamage",
                6, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HAND));
        itemMeta.setDisplayName(ChatColor.AQUA + "War Mace");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Type:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Blunt: +10");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        mace.setItemMeta(itemMeta);
        player.getInventory().addItem(mace);


    }

    public static void giveSurgePlate(Player player){
        ItemStack helm = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta = helm.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HEAD));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HEAD));
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Slashing: -2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Blunt: +2");
        itemMeta.setLore(itemLore);
        itemMeta.setDisplayName(ChatColor.AQUA + "Plate Helmet");
        itemMeta.setUnbreakable(true);
        helm.setItemMeta(itemMeta);
        player.getInventory().addItem(helm);

        ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
        itemMeta = chest.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.CHEST));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                8, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.CHEST));
        itemMeta.setDisplayName(ChatColor.AQUA + "Plate Chestplate");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Slashing: -2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Blunt: +2");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        chest.setItemMeta(itemMeta);
        player.getInventory().addItem(chest);

        ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
        itemMeta = legs.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.LEGS));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                6, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.LEGS));
        itemMeta.setDisplayName(ChatColor.AQUA + "Plate Leggings");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC + "Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Slashing: -2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Blunt: +2");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        legs.setItemMeta(itemMeta);
        player.getInventory().addItem(legs);

        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        itemMeta = boots.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.FEET));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.FEET));
        itemMeta.setDisplayName(ChatColor.AQUA + "Plate Boots");
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC + "Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Slashing: -2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Blunt: +2");
        itemMeta.setLore(itemLore);
        itemMeta.setUnbreakable(true);
        boots.setItemMeta(itemMeta);
        player.getInventory().addItem(boots);
    }

    public static void giveFood(Player player){
        ItemStack beef = new ItemStack(Material.COOKED_BEEF,64);
        ItemMeta itemMeta = beef.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Beef");
        beef.setItemMeta(itemMeta);
        player.getInventory().addItem(beef);
    }

    public static void giveRanged(Player player){
        ItemStack crossBow = new ItemStack(Material.CROSSBOW);
        ItemMeta itemMeta = crossBow.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Surge Crosbow");
        itemMeta.setUnbreakable(true);
        crossBow.setItemMeta(itemMeta);
        player.getInventory().addItem(crossBow);

        ItemStack bow = new ItemStack(Material.BOW);
        itemMeta = bow.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Surge Bow");
        itemMeta.setUnbreakable(true);
        bow.setItemMeta(itemMeta);
        player.getInventory().addItem(bow);

        ItemStack arrows = new ItemStack(Material.ARROW,64);
         itemMeta = arrows.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Surge Arrows");
        arrows.setItemMeta(itemMeta);
        player.getInventory().addItem(arrows);
        player.getInventory().addItem(arrows);
    }

    public static void giveSurgeChain(Player player){
        ItemStack chainHelm = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta itemMeta = chainHelm.getItemMeta();
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Slashing: +2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Blunt: -2");
        itemMeta.setLore(itemLore);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HEAD));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.HEAD));
        itemMeta.setDisplayName(ChatColor.AQUA + "Chain Helm");
        itemMeta.setUnbreakable(true);
        chainHelm.setItemMeta(itemMeta);
        player.getInventory().addItem(chainHelm);

        ItemStack chainChest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        itemMeta = chainChest.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.CHEST));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                8, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.CHEST));
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Slashing: +2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Blunt: -2");
        itemMeta.setLore(itemLore);
        itemMeta.setDisplayName(ChatColor.AQUA + "Chain Chestplate");
        itemMeta.setUnbreakable(true);
        chainChest.setItemMeta(itemMeta);
        player.getInventory().addItem(chainChest);

        ItemStack chainLegs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        itemMeta = chainLegs.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.LEGS));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                6, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.LEGS));
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Slashing: +2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Blunt: -2");
        itemMeta.setLore(itemLore);
        itemMeta.setDisplayName(ChatColor.AQUA + "Chain Legs");
        itemMeta.setUnbreakable(true);
        chainLegs.setItemMeta(itemMeta);
        player.getInventory().addItem(chainLegs);

        ItemStack chainBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
        itemMeta = chainBoots.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(),"generic.armourToughness",
                2, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.FEET));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"generic.armour",
                3, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlot.FEET));
        itemLore = new ArrayList<>();
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.GOLD+" Slashing: +2");
        itemLore.add(ChatColor.LIGHT_PURPLE +""+ChatColor.ITALIC +"Armor:"+ ChatColor.BOLD + "" + ChatColor.RED+" Blunt: -2");
        itemMeta.setLore(itemLore);
        itemMeta.setDisplayName(ChatColor.AQUA + "Chain Boots");
        itemMeta.setUnbreakable(true);
        chainBoots.setItemMeta(itemMeta);
        player.getInventory().addItem(chainBoots);



    }
    public void builtInv(Inventory inv){
        for(int i = 0; i< 54; i++) {
            if(i % 2 == 0){
                inv.setItem(i, createGuiItem(Material.GREEN_STAINED_GLASS_PANE,ChatColor.DARK_GREEN + "X"));
            }else{
                inv.setItem(i, createGuiItem(Material.YELLOW_STAINED_GLASS_PANE,ChatColor.YELLOW+"X"));
            }
            //22 chest plate
            //30,31,32 //40
        }

        inv.setItem(10,createGuiItem(Material.PURPLE_WOOL, ChatColor.DARK_PURPLE+"Purple Team"));
        inv.setItem(16,createGuiItem(Material.BLUE_WOOL, ChatColor.DARK_BLUE+"Blue Team"));
        inv.setItem(43,createGuiItem(Material.GREEN_WOOL, ChatColor.DARK_GREEN+"Green Team"));
        inv.setItem(37,createGuiItem(Material.RED_WOOL, ChatColor.DARK_RED + "Red Team"));
        inv.setItem(49, createGuiItem(Material.BLACK_WOOL, ChatColor.DARK_GRAY + "Clear Team"));

        inv.setItem(22,createGuiItem(Material.IRON_CHESTPLATE, ChatColor.GOLD+"Armour Kit"));
        inv.setItem(30,createGuiItem(Material.IRON_SWORD, ChatColor.GOLD+"Weapons Kit"));
        inv.setItem(31,createGuiItem(Material.COOKED_BEEF, ChatColor.GOLD+"Food Kit"));
        inv.setItem(32,createGuiItem(Material.CROSSBOW,ChatColor.GOLD +"Ranged Kit"));
    }
}
