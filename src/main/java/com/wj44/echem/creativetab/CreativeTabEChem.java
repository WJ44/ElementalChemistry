package com.wj44.echem.creativetab;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Wesley "WJ44" Joosten on 12-7-2014.
 * <p/>
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class CreativeTabEChem
{
    public static final CreativeTabs ECHEM_TAB = new CreativeTabs(Reference.LOWERCASE_MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.logo;
        }
    };

    public static final CreativeTabs ELEMENT_CONTAINER_TAB = new CreativeTabs("elementContainers")
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.elementContainer;
        }

        //Sets the metadata value for the IconItem
        @Override
        @SideOnly(Side.CLIENT)
        public int getIconItemDamage()
        {
            return 1;
        }
    };
}
