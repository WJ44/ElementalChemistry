package com.wj44.echem.client.settings;

import com.wj44.echem.reference.Names;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * Created by Wesley "WJ44" Joosten on 17/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class KeyBindings
{
    public static KeyBinding select = new KeyBinding(Names.Keys.SELECT, Keyboard.KEY_F, Names.Keys.CATEGORY);
}
