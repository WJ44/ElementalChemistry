package com.wj44.echem.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 2-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ElementalChemistryAPI
{
    public static HashMap<List, ElementList> itemElements = new HashMap<List, ElementList>();

    /**
     * Used to add elements to an item/block (respects metadata)
     * example: ElementalChemistryAPI.registerItemElements(new ItemStack(Items.dye, 0, 0), new ElementList().add(Element.CALCIUM, 1));
     * @param itemStack the item used (amount does not matter, respects metadata)
     * @param elementList list of elements and the respective amounts
     */
    public static void registerItemElements(ItemStack itemStack, ElementList elementList)
    {
        itemElements.put(Arrays.asList(itemStack.getItem(), itemStack.getItemDamage()), elementList);
    }

    /**
     * Used to add elements to an item
     * example: ElementalChemistryAPI.registerItemElements(Items.emerald, new ElementList().add(Element.BERYLLIUM, 3).add(Element.ALUMINIUM, 2).add(Element.SILICON, 6).add(Element.OXYGEN, 18));
     * @param item the item used
     * @param elementList list of elements and the respective amounts
     */
    public static void registerItemElements(Item item, ElementList elementList)
    {
        registerItemElements(new ItemStack(item), elementList);
    }

    /**
     * Used to add elements to a block
     * example: ElementalChemistryAPI.registerItemElements(Blocks.quartz_block, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
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
}
