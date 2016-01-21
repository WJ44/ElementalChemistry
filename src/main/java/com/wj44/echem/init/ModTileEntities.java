package com.wj44.echem.init;

import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityDataBank;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 15/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityDataBank.class, Names.Blocks.DATA_BANK);
        GameRegistry.registerTileEntity(TileEntityDecomposer.class, Names.Blocks.DECOMPOSER);
        GameRegistry.registerTileEntity(TileEntityItemScanner.class, Names.Blocks.ITEM_SCANNER);
    }
}
