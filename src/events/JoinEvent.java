package events;

import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class JoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.setMaxHealth(40);
        player.setScoreboard(Main.scoreboard);
        Scoreboard scoreboard = Main.scoreboard;
        Objective objective = scoreboard.getObjective("showhealth");
        Score score = (Score) objective.getScore("showhealth");
        score.setScore((int) player.getHealth());

        for(Player online : Bukkit.getOnlinePlayers()){
            online.setScoreboard(Main.scoreboard);

            online.setHealth(online.getHealth()); //Update their health

           // online.sendMessage("" + Main.scoreboard.getEntries());

        }

    }
}
