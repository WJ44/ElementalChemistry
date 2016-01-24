package com.wj44.echem.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 16/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public abstract class ContainerEChem extends Container
{
    protected IInventory inventory;

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return inventory.isUseableByPlayer(playerIn);
    }

    /**
     * Adds the player inventory and the hotbar
     * @param playerInventory
     * @param x The x-position where the inventory should start (default 8)
     * @param y The y-position where the inventory should start (default 84)
     */
    protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y)
    {
        //Adds the inventory
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }

        //Adds the hotbar
        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(playerInventory, i, x + i * 18, y + 58));
        }
    }

    //When using mergeItemStack, the first int is the first slot it tries to use, the second the last + 1, the boolean is whether to do it in reverse or not

    /**
     * @param stack
     * @param startIndex first slot tried
     * @param endIndex last slot tried + 1
     * @param reverseDirection reverse
     * @return
     */
    @Override
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection)
    {
        boolean flag = false;
        int i = startIndex;

        if (reverseDirection)
        {
            i = endIndex - 1;
        }

        if (stack.isStackable())
        {
            while (stack.stackSize > 0 && (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex))
            {
                Slot slot = (Slot)this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();

                if (itemstack != null && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack) && itemstack.stackSize < slot.getSlotStackLimit())
                {
                    int j = itemstack.stackSize + stack.stackSize;

                    if (j <= getStackLimit(stack, slot))
                    {
                        stack.stackSize = 0;
                        itemstack.stackSize = j;
                        slot.onSlotChanged();
                        flag = true;
                    }
                    else if (itemstack.stackSize < getStackLimit(stack, slot))
                    {
                        stack.stackSize -= getStackLimit(stack, slot) - itemstack.stackSize;
                        itemstack.stackSize = getStackLimit(stack, slot);
                        slot.onSlotChanged();
                        flag = true;
                    }
                }

                if (reverseDirection)
                {
                    --i;
                }
                else
                {
                    ++i;
                }
            }
        }

        if (stack.stackSize > 0)
        {
            if (reverseDirection)
            {
                i = endIndex - 1;
            }
            else
            {
                i = startIndex;
            }

            while (!reverseDirection && i < endIndex || reverseDirection && i >= startIndex)
            {
                Slot slot1 = (Slot)this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();

                if (itemstack1 == null && slot1.isItemValid(stack)) // Forge: Make sure to respect isItemValid in the slot.
                {
                    itemstack1 = new ItemStack(stack.getItem(), 0);
                    if (stack.stackSize <= slot1.getSlotStackLimit())
                    {
                        itemstack1.stackSize = stack.stackSize;
                        slot1.putStack(itemstack1);
                        stack.stackSize = 0;
                        flag = true;
                    }
                    else if (stack.stackSize > slot1.getSlotStackLimit())
                    {
                        itemstack1.stackSize = slot1.getSlotStackLimit();
                        slot1.putStack(itemstack1);
                        stack.stackSize = stack.stackSize - itemstack1.stackSize;
                        flag = true;
                    }
                    if (itemstack1.stackSize == 0)
                    {
                        slot1.putStack(null);
                    }
                }

                if (reverseDirection)
                {
                    --i;
                }
                else
                {
                    ++i;
                }
            }
        }

        return flag;
    }

    private int getStackLimit(ItemStack itemStack, Slot slot)
    {
        if (itemStack.stackSize > slot.getSlotStackLimit())
        {
            return slot.getSlotStackLimit();
        }
        return itemStack.getMaxStackSize();
    }
}
