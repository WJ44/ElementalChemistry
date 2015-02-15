package com.wj44.echem.block;

import com.wj44.echem.reference.Reference;
import com.wj44.echem.tileentity.TileEntityMine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Stack;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockMine extends BlockEChemTileEntity
{
    public BlockMine()
    {
        setBlockName("mine");
        setBlockTextureName(Reference.LOWERCASE_MOD_ID + ":" + "mine");
    }
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityMine();
    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        TileEntityMine te = (TileEntityMine)world.getTileEntity(x, y, z);

        te.setCamo(player.getCurrentEquippedItem());

        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        TileEntityMine te = (TileEntityMine)world.getTileEntity(x, y, z);
        ItemStack stack = te.getCamo();
        if (stack != null && stack.getItem() instanceof ItemBlock)
        {
            Block block = ((ItemBlock) stack.getItem()).field_150939_a;
            return block.getIcon(side, stack.getItemDamage());
        }
        else
        {
            return super.getIcon(world, x, y, z, side);
        }
    }
}
