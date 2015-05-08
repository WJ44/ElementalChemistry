package com.wj44.echem.item.crafting;

import com.wj44.echem.init.ModItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 8-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class RecipeElementContainerCombining implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        int i = 0;
        int element = -1;

        for (int j = 0; j < inv.getSizeInventory(); ++j)
        {
            ItemStack itemStack = inv.getStackInSlot(j);

            if (itemStack != null)
            {
                if (itemStack.getItem() == ModItems.elementContainer)
                {
                    if (element == -1)
                    {
                        element = itemStack.getItemDamage();
                    }
                    else if (element != itemStack.getItemDamage())
                    {
                        return false;
                    }
                }
                else return false;
                i++;
            }
        }

        return  i > 1 && element != 0;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        int element = -1;
        int amount = 0;

        for (int j = 0; j < inv.getSizeInventory(); ++j)
        {
            ItemStack itemStack = inv.getStackInSlot(j);

            if (itemStack != null)
            {
                if (itemStack.getItem() == ModItems.elementContainer)
                {
                    if (element == -1)
                    {
                        element = itemStack.getItemDamage();
                    }
                    amount += itemStack.getTagCompound().getInteger("Amount");
                }
            }
        }
        ItemStack returnItemStack = new ItemStack(ModItems.elementContainer, 1, element);
        returnItemStack.setTagCompound(new NBTTagCompound());
        returnItemStack.getTagCompound().setInteger("Amount", amount);

        return returnItemStack;
    }

    @Override
    public int getRecipeSize()
    {
        return 4;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return null;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv)
    {
        int stacks = 0;
        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemStack = inv.getStackInSlot(i);
            if (itemStack != null)
            {
                stacks++;
            }
        }
        ItemStack[] itemStacks = new ItemStack[inv.getSizeInventory()];

        itemStacks[0] = new ItemStack(ModItems.elementContainer, stacks-1, 0);
        itemStacks[0].setTagCompound(new NBTTagCompound());
        itemStacks[0].getTagCompound().setInteger("Amount", 0);
        return itemStacks;
    }
}
