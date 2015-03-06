package com.wj44.echem.util;

import com.wj44.echem.reference.Elements;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    private Map elementList;

    public ElementHelper(Item item)
    {
        elementList = new LinkedHashMap((LinkedHashMap) itemElementsList.get(item));
    }

    public static void addItemToItemElementList(Item item, Map elementList)
    {
        itemElementsList.put(item, elementList);
    }

    public static void addItemToItemElementList(Block block, Map elementList)
    {
        itemElementsList.put(Item.getItemFromBlock(block), elementList);
    }

    public Set<Elements> getElements()
    {
        return elementList.keySet();
    }

    public int getAmount(Elements element)
    {
        return (Integer) elementList.get(element);
    }


    /**
     * Returns true if there is room in the inventory for the element containers
     *
     * @param itemStacks all output slots in the inventory
     * @return
     */
    public boolean compareContainersWithElements(ItemStack[] itemStacks)
    {
        int availableSlots = 0;

        for (ItemStack itemStack : itemStacks)
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

    /**
     * Returns true if the input element containers are the elements required for the output
     *
     * @param itemStacks
     * @return
     */
    public boolean compareElementsWithContainers(ItemStack[] itemStacks)
    {
        int matching = 0;
        int[] elementCounter = new int[Elements.values().length];
        for (Elements element : getElements())
        {
            for (ItemStack itemStack : itemStacks)
            {
                if (itemStack != null)
                {
                    if (itemStack.getItemDamage() == element.ordinal())
                    {
                        elementCounter[element.ordinal()] += itemStack.stackSize;
                    }
                }
            }
            if (elementCounter[element.ordinal()] >= getAmount(element))
            {
                matching++;
            }
        }
        return matching == getElements().size();
    }

}



