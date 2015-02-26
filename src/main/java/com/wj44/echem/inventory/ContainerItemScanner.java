package com.wj44.echem.inventory;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Wesley "WJ44" Joosten on 24-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerItemScanner extends ContainerEChem
{
    private final TileEntityItemScanner tileItemScanner;
    private int currentItemBurnTime;
    private int field_178153_g;
    private int itemScannerCookTime;
    private int itemScannerBurnTime;

    public ContainerItemScanner(InventoryPlayer playerInventory, TileEntityItemScanner tileEntityItemScanner)
    {
        this.tileItemScanner = tileEntityItemScanner;
        this.addSlotToContainer(new Slot(tileEntityItemScanner, tileEntityItemScanner.INPUT_INVENTORY_INDEX, 56, 17));
        this.addSlotToContainer(new SlotMachineFuel(tileEntityItemScanner, tileEntityItemScanner.FUEL_INVENTORY_INDEX, 56, 53));
        this.addSlotToContainer(new SlotMachineOutput(tileEntityItemScanner, tileEntityItemScanner.OUTPUT_INVENTORY_INDEX, 116, 35));


        addPlayerSlots(playerInventory, 8, 84);
    }

    public void onCraftGuiOpened(ICrafting listener)
    {
        super.onCraftGuiOpened(listener);
        listener.func_175173_a(this, this.tileItemScanner);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.currentItemBurnTime != this.tileItemScanner.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileItemScanner.getField(2));
            }

            if (this.itemScannerCookTime != this.tileItemScanner.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileItemScanner.getField(0));
            }

            if (this.itemScannerBurnTime != this.tileItemScanner.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileItemScanner.getField(1));
            }

            if (this.field_178153_g != this.tileItemScanner.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileItemScanner.getField(3));
            }
        }

        this.currentItemBurnTime = this.tileItemScanner.getField(2);
        this.itemScannerCookTime = this.tileItemScanner.getField(0);
        this.itemScannerBurnTime = this.tileItemScanner.getField(1);
        this.field_178153_g = this.tileItemScanner.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileItemScanner.setField(id, data);
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileItemScanner.isUseableByPlayer(playerIn);
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
