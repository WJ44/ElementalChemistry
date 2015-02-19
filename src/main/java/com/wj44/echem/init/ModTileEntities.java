package com.wj44.echem.init;

import com.wj44.echem.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityMine.class, Reference.LOWERCASE_MOD_ID + ":mine");
    }
}
