package com.wj44.echem.block;

import com.wj44.echem.tileentity.TileEntityEChem;
import com.wj44.echem.tileentity.TileEntityElementMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 7-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public abstract class BlockElementMachine extends BlockEChemContainer
{
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!keepInventory)
        {
            TileEntityElementMachine tileentity = (TileEntityElementMachine) worldIn.getTileEntity(pos);

            if (tileentity.dataBankConnected)
            {
                tileentity.setInventorySlotContents(tileentity.getDataCardIndex(), null);
            }
            if (tileentity instanceof TileEntityEChem)
            {
                InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityEChem) tileentity);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }
}
