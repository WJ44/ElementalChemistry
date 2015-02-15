package com.wj44.echem.block;

import com.wj44.echem.creativetab.CreativeTabEChem;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public abstract class BlockEChemTileEntity extends BlockContainer
{
    public BlockEChemTileEntity(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabEChem.ECHEM_TAB);
    }

    public BlockEChemTileEntity()
    {
        this(Material.rock);
    }
}
