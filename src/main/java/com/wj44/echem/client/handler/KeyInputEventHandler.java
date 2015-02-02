package com.wj44.echem.client.handler;

import com.wj44.echem.client.settings.Keybindings;
import com.wj44.echem.reference.Key;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/**
 * Created by Wesley "WJ44" Joosten on 31-8-2014.
 *
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class KeyInputEventHandler
{
    private static Key getPressedKeybinding()
    {
        if (Keybindings.gui.isPressed())
        {
            return Key.GUI;
        }

        return Key.UNKOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {

    }
}
