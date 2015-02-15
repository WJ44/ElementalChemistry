package com.wj44.echem.block;

import com.wj44.echem.reference.Reference;
import com.wj44.echem.tileentity.TileEntityMine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
}
