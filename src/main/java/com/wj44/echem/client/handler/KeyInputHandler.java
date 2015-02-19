package com.wj44.echem.client.handler;

import com.wj44.echem.client.settings.Keybindings;
import com.wj44.echem.network.NetworkHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class KeyInputHandler
{
    private Keybindings getPressedKey()
    {
        for (Keybindings key : Keybindings.values())
        {
            if (key.isPressed())
            {
                return key;
            }
        }
        return null;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        Keybindings key = getPressedKey();
        if (key != null)
        {
            switch (key)
            {
                case EXPLODE:
                    NetworkHandler.sendToServer(new MessageExplode(3)); //todo Delete
                    break;
                case EXPLODE_BIG:
                    NetworkHandler.sendToServer(new MessageExplode(30)); //todo Delete
            }
        }
    }
}
