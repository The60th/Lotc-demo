package commands;

import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static events.InventoryClick.removeTeams;

public class ToggleCommand implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player && label.equalsIgnoreCase("toggle")) {
            Player sender = (Player) commandSender;
            //If the player has no args inform them
            if (args.length == 0) {
                sender.sendMessage("Please check command usage.");
            }
            //Command is toggle.
            switch (args[0].toLowerCase()) {
                case "trample":
                    //If the args is trample toggle the mode using Main.toggle
                    sender.sendMessage("You have swapped your trample status to: " + Main.toggle(sender.getUniqueId()));
                    //Trample case
                    return true;
                case "forcetrample":
                    //If the args is forcetrample check for the pex node then pass onto the next player.
                    if (commandSender.hasPermission("group." + "forcetrample")) {
                        if (args.length < 2) {
                            //Need arg. Failed
                            sender.sendMessage("You do not have permission for this command");
                        } else {
                            //Get the player
                            String name = args[1];
                            //Name of player
                            Player target = Bukkit.getPlayer(name);
                            if (target != null) {
                                //Check that we can resolve the target player.
                                boolean bool = Main.toggle(target.getUniqueId());
                                sender.sendMessage("You have set " + name + " trample status to: " + bool);
                                target.sendMessage("A moderator has set your trample status to: " + bool);
                                return true;
                            } else {
                                sender.sendMessage("Failed to resolve player of: " + name);
                            }
                        }
                    } else {
                        //Command failed no perm.
                        return false;
                    }
            }
        }
        return false;
    }
}
