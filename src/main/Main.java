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
import java.util.UUID;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static Logger logger;
    private static ArrayList<UUID> trampleToggle = new ArrayList<>();

    public static void addToggle(UUID player) {
        trampleToggle.remove(player);
    }

    public static void removeToggle(UUID player) {
        trampleToggle.add(player);
    }

    public static boolean checkToggle(UUID player) {
        return trampleToggle.contains(player);
    }

    //Toggle the players status within the list.
    //Return false if the player is added, return true if the player is removed.
    public static boolean toggle(UUID player) {
        if (trampleToggle.contains(player)) {
            trampleToggle.remove(player);
            return true;
        } else {
            trampleToggle.add(player);
            return false;
        }
    }

    public void onEnable() {
        Main.logger = Logger.getLogger("Minecraft");
        this.registerCommands();
        this.registerEvents();
    }


    public void onDisable() {
        final PluginDescriptionFile pdfFile = this.getDescription();
        final Logger logger = Logger.getLogger("Minecraft");
        logger.info(pdfFile.getName() + "has successfully disabled.");
    }


    public void registerEvents() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new Harvest(this), this);
        pm.registerEvents(new Trample(this), this);
        pm.registerEvents(new PistonEvent(this), this);
        pm.registerEvents(new WaterEvent(this), this);
    }


    public void registerCommands() {
        this.getCommand("toggle").setExecutor(new ToggleCommand());

    }
}
