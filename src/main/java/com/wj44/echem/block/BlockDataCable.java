package com.wj44.echem.block;

import com.wj44.echem.dataCables.DataCable;
import com.wj44.echem.dataCables.DataCables;
import com.wj44.echem.init.ModBlocks;
import com.wj44.echem.reference.Names;
import com.wj44.echem.util.DataHelper;
import com.wj44.echem.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 17-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockDataCable extends BlockEChem
{
    public BlockDataCable()
    {
        setUnlocalizedName(Names.Blocks.DATA_CABLE);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (!worldIn.isRemote)
        {
            DataCables.createDataCable(pos);
            DataHelper.updateData(worldIn, pos, this);
        }

        return this.getDefaultState();
    }

    public void onDataChange(World world, BlockPos pos, BlockPos neighborPos, Block sourceBlock)
    {
        if (!world.isRemote)
        {
            LogHelper.info(pos + " from " + neighborPos);
            DataCable cable = DataCables.getCableAt(pos);

            cable.checkConnected();

            if((sourceBlock == ModBlocks.dataBank || sourceBlock == ModBlocks.dataCable || sourceBlock == ModBlocks.composer) && cable.connectedToDataBank)
            {
                cable.passInformation();
            }
            else if ((sourceBlock == ModBlocks.dataBank || sourceBlock == ModBlocks.composer || sourceBlock == ModBlocks.dataCable) &! cable.connectedToDataBank && cable.originCable != null)
            {
                cable.originCable.passInformation();
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            DataHelper.updateData(worldIn, pos, this);
            DataCables.removeDataCable(pos);
        }
        super.breakBlock(worldIn, pos, state);
    }
}
