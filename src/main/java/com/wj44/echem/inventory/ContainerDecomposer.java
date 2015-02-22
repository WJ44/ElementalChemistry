package com.wj44.echem.inventory;

import com.wj44.echem.tileentity.TileEntityDecomposer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by Wesley "WJ44" Joosten on 20-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerDecomposer extends ContainerEChem
{
    private TileEntityDecomposer tileEntityDecomposer;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerDecomposer(InventoryPlayer inventoryPlayer, TileEntityDecomposer tileEntityDecomposer)
    {
        this.tileEntityDecomposer = tileEntityDecomposer;
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.INPUT_INVENTORY_INDEX, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.FUEL_INVENTORY_INDEX, 56, 53));
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX, 116, 17));
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX+1, 134, 17));
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX+2, 116, 35));
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX+3, 134, 35));
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX+4, 116, 53));
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX+5, 134, 53));


        addPlayerSlots(inventoryPlayer, 8, 84);
    }

    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileEntityDecomposer.decomposerCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileEntityDecomposer.decomposerBurnTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileEntityDecomposer.currentItemBurnTime);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileEntityDecomposer.decomposerCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntityDecomposer.decomposerCookTime);
            }

            if (this.lastBurnTime != this.tileEntityDecomposer.decomposerBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntityDecomposer.decomposerBurnTime);
            }

            if (this.lastItemBurnTime != this.tileEntityDecomposer.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntityDecomposer.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.tileEntityDecomposer.decomposerCookTime;
        this.lastBurnTime = this.tileEntityDecomposer.decomposerBurnTime;
        this.lastItemBurnTime = this.tileEntityDecomposer.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.tileEntityDecomposer.decomposerCookTime = updatedValue;
        }

        if (valueType == 1)
        {
            this.tileEntityDecomposer.decomposerBurnTime = updatedValue;
        }

        if (valueType == 2)
        {
            this.tileEntityDecomposer.currentItemBurnTime = updatedValue;
        }
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileEntityDecomposer.isUseableByPlayer(player);
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
             * Shift clicking out of the decomposer, into the inventory
             */
            if (slotIndex < TileEntityDecomposer.INVENTORY_SIZE)
            {
                if (!mergeItemStack(slotStack, TileEntityDecomposer.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            /**
             * Shift clicking out of the inventory into the decomposer
             */
            else
            {
                /**
                 * if the item is a fuel, try to put it in either the input slot, or fuel slot, in reverse
                 */
                if (TileEntityDecomposer.isItemFuel(slotStack))
                {
                    if (!mergeItemStack(slotStack, TileEntityDecomposer.INPUT_INVENTORY_INDEX, TileEntityDecomposer.OUTPUT_INVENTORY_INDEX, true))
                    {
                        return null;
                    }
                }
                /**
                 * try to put it in the input slot
                 */
                else
                {
                    if (!mergeItemStack(slotStack, TileEntityDecomposer.INPUT_INVENTORY_INDEX, TileEntityDecomposer.FUEL_INVENTORY_INDEX, false))
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
