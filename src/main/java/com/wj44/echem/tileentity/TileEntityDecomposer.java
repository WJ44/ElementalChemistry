package com.wj44.echem.tileentity;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.reference.Names;
import com.wj44.echem.util.APIHelper;
import com.wj44.elementscore.api.Element;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

/**
 * Created by Wesley "WJ44" Joosten on 21/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityDecomposer extends TileEntityEChem implements ITickable
{
    public static final int INPUT_INVENTORY_INDEX = 0;
    public static final int DATA_CARD_INVENTORY_INDEX = 1;
    public static final int OUTPUT_INVENTORY_STARTINDEX = 2;
    public static final int[] OUTPUT = {OUTPUT_INVENTORY_STARTINDEX, OUTPUT_INVENTORY_STARTINDEX+1, OUTPUT_INVENTORY_STARTINDEX+2, OUTPUT_INVENTORY_STARTINDEX+3, OUTPUT_INVENTORY_STARTINDEX+4, OUTPUT_INVENTORY_STARTINDEX+5};

    public TileEntityDecomposer()
    {
        inventory = new ItemStack[8];
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        if (index == 0)
        {
            return true;
        }
        else if (index == 1)
        {
            return stack.getItem() == ModItems.dataCard && stack.getTagCompound().getBoolean("isScanned");
        }
        else if (stack.getItem() == ModItems.elementContainer)
        {
            return true;
        }
        return false;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : Names.Containers.DECOMPOSER;
    }

    @Override
    public void update()
    {
        if (inventory[INPUT_INVENTORY_INDEX] != null && inventory[DATA_CARD_INVENTORY_INDEX] != null && inventory[DATA_CARD_INVENTORY_INDEX].getTagCompound().getBoolean("isScanned"))
        {
            if (ItemStack.loadItemStackFromNBT(inventory[DATA_CARD_INVENTORY_INDEX].getTagCompound()).getItem() == inventory[INPUT_INVENTORY_INDEX].getItem())
            {
                if (checkOutputSlots())
                {
                    for (Element element : APIHelper.getElementList(inventory[INPUT_INVENTORY_INDEX]).getElements())
                    {
                        int amount = APIHelper.getElementAmount(inventory[INPUT_INVENTORY_INDEX], element);
                        boolean doSecondLoop = true;
                        for (int index : OUTPUT)
                        {
                            ItemStack itemStack = inventory[index];
                            if (itemStack != null && itemStack.getItem() == ModItems.elementContainer && itemStack.getItemDamage() == element.number)
                            {
                                if (itemStack.getItemDamage() == element.number)
                                {
                                    itemStack.getTagCompound().setInteger("amount", itemStack.getTagCompound().getInteger("amount") + amount);
                                    doSecondLoop = false;
                                    break;
                                }
                            }
                        }
                        if (doSecondLoop)
                        {
                            for (int index : OUTPUT)
                            {
                                ItemStack itemStack = inventory[index];
                                if (itemStack != null && itemStack.getItem() == ModItems.elementContainer && itemStack.getItemDamage() == 0)
                                {
                                    itemStack.setItemDamage(element.number);
                                    itemStack.getTagCompound().setInteger("amount", itemStack.getTagCompound().getInteger("amount") + amount);
                                    break;
                                }
                            }
                        }

                    }

                    decrStackSize(INPUT_INVENTORY_INDEX, 1);
                }
            }
        }
    }

    private boolean checkOutputSlots()
    {
        int emptyStacks = 0;
        int requiredEmpty = 0;
        for (Element element : APIHelper.getElementList(inventory[INPUT_INVENTORY_INDEX]).getElements())
        {
            boolean foundMatch = false;
            for (int index : OUTPUT)
            {
                if (inventory[index] != null && inventory[index].getItem() == ModItems.elementContainer && inventory[index].getItemDamage() == element.number)
                {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch)
            {
                requiredEmpty++;
            }
        }
        for (int index : OUTPUT)
        {
            if (inventory[index] != null && inventory[index].getItem() == ModItems.elementContainer && inventory[index].getItemDamage() == 0)
            {
                emptyStacks++;
            }
        }

        if (requiredEmpty <= emptyStacks) return true;
        return false;
    }
}
