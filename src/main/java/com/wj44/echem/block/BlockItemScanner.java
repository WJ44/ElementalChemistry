package com.wj44.echem.block;

import com.wj44.echem.ElementalChemistry;
import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.init.ModBlocks;
import com.wj44.echem.reference.GUIs;
import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Wesley "WJ44" Joosten on 24-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockItemScanner extends BlockEChemContainer
{
    private final boolean isBurning;

    public BlockItemScanner(boolean isBurning)
    {
        setUnlocalizedName(Names.Blocks.ITEM_SCANNER);
        setUnlocalizedName(Names.Blocks.COMPOSER);
        if(!isBurning)
        {
            setCreativeTab(CreativeTabEChem.ECHEM_TAB);
        }
        this.isBurning = isBurning;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityItemScanner();
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
                player.openGui(ElementalChemistry.instance, GUIs.ITEM_SCANNER.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }

    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;

        if (active)
        {
            worldIn.setBlockState(pos, ModBlocks.lit_itemScanner.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.lit_itemScanner.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else
        {
            worldIn.setBlockState(pos, ModBlocks.itemScanner.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.itemScanner.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(ModBlocks.decomposer);
    }

}
