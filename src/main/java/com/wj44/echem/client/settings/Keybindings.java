package com.wj44.echem.client.settings;

import com.wj44.echem.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * Created by Wesley "WJ44" Joosten on 31-8-2014.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class Keybindings
{
    public static KeyBinding gui = new KeyBinding(Names.Keys.GUI, Keyboard.KEY_C, Names.Keys.CATEGORY);
}
