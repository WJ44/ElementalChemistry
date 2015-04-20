package com.wj44.echem.init;

import com.wj44.echem.dataCables.DataCable;
import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityComposer;
import com.wj44.echem.tileentity.TileEntityDataBank;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityDecomposer.class, Names.Blocks.DECOMPOSER);
        GameRegistry.registerTileEntity(TileEntityComposer.class, Names.Blocks.ITEM_SCANNER);
        GameRegistry.registerTileEntity(TileEntityItemScanner.class, Names.Blocks.COMPOSER);
        GameRegistry.registerTileEntity(TileEntityDataBank.class, Names.Blocks.DATA_BANK);
        GameRegistry.registerTileEntity(DataCable.class, Names.Blocks.DATA_CABLE);
    }
}
