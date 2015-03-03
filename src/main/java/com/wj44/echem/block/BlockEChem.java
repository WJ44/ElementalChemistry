package com.wj44.echem.block;

import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Wesley "WJ44" Joosten on 12-7-2014.
 *
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockEChem extends Block
{
    public BlockEChem(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabEChem.ECHEM_TAB);
    }

    public BlockEChem()
    {
        this(Material.rock);
    }

    public BlockEChem(String name)
    {
        this();
        this.setUnlocalizedName(name);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.LOWERCASE_MOD_ID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}