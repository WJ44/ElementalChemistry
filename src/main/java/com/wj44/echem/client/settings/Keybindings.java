package com.wj44.echem.client.settings;

import com.wj44.echem.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public enum Keybindings
{
    EXPLODE(Names.Keys.EXPLODE, Keyboard.KEY_Z), //TODO delete
    EXPLODE_BIG("keys.echem.explodeBig", Keyboard.KEY_X); //todo Delete

    private final KeyBinding keybinding;

    private Keybindings(String keyName, int defaultKeyCode)
    {
        keybinding = new KeyBinding(keyName, defaultKeyCode, Names.Keys.CATEGORY);
    }

    public KeyBinding getKeybind()
    {
        return keybinding;
    }

    public boolean isPressed()
    {
        return keybinding.isPressed();
    }
}
