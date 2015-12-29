package com.wj44.echem.block;

import com.wj44.echem.creativetab.ModCreativeTabs;
import com.wj44.echem.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Wesley "WJ44" Joosten on 29/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockEChem extends Block
{
    public BlockEChem(Material material)
    {
        super(material);
        this.setCreativeTab(ModCreativeTabs.tabEChem);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
