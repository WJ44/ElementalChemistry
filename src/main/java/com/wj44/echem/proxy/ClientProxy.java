package com.wj44.echem.proxy;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.item.ItemElementContainer;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Wesley "WJ44" Joosten on 26-6-2014.
 *
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

        ModItems.registerRenders();
    }

    @Override
    public void postInit()
    {

    }

    @Override
    public EntityPlayer getEntityPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }
}
