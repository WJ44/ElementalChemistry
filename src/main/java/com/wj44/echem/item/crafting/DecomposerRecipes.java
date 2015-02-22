package com.wj44.echem.item.crafting;

import com.wj44.echem.init.ModBlocks;
import com.wj44.echem.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Wesley "WJ44" Joosten on 22-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class DecomposerRecipes
{
    private static final DecomposerRecipes smeltingBase = new DecomposerRecipes();

    private Map smeltingList = new HashMap();

    public static DecomposerRecipes smelting()
    {
        return smeltingBase;
    }

    private DecomposerRecipes()
    {
        addRecipe(ModBlocks.testBlock, new ItemStack(ModItems.elementContainer, 5));
    }

    public void addRecipe(Block input, ItemStack output)
    {
        this.addRecipe(Item.getItemFromBlock(input), output);
    }

    public void addRecipe(Item input, ItemStack output)
    {
        this.addRecipe(new ItemStack(input, 1, 32767), output);
    }

    public void addRecipe(ItemStack input, ItemStack output)
    {
        this.smeltingList.put(input, output);
    }


    public ItemStack getSmeltingResult(ItemStack input)
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Map.Entry entry;
        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!this.compareItemStacks(input, (ItemStack) entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean compareItemStacks(ItemStack input, ItemStack output)
    {
        return output.getItem() == input.getItem() && (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }
}
