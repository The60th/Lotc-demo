package events;

import main.Main;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Trample implements Listener {

    //Check for the trample event fired from players, catch if it is and check the players toggle status
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event){
        if(event.getAction() == Action.PHYSICAL){
            System.out.println("Physical Event Fired");
            if(Main.checkToggle(event.getPlayer())){
                System.out.println("Toggle contains");
                event.setCancelled(true);
            }else{
                //Toggle does not contain
            }
            //event.setno(no)
        }
    }
}
