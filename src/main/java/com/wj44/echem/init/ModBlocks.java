package com.wj44.echem.init;

import com.wj44.echem.block.BlockEChem;
import com.wj44.echem.block.BlockTest;
import com.wj44.echem.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 12-7-2014.
 * -
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockEChem testBlock = new BlockTest();

    public static void init()
    {
        GameRegistry.registerBlock(testBlock, "testBlock");
    }
}
