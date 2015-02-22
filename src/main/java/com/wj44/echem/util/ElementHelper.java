package com.wj44.echem.util;

import com.wj44.echem.reference.Elements;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

/**
 * Created by Wesley "WJ44" Joosten on 22-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ElementHelper
{
    public static Map itemElementsList = new HashMap();
    private Map elementList = new HashMap();

    public ElementHelper(Item item)
    {
        this.elementList.putAll((Map) itemElementsList.get(item));
    }

    public static void addItemToItemElementList(Item item, Map elementList)
    {
        itemElementsList.put(item, elementList);
    }

    public Set<Elements> getElements()
    {
       return elementList.keySet();
    }

    public int getAmount(Elements element)
    {
        return (Integer) elementList.get(element);
    }

    public boolean compareContainersWithElements(ItemStack[] itemStacks)
    {
        int availableSlots = 0;

        for(ItemStack itemStack : itemStacks)
        {
            if (itemStack == null)
            {
                availableSlots++;
            }
            else
            {
                for (Elements element : getElements())
                {
                    if (itemStack.getItemDamage() == element.ordinal() && itemStack.stackSize + getAmount(element) <= 64)
                    {
                        availableSlots++;
                    }
                }
            }
        }
        return availableSlots >= getElements().size();
    }
}
