package com.wj44.echem.proxy;

import com.wj44.echem.init.ModBlocks;
import com.wj44.echem.init.ModItems;
import com.wj44.echem.item.ItemElementContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Wesley "WJ44" Joosten on 24/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {

    }

    @Override
    public void init()
    {
        ItemElementContainer.registerVariants();
    }

    @Override
    public void postInit()
    {
        ModBlocks.registerRenders();
        ModItems.registerRenders();
    }

    @Override
    public EntityPlayer getEntityPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }
}
