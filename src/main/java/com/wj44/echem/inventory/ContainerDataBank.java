package com.wj44.echem.inventory;

import com.wj44.echem.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 15/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerDataBank extends ContainerEChem
{
    public ContainerDataBank(InventoryPlayer playerInventory, IInventory dataBankInventory)
    {
        this.inventory = dataBankInventory;

        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                addSlotToContainer(new Slot(dataBankInventory, j + i * 7, 44 + j * 18, 17 + i * 18)
                {
                    @Override
                    public boolean isItemValid(ItemStack itemStack)
                    {
                        if (itemStack.getItem() == ModItems.dataCard)
                        {
                            return itemStack.getTagCompound().getBoolean("isScanned");
                        }

                        return false;
                    }
                });
            }
        }

        addPlayerSlots(playerInventory, 8, 84);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (itemstack.getItem() == ModItems.dataCard)
            {
                if (index < inventory.getSizeInventory())
                {
                    if (!this.mergeItemStack(itemstack1,  inventory.getSizeInventory(), this.inventorySlots.size(), true))
                    {
                        return null;
                    }
                }
                else if (!this.mergeItemStack(itemstack1, 0, inventory.getSizeInventory(), false))
                {
                    return null;
                }

                if (itemstack1.stackSize == 0)
                {
                    slot.putStack(null);
                }
                else
                {
                    slot.onSlotChanged();
                }

                if (itemstack1.stackSize == itemstack.stackSize)
                {
                    return null;
                }

                slot.onPickupFromSlot(playerIn, itemstack1);
            }
            else
            {
                return null;
            }
        }

        return itemstack;
    }

    public void selectSlot(int slot)
    {
        inventory.setField(0, slot);
    }

}
