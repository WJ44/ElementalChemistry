package com.wj44.echem.block;

import com.wj44.echem.ElementalChemistry;
import com.wj44.echem.reference.Guis;
import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityDataBank;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 15/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockDataBank extends BlockEChemContainer
{
    public BlockDataBank()
    {
        setUnlocalizedName(Names.Blocks.DATA_BANK);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityDataBank();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                player.openGui(ElementalChemistry.instance, Guis.DATA_BANK.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }
}
