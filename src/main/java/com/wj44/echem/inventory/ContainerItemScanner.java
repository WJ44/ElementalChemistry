package com.wj44.echem.inventory;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityItemScanner;
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
public class ContainerItemScanner extends ContainerEChem
{
    public ContainerItemScanner(InventoryPlayer playerInventory, IInventory itemScannerInventory)
    {
        this.inventory = itemScannerInventory;

        this.addSlotToContainer(new Slot(itemScannerInventory, TileEntityItemScanner.INPUT_INVENTORY_INDEX, 56, 17));
        this.addSlotToContainer(new Slot(itemScannerInventory, TileEntityItemScanner.OUTPUT_INVENTORY_INDEX, 116, 35)
        {
            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                if (itemStack.getItem() == ModItems.dataCard)
                {
                    return !itemStack.getTagCompound().getBoolean("isScanned");
                }

                return false;
            }
        });
        addPlayerSlots(playerInventory, 8, 84);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            /**
             * Shift clicking out of the itemScanner, into the inventory
             */
            if (slotIndex < inventory.getSizeInventory())
            {
                if (!mergeItemStack(slotStack, inventory.getSizeInventory(), inventorySlots.size(), false))
                {
                    return null;
                }
            }
            /**
             * Shift clicking out of the inventory into the itemScanner
             */
            else
            {
                /**
                 * if the item is a data card, try to put it in either the input slot, or fuel slot, in reverse
                 */
                if (slotStack.getItem() == ModItems.dataCard && !slotStack.getTagCompound().getBoolean("isScanned"))
                {
                    if (!mergeItemStack(slotStack, TileEntityItemScanner.INPUT_INVENTORY_INDEX, TileEntityItemScanner.OUTPUT_INVENTORY_INDEX+1, true))
                    {
                        return null;
                    }
                }

                /**
                 * try to put it in the input slot
                 */
                else
                {
                    if (!mergeItemStack(slotStack, TileEntityItemScanner.INPUT_INVENTORY_INDEX, TileEntityItemScanner.INPUT_INVENTORY_INDEX+1, false))
                    {
                        return null;
                    }
                }
            }

            if (slotStack.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
