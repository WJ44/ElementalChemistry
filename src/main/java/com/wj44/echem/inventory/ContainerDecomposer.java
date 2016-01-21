package com.wj44.echem.inventory;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 21/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerDecomposer extends ContainerEChem
{
    public ContainerDecomposer(InventoryPlayer playerInventory, IInventory decomposerInventory)
    {
        this.inventory = decomposerInventory;

        this.addSlotToContainer(new Slot(decomposerInventory, TileEntityDecomposer.INPUT_INVENTORY_INDEX, 56, 17));
        this.addSlotToContainer(new Slot(decomposerInventory, TileEntityDecomposer.DATA_CARD_INVENTORY_INDEX, 17, 35)
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
        this.addSlotToContainer(new SlotDecomposerOutput(decomposerInventory, TileEntityDecomposer.OUTPUT[0], 116, 17));
        this.addSlotToContainer(new SlotDecomposerOutput(decomposerInventory, TileEntityDecomposer.OUTPUT[1], 134, 17));
        this.addSlotToContainer(new SlotDecomposerOutput(decomposerInventory, TileEntityDecomposer.OUTPUT[2], 116, 35));
        this.addSlotToContainer(new SlotDecomposerOutput(decomposerInventory, TileEntityDecomposer.OUTPUT[3], 134, 35));
        this.addSlotToContainer(new SlotDecomposerOutput(decomposerInventory, TileEntityDecomposer.OUTPUT[4], 116, 53));
        this.addSlotToContainer(new SlotDecomposerOutput(decomposerInventory, TileEntityDecomposer.OUTPUT[5], 134, 53));
        addPlayerSlots(playerInventory, 8, 84);
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
            if (slotIndex < inventory.getSizeInventory())
            {
                if (!mergeItemStack(slotStack, inventory.getSizeInventory(), inventorySlots.size(), false))
                {
                    return null;
                }
            }
            /**
             * Shift clicking out of the inventory into the decomposer
             */
            else
            {
                if (slotStack.getItem() == ModItems.dataCard && slotStack.getTagCompound().getBoolean("isScanned"))
                {
                    if (!mergeItemStack(slotStack, TileEntityDecomposer.DATA_CARD_INVENTORY_INDEX, TileEntityDecomposer.OUTPUT_INVENTORY_STARTINDEX, false))
                    {
                        return null;
                    }
                }
                else if (slotStack.getItem() == ModItems.elementContainer)
                {
                    if (!mergeItemStack(slotStack, TileEntityDecomposer.OUTPUT_INVENTORY_STARTINDEX, TileEntityDecomposer.OUTPUT[5]+1, false))
                    {
                        return null;
                    }
                }
                /**
                 * try to put it in the input slot
                 */
                else
                {
                    if (!mergeItemStack(slotStack, TileEntityDecomposer.INPUT_INVENTORY_INDEX, TileEntityDecomposer.DATA_CARD_INVENTORY_INDEX, false))
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

class SlotDecomposerOutput extends Slot
{

    public SlotDecomposerOutput(IInventory inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        return itemStack.getItem() == ModItems.elementContainer;
    }
}

