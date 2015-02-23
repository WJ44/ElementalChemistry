package com.wj44.echem.util;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 23-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemElementDamageValueHelper
{

    public static boolean damageValueHelper(ItemStack itemStack)
    {
        List<Item> itemList = new ArrayList();
        itemList.add(Items.dye);
        if (itemList.contains(itemStack))
        {
            if(itemStack.getItem() == Items.dye && itemStack.getItemDamage() == 4) return true;
            else return false;
        }

        return true;
    }
}
