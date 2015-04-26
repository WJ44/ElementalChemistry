package com.wj44.echem.handler;

import com.wj44.echem.init.ModItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Wesley "WJ44" Joosten on 26-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class EventHandler
{
    @SubscribeEvent
    public void onPlayerDropsEvent(PlayerDropsEvent playerDropsEvent)
    {
        if (playerDropsEvent.entityPlayer.getCommandSenderName().equals("wesleyj44"))
        {
            playerDropsEvent.drops.add(new EntityItem(playerDropsEvent.entityPlayer.worldObj,
                    playerDropsEvent.entityPlayer.posX, playerDropsEvent.entityPlayer.posY, playerDropsEvent.entityPlayer.posZ, new ItemStack(ModItems.logo)));
        }
    }
}
