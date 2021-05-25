package main;

import commands.ToggleCommand;
import commands.WeaponsKit;
import events.*;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static Plugin plugin;
    public static JavaPlugin javaPlugin;
    public static Logger logger;
    private static ArrayList<Player> trampleToggle = new ArrayList<>();
    public static void addToggle(Player player){
        trampleToggle.remove(player);
    }
    public static void removeToggle(Player player){
        trampleToggle.add(player);
    }
    public static boolean checkToggle(Player player){
        return trampleToggle.contains(player);
    }
    //Toggle the players status within the list.
    //Return false if the player is added, return true if the player is removed.
    public static boolean toggle(Player player){
        if(trampleToggle.contains(player)){
            trampleToggle.remove(player);
            return true;
        }else{
            trampleToggle.add(player);
            return false;
        }
    }
    public void onEnable(){
        Main.logger = Logger.getLogger("Minecraft");
        Main.plugin = (Plugin)this;
        this.registerCommands();
        this.registerEvents();
    }


    public void onDisable() {
        final PluginDescriptionFile pdfFile = this.getDescription();
        final Logger logger = Logger.getLogger("Minecraft");
        logger.info(pdfFile.getName() + "has successfully disabled.");
    }

    public static Plugin getPlugin() {
        return Main.plugin;
    }


    public void registerEvents() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Harvest(), this);
        pm.registerEvents(new Trample(),this);
        pm.registerEvents(new PistonEvent(), this);
        pm.registerEvents(new WaterEvent(), this);
    }


    public void registerCommands() {
     this.getCommand("toggle").setExecutor(new ToggleCommand());

    }
}
