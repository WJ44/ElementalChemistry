package com.wj44.echem.network;

import com.wj44.echem.reference.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class NetworkHandler
{
    private static SimpleNetworkWrapper INSTANCE;

    public static void init()
    {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
    }

    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }
}
