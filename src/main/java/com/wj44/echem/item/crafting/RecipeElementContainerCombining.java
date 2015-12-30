package com.wj44.echem.item.crafting;

import com.wj44.echem.init.ModItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 30/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class RecipeElementContainerCombining implements IRecipe
{
    @Override
    public boolean matches(InventoryCrafting inventory, World world)
    {
        int i = 0;
        int element = -1;

        for (int j = 0; j < inventory.getSizeInventory(); ++j)
        {
            ItemStack itemStack = inventory.getStackInSlot(j);

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
    public ItemStack getCraftingResult(InventoryCrafting inventory)
    {
        int element = -1;
        int amount = 0;

        for (int j = 0; j < inventory.getSizeInventory(); ++j)
        {
            ItemStack itemStack = inventory.getStackInSlot(j);

            if (itemStack != null)
            {
                if (itemStack.getItem() == ModItems.elementContainer)
                {
                    if (element == -1)
                    {
                        element = itemStack.getItemDamage();
                    }
                    amount += itemStack.getTagCompound().getInteger("amount");
                }
            }
        }
        ItemStack returnItemStack = new ItemStack(ModItems.elementContainer, 1, element);
        returnItemStack.setTagCompound(new NBTTagCompound());
        returnItemStack.getTagCompound().setInteger("amount", amount);

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
    public ItemStack[] getRemainingItems(InventoryCrafting inventory)
    {
        int stacks = 0;
        for (int i = 0; i < inventory.getSizeInventory(); ++i)
        {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack != null)
            {
                stacks++;
            }
        }
        ItemStack[] itemStacks = new ItemStack[inventory.getSizeInventory()];

        itemStacks[0] = new ItemStack(ModItems.elementContainer, stacks-1, 0);
        itemStacks[0].setTagCompound(new NBTTagCompound());
        itemStacks[0].getTagCompound().setInteger("amount", 0);
        return itemStacks;
    }
}
