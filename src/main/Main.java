package main;

import commands.WeaponsKit;
import events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static Plugin plugin;
    public static JavaPlugin javaPlugin;
    public static Logger logger;
    public static Random random = new Random();
    ScoreboardManager scoreboardManager;
    public static Scoreboard scoreboard;
    public static Team redTeam;
    public static Team blueTeam;
    public static Team purpleTeam;
    public static Team greenTeam;
    public static Team clearTeam;
    public void onEnable(){
        Main.logger = Logger.getLogger("Minecraft");
        Main.plugin = (Plugin)this;
        this.javaPlugin = this;

        this.registerCommands();
        this.registerEvents();
        enableScoreBoard();
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
        pm.registerEvents(new playerAttacks(), this);
        pm.registerEvents(new JoinEvent(), this);
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new Harvest(), this);
        pm.registerEvents(new Trample(),this);
    }


    public void registerCommands() {
     this.getCommand("kit").setExecutor(new WeaponsKit());

    }

    public void enableScoreBoard(){
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        redTeam = scoreboard.registerNewTeam("RedTeam");
        purpleTeam = scoreboard.registerNewTeam("PurpleTeam");
        greenTeam = scoreboard.registerNewTeam("GreenTeam");
        blueTeam = scoreboard.registerNewTeam("BlueTeam");
        clearTeam = scoreboard.registerNewTeam("ClearTeam");
        clearTeam.setAllowFriendlyFire(true);
        redTeam.setAllowFriendlyFire(false);
        purpleTeam.setAllowFriendlyFire(false);
        greenTeam.setAllowFriendlyFire(false);
        blueTeam.setAllowFriendlyFire(false);

        clearTeam.setColor(ChatColor.WHITE);
        redTeam.setColor(ChatColor.RED);
        purpleTeam.setColor(ChatColor.DARK_PURPLE);
        greenTeam.setColor(ChatColor.DARK_GREEN);
        blueTeam.setColor(ChatColor.DARK_BLUE);



        Objective health = scoreboard.registerNewObjective("showhealth", Criterias.HEALTH);
        health.setDisplaySlot(DisplaySlot.BELOW_NAME);
        health.setDisplayName(ChatColor.DARK_RED + "❤");
        Score score = health.getScore("showhealth");
    }


}
