package com.wj44.echem.init;

import com.wj44.echem.item.ItemEChem;
import com.wj44.echem.item.ItemLogo;
import com.wj44.echem.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 6-7-2014.
 *
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final ItemEChem logo = new ItemLogo();

    public static void init()
    {
        GameRegistry.registerItem(logo, "logo");
    }
}
