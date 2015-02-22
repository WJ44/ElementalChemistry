package com.wj44.echem.block;

import com.wj44.echem.ElementalChemistry;
import com.wj44.echem.reference.GUIs;
import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 20-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockDecomposer extends BlockEChemTileEntity
{
    public BlockDecomposer()
    {
        setBlockName(Names.Blocks.DECOMPOSER);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData)
    {
        return new TileEntityDecomposer();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (player.isSneaking())
        {
            return false;
        }
        else
        {
            if (!world.isRemote)
            {
                    player.openGui(ElementalChemistry.instance, GUIs.DECOMPOSER.ordinal(), world, x, y, z);
            }

            return true;
        }
    }
}
