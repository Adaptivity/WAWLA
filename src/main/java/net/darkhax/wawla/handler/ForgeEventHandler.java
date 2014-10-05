package net.darkhax.wawla.handler;

import net.darkhax.wawla.modules.Module;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ForgeEventHandler {

    public ForgeEventHandler() {

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {

        for (Module module : Module.getModules())
            module.onTooltipDisplayed(event.itemStack, event.entityPlayer, event.toolTip, event.showAdvancedItemTooltips);
    }
}
