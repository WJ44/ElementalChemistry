package com.wj44.echem.util;

import com.wj44.echem.api.Element;
import com.wj44.echem.api.ElementList;
import com.wj44.echem.api.ElementalChemistryAPI;
import com.wj44.echem.api.PropertyList;
import com.wj44.echem.reference.Names;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

/**
 * Created by Wesley "WJ44" Joosten on 2-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class APIHelper
{
    public static ElementList getElementList(ItemStack itemStack)
    {
        return ElementalChemistryAPI.hasElements(itemStack) ? ElementalChemistryAPI.itemElements.get(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage())) : null;
    }

    public static PropertyList getPropertyList(ItemStack itemStack)
    {
        return ElementalChemistryAPI.hasProperties(itemStack) ? ElementalChemistryAPI.itemProperties.get(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage())) : null;
    }

    /**
     * Returns true if there is room in the inventory for the element containers
     *
     * @param item the item that is to be decomposed
     * @param inventory all output slots in the inventory
     * @return
     */
    public static boolean compareInventoryWithElements(ItemStack item, ItemStack[] inventory) //TODO Improve(stack up to 64) (also in te)
    {
        int availableSlots = 0;

        for (ItemStack itemStack : inventory)
        {
            if (itemStack == null)
            {
                availableSlots++;
            }
            else
            {
                for (Element element : getElementList(item).getElements())
                {
                    if (itemStack.getItemDamage() == element.number && itemStack.stackSize + getElementList(item).getAmount(element) <= 64)
                    {
                        availableSlots++;
                    }
                }
            }
        }
        return availableSlots >= getElementList(item).getElements().length;
    }

    /**
     * Returns true if the input element containers are the elements required for the output
     *
     * @param item the item that is to be composed
     * @param inventory all input slots in the inventory
     * @return
     */
    public static boolean compareElementsWithInventory(ItemStack item, ItemStack[] inventory) //TODO Improve(stack up to 64) (also in te)
    {
        int matching = 0;
        int[] elementCounter = new int[Element.elements.values().size()];
        for (Element element : getElementList(item).getElements())
        {
            for (ItemStack itemStack : inventory)
            {
                if (itemStack != null)
                {
                    if (itemStack.getItemDamage() == element.number)
                    {
                        elementCounter[element.number] += itemStack.stackSize;
                    }
                }
            }
            if (elementCounter[element.number] >= getElementList(item).getAmount(element))
            {
                matching++;
            }
        }
        return matching == getElementList(item).getElements().length;
    }

    public static int getElementAmount(ItemStack itemStack, Element element)
    {
        ElementList elementList = getElementList(itemStack);
        int totalElements = 0;
        for (Element e : elementList.getElements())
        {
            totalElements += elementList.getAmount(e);
        }
        float elementProportion = totalElements / elementList.getAmount(element);
        int itemMass = (Integer) getPropertyList(itemStack).getValue(Names.Properties.MASS);
        int amount = (int) (itemMass / (0.166053892F * element.mass));
        return (int) (amount * elementProportion) / 10;
    }
}
