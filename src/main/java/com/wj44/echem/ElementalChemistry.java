package com.wj44.echem;

import com.wj44.echem.handler.GuiHandler;
import com.wj44.echem.init.*;
import com.wj44.echem.network.DescriptionHandler;
import com.wj44.echem.network.NetworkHandler;
import com.wj44.echem.proxy.CommonProxy;
import com.wj44.echem.reference.Reference;
import com.wj44.echem.util.LogHelper;
import com.wj44.echem.world.gen.WorldGeneratorEChem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 22-6-2014.
 *
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

        ModItems.init();
        ModBlocks.init();

        GameRegistry.registerWorldGenerator(new WorldGeneratorEChem(), 0);

        NetworkHandler.init();

        DescriptionHandler.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        TileEntities.init();

        ItemElements.init();

        proxy.init();

        Recipes.init();

        LogHelper.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }

}
