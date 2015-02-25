package com.wj44.echem.proxy;

import com.wj44.echem.client.handler.KeyInputHandler;
import com.wj44.echem.client.settings.Keybindings;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

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
        registerKeyBinds();
    }

    private void registerKeyBinds()
    {
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        for (Keybindings key : Keybindings.values())
        {
            ClientRegistry.registerKeyBinding(key.getKeybind());
        }
    }

    @Override
    public void init()
    {

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
