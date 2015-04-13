package com.wj44.echem.inventory;

import com.wj44.echem.tileentity.TileEntityDataBank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 12-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerDataBank extends ContainerEChem
{
    private final TileEntityDataBank tileEntityDataBank;
    private int numRows;

    public ContainerDataBank(InventoryPlayer playerInventory, TileEntityDataBank tileEntityDataBank)
    {
        this.tileEntityDataBank = tileEntityDataBank;

        this.numRows = tileEntityDataBank.getSizeInventory() / 9;

        int j;
        int k;
        for (j = 0; j < this.numRows; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(tileEntityDataBank, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        addPlayerSlots(playerInventory, 8, 84);
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileEntityDataBank.isUseableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
