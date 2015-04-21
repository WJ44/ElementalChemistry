package com.wj44.echem.util;

import com.wj44.echem.block.BlockDataCable;
import com.wj44.echem.dataCables.DataCable;
import com.wj44.echem.dataCables.DataCables;
import com.wj44.echem.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Wesley "WJ44" Joosten on 18-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class DataHelper
{
    public static void updateData(World world, BlockPos sourcePos, Block sourceBlock)
    {
        for (BlockPos pos : BlockPosHelper.getNeighboringPositions(sourcePos))
        {
            if (world.getBlockState(pos).getBlock() == ModBlocks.dataCable)
            {
                BlockDataCable block = (BlockDataCable) world.getBlockState(pos).getBlock();
                block.onDataChange(world, pos, sourcePos, sourceBlock);
            }
        }
    }

    public static void removeDataCable(BlockPos pos, World world)
    {
        DataCable cable = DataCables.getCableAt(pos);

        if (cable.connectedToDataBank)
        {
            cable.passDisconnect(pos, new Random().nextInt());
        }

        cable.updateConnection();
    }
}
