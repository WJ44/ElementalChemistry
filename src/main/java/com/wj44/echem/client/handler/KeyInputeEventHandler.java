package com.wj44.echem.client.handler;

import com.wj44.echem.ElementalChemistry;
import com.wj44.echem.client.settings.KeyBindings;
import com.wj44.echem.reference.Key;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * Created by Wesley "WJ44" Joosten on 17/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class KeyInputeEventHandler
{
    private static Key getPressedKeyBinding()
    {
        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        switch (getPressedKeyBinding())
        {
            case UNKNOWN:
            {
                //NOOP
            }
        }
    }
}
