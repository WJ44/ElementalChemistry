package com.wj44.elementscore.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 2-5-2015.
 *
 * Part of the ElementsCore Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ElementAPI
{
    public static HashMap<List, ElementList> itemElements = new HashMap<List, ElementList>();
    public static HashMap<List, ItemProperties> itemProperties = new HashMap<List, ItemProperties>();

    //======================================ELEMENTS======================================
    /**
     * Used to add elements to an item/block (respects metadata)
     * example: ElementAPI.registerItemElements(new ItemStack(Items.dye, 0, 0), new ElementList().add(Element.CALCIUM, 1));
     * @param itemStack the item used (amount does not matter, respects metadata)
     * @param elementList list of elements and the respective amounts
     */
    public static void registerItemElements(ItemStack itemStack, ElementList elementList)
    {
        itemElements.put(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage()), elementList);
    }

    /**
     * Used to add elements to an item
     * example: ElementAPI.registerItemElements(Items.emerald, new ElementList().add(Element.BERYLLIUM, 3).add(Element.ALUMINIUM, 2).add(Element.SILICON, 6).add(Element.OXYGEN, 18));
     * @param item the item used
     * @param elementList list of elements and the respective amounts
     */
    public static void registerItemElements(Item item, ElementList elementList)
    {
        registerItemElements(new ItemStack(item), elementList);
    }

    /**
     * Used to add elements to a block
     * example: ElementAPI.registerItemElements(Blocks.quartz_block, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
     * @param block the block used
     * @param elementList list of elements and the respective amounts
     */
    public static void registerItemElements(Block block, ElementList elementList)
    {
        registerItemElements(new ItemStack(block), elementList);
    }

    /**
     * Returns true when the item passed has elements defined
     * @param itemStack the item passed (respects metadata, amount does not matter)
     * @return
     */
    public static boolean hasElements(ItemStack itemStack)
    {
        if (itemElements.containsKey(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage()))) return true;
        return false;
    }

    //======================================PROPERTIES======================================
    public static void registerItemProperties(ItemStack itemStack, ItemProperties propertyList)
    {
        itemProperties.put(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage()), propertyList);
    }

    public static void registerItemProperties(Item item, ItemProperties propertyList)
    {
        registerItemProperties(new ItemStack(item), propertyList);
    }

    public static void registerItemProperties(Block block, ItemProperties propertyList)
    {
        registerItemProperties(new ItemStack(block), propertyList);
    }

    public static boolean hasProperties(ItemStack itemStack)
    {
        if (itemProperties.containsKey(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage()))) return true;
        return false;
    }
}
