package com.wj44.echem.proxy;

import com.wj44.echem.client.settings.Keybindings;
import cpw.mods.fml.client.registry.ClientRegistry;

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
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(Keybindings.gui);
    }
}
