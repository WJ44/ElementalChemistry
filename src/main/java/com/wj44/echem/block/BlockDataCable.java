package com.wj44.echem.block;

import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityDataCable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 17-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockDataCable extends BlockEChem implements ITileEntityProvider
{
    public BlockDataCable()
    {
        setUnlocalizedName(Names.Blocks.DATA_CABLE);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityDataCable();
    }
}
