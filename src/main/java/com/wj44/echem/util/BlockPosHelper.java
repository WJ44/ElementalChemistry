package com.wj44.echem.util;

import net.minecraft.util.BlockPos;

/**
 * Created by Wesley "WJ44" Joosten on 17-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockPosHelper
{
    public static BlockPos[] getNeighboringPositions(BlockPos pos)
    {
        BlockPos[] positions = new BlockPos[6];

        positions[0] = pos.west();
        positions[1] = pos.east();
        positions[2] = pos.down();
        positions[3] = pos.up();
        positions[4] = pos.north();
        positions[5] = pos.south();

        return positions;
    }
}
