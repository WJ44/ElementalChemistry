package com.wj44.echem.util;

import com.wj44.elementscore.api.Element;
import com.wj44.elementscore.api.ElementAPI;
import com.wj44.elementscore.api.ElementList;
import com.wj44.elementscore.api.ItemProperties;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

/**
 * Created by Wesley "WJ44" Joosten on 15/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class APIHelper
{
    public static ElementList getElementList(ItemStack itemStack)
    {
        return ElementAPI.hasElements(itemStack) ? ElementAPI.itemElements.get(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage())) : null;
    }

    public static ItemProperties getProperties(ItemStack itemStack)
    {
        return ElementAPI.hasProperties(itemStack) ? ElementAPI.itemProperties.get(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage())) : null;
    }

    //TODO needs improvement
    public static int getElementAmount(ItemStack itemStack, Element element)
    {
        ElementList elementList = getElementList(itemStack);
        int totalElements = 0;
        for (Element e : elementList.getElements())
        {
            totalElements += elementList.getAmount(e);
        }
        float elementProportion = totalElements / elementList.getAmount(element);
        int itemMass = getProperties(itemStack).mass;
        int amount = (int) (itemMass / (0.166053892F * element.mass));
        return (int) (amount * elementProportion) / 10;
    }
}
