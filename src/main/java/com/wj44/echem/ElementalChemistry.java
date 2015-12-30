package com.wj44.echem;

import com.wj44.echem.init.ModBlocks;
import com.wj44.echem.init.ModItems;
import com.wj44.echem.init.ModRecipes;
import com.wj44.echem.proxy.CommonProxy;
import com.wj44.echem.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Wesley "WJ44" Joosten on 24/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ElementalChemistry
{
    @Mod.Instance(Reference.MOD_ID)
    public static ElementalChemistry instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();

        ModBlocks.init();
        ModItems.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();

        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLInitializationEvent event)
    {
        proxy.postInit();
    }
}
