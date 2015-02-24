package com.wj44.echem.inventory;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 24-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerItemScanner extends ContainerEChem
{
    private TileEntityItemScanner tileEntityItemScanner;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerItemScanner(InventoryPlayer inventoryPlayer, TileEntityItemScanner tileEntityItemScanner)
    {
        this.tileEntityItemScanner = tileEntityItemScanner;
        this.addSlotToContainer(new Slot(tileEntityItemScanner, tileEntityItemScanner.INPUT_INVENTORY_INDEX, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityItemScanner, tileEntityItemScanner.FUEL_INVENTORY_INDEX, 56, 53));
        this.addSlotToContainer(new Slot(tileEntityItemScanner, tileEntityItemScanner.OUTPUT_INVENTORY_INDEX, 116, 35)
        {
            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                if (itemStack.getItem() == ModItems.dataCard)
                {
                    return !itemStack.stackTagCompound.getBoolean("isScanned");
                }

                return false;
            }
        });

        addPlayerSlots(inventoryPlayer, 8, 84);
    }

    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityItemScanner.itemScannerCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityItemScanner.itemScannerBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityItemScanner.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileEntityItemScanner.itemScannerCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityItemScanner.itemScannerCookTime);
            }

            if (this.lastBurnTime != this.tileEntityItemScanner.itemScannerBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityItemScanner.itemScannerBurnTime);
            }

            if (this.lastItemBurnTime != this.tileEntityItemScanner.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntityItemScanner.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileEntityItemScanner.itemScannerCookTime;
        this.lastBurnTime = this.tileEntityItemScanner.itemScannerBurnTime;
        this.lastItemBurnTime = this.tileEntityItemScanner.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityItemScanner.itemScannerCookTime = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityItemScanner.itemScannerBurnTime = updatedValue;
        }

        if (valueType == 2)
        {
            this.tileEntityItemScanner.currentItemBurnTime = updatedValue;
        }
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntityItemScanner.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            /**
             * Shift clicking out of the itemScanner, into the inventory
             */
            if (slotIndex < TileEntityItemScanner.INVENTORY_SIZE)
            {
                if (!mergeItemStack(slotStack, TileEntityItemScanner.INVENTORY_SIZE, inventorySlots.size(), false))
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
                 * if the item is a fuel, try to put it in either the input slot, or fuel slot, in reverse
                 */
                if (TileEntityItemScanner.isItemFuel(slotStack))
                {
                    if (!mergeItemStack(slotStack, TileEntityItemScanner.INPUT_INVENTORY_INDEX, TileEntityItemScanner.OUTPUT_INVENTORY_INDEX, true))
                    {
                        return null;
                    }
                }

                /**
                 * if the item is a data card, try to put it in either the input slot, or fuel slot, in reverse
                 */
                if (slotStack.getItem() == ModItems.dataCard && !slotStack.stackTagCompound.getBoolean("isScanned"))
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
                    if (!mergeItemStack(slotStack, TileEntityItemScanner.INPUT_INVENTORY_INDEX, TileEntityItemScanner.FUEL_INVENTORY_INDEX, false))
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
