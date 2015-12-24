package com.wj44.echem.block;

import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityMachinePart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 6-6-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockTank extends BlockMachinePart
{
    public BlockTank()
    {
        setUnlocalizedName(Names.Blocks.TANK);
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityMachinePart("tank");
    }
}
