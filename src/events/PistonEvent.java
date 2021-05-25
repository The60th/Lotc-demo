package events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class PistonEvent implements Listener {

    /**
     *
     * Check if the instance of any blocks effect by the piston moving are of ageAble type, if so we know they
     * are a crop and want to replace the block instance with air so that items do not drop.
     *
     * Unsure of the effects of honey blocks and slimeblocks on this interaction.
     * They may cause other blocks to be effect that will break crops and allow them to drop
     * with a different set of events being fired.
     *
     */

    @EventHandler
    public void pistonExtendEvent(BlockPistonExtendEvent event){
        for (Block block : event.getBlocks()) {
            if(block.getBlockData() instanceof Ageable){
                block.setType(Material.AIR);
            }
        }
    }

}
