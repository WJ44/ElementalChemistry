package com.wj44.echem.block;

import com.wj44.echem.util.DataHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 21-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public abstract class BlockElementMachine extends BlockEChemContainer
{
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (!worldIn.isRemote)
        {
            DataHelper.updateData(worldIn, pos, this);
        }
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
}
