package com.wj44.echem.item;

import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.reference.Textures;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 6-7-2014.
 *
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemEChem extends Item
{
    public ItemEChem()
    {
        super();
        this.setCreativeTab(CreativeTabEChem.ECHEM_TAB);
    }

    public ItemEChem(String name)
    {
        super();
        this.setCreativeTab(CreativeTabEChem.ECHEM_TAB);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
