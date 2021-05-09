package events;

import commands.WeaponsKit;
import main.Main;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!event.getView().getTitle().equals(ChatColor.ITALIC + "" + ChatColor.DARK_GREEN + "Kit Selector")) return;
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
      //  player.sendMessage("Clicked: " + event.getRawSlot());

        switch (event.getRawSlot()){
            case 10:
                //Purple team
                player.sendMessage(ChatColor.DARK_PURPLE + "Set team to purple.");
                removeTeams(player);
                Main.purpleTeam.addPlayer(player);
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 16:
                //Blue team
                player.sendMessage(ChatColor.DARK_BLUE + "Set team to blue.");
                removeTeams(player);
                Main.blueTeam.addPlayer(player);
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 22:
                //Kit armour
                WeaponsKit.giveSurgePlate(player);
                WeaponsKit.giveSurgeChain(player);
                player.sendMessage(ChatColor.RED + "Received kit armour.");
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 30:
                //Kit weapons
                WeaponsKit.giveWeapons(player);
                player.sendMessage(ChatColor.RED + "Received kit weapons.");
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 31:
                //Kit food
                player.sendMessage(ChatColor.RED + "Received kit food.");
                WeaponsKit.giveFood(player);
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 32:
                //Kit ranged
                player.sendMessage(ChatColor.RED + "Received kit ranged.");
                WeaponsKit.giveRanged(player);
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 37:
                //Red team
                player.sendMessage(ChatColor.RED + "Set team to red.");
                removeTeams(player);
                Main.redTeam.addPlayer(player);
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 43:
                //Green team
                player.sendMessage(ChatColor.DARK_GREEN + "Set team to green.");
                removeTeams(player);
                Main.greenTeam.addPlayer(player);
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
            case 49:
                //Clear team.
                player.sendMessage(ChatColor.GOLD + "Cleared team.");
                removeTeams(player);
                Main.clearTeam.addPlayer(player);
                ((Player) player).playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 3.0f, 2.0f);
                break;
        }
    }

    public static void removeTeams(Player player){
        if(Main.redTeam.hasPlayer(player)) Main.redTeam.removePlayer(player);
        if(Main.blueTeam.hasPlayer(player)) Main.blueTeam.removePlayer(player);
        if(Main.greenTeam.hasPlayer(player)) Main.greenTeam.removePlayer(player);
        if(Main.purpleTeam.hasPlayer(player)) Main.purpleTeam.removePlayer(player);
        if(Main.clearTeam.hasPlayer(player)) Main.clearTeam.removePlayer(player);
        player.setScoreboard(Main.scoreboard);

    }
}
