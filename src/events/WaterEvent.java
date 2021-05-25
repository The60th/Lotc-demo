package events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class WaterEvent implements Listener {


    /**
     *
     * This may cause unforseen lag issues or double event triggers as the way it functions is follows:
     * On the first spread of water we detect if the "toBlock" is an instance of ageAble, if it is we set the block
     * to air and cancel the event. The event needs to be cancelled in this instance because items will drop
     * before the block is set to air. This causes the water to get reset and need to flow a second time.
     *
     */
    @EventHandler
    public void waterFlowEvent(BlockFromToEvent event){
            if(event.getToBlock().getBlockData() instanceof Ageable){
                event.getToBlock().setType(Material.AIR);
                event.setCancelled(true);
            }
    }
}
