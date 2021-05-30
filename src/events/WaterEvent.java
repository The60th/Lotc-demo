package events;

import main.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

public class WaterEvent implements Listener {
    private Main main;
    public WaterEvent(Main main){
        this.main = main;
    }

    /**
     *
     * This function checks for flowing water to a block, if the block water is flowing to is ageable we do several
     * things. First we get the current water level of the source block, we then set the target block to water,
     * then set that water sources level to the current water level + 1, this will effectively reduce the water
     * level by 1. Then we cancel the event to prevent items from being dropped but water flows as normal.
     *
     */
    @EventHandler
    public void waterFlowEvent(BlockFromToEvent event){
            if(event.getToBlock().getBlockData() instanceof Ageable){
                //Set the block that water is flowing to to the correct water level.
                Levelled startingLevel =  (Levelled)( event.getBlock().getBlockData());

                event.getToBlock().setType(Material.WATER);
                Levelled level = (Levelled)event.getToBlock().getBlockData();
                level.setLevel(startingLevel.getLevel()+1); //Water level works in reverse, with a higher number being lower
                event.getToBlock().setBlockData(level);
                //cancel the event so drops work correctly.
                event.setCancelled(true);
            }
    }
}
