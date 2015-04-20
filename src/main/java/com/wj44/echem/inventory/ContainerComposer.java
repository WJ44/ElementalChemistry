package com.wj44.echem.inventory;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityComposer;
import com.wj44.echem.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Wesley "WJ44" Joosten on 6-3-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerComposer extends ContainerEChem
{
    private final TileEntityComposer tileComposer;
    private int currentItemBurnTime;
    private int totalCookTime;
    private int cookTime;
    private int composerBurnTime;

    public ContainerComposer(InventoryPlayer playerInventory, final TileEntityComposer tileEntityComposer)
    {
        this.tileComposer = tileEntityComposer;
        this.addSlotToContainer(new Slot(tileEntityComposer, tileEntityComposer.INPUT_INVENTORY_INDEX1, 8, 17));
        this.addSlotToContainer(new Slot(tileEntityComposer, tileEntityComposer.INPUT_INVENTORY_INDEX2, 26, 17));
        this.addSlotToContainer(new Slot(tileEntityComposer, tileEntityComposer.INPUT_INVENTORY_INDEX3, 8, 35));
        this.addSlotToContainer(new Slot(tileEntityComposer, tileEntityComposer.INPUT_INVENTORY_INDEX4, 26, 35));
        this.addSlotToContainer(new Slot(tileEntityComposer, tileEntityComposer.INPUT_INVENTORY_INDEX5, 8, 53));
        this.addSlotToContainer(new Slot(tileEntityComposer, tileEntityComposer.INPUT_INVENTORY_INDEX6, 26, 53));
        this.addSlotToContainer(new SlotMachineFuel(tileEntityComposer, tileEntityComposer.FUEL_INVENTORY_INDEX, 56, 53));
        this.addSlotToContainer(new Slot(tileEntityComposer, tileEntityComposer.DATA_CARD_INVENTORY_INDEX, 56, 17)
        {
            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                if (tileEntityComposer.dataBankConnected)
                {
                    return false;
                }
                if (itemStack.getItem() == ModItems.dataCard)
                {
                    return itemStack.getTagCompound().getBoolean("isScanned");
                }

                return false;
            }

            @Override
            public boolean canTakeStack(EntityPlayer player)
            {
                if (tileEntityComposer.dataBankConnected)
                {
                    return false;
                }
                return true;
            }
        });
        this.addSlotToContainer(new SlotMachineOutput(tileEntityComposer, tileEntityComposer.OUTPUT_INVENTORY_INDEX, 116, 35));
        addPlayerSlots(playerInventory, 8, 84);
    }

    public void onCraftGuiOpened(ICrafting listener)
    {
        super.onCraftGuiOpened(listener);
        listener.func_175173_a(this, this.tileComposer);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.currentItemBurnTime != this.tileComposer.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileComposer.getField(2));
            }

            if (this.cookTime != this.tileComposer.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileComposer.getField(0));
            }

            if (this.composerBurnTime != this.tileComposer.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileComposer.getField(1));
            }

            if (this.totalCookTime != this.tileComposer.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileComposer.getField(3));
            }
        }

        this.currentItemBurnTime = this.tileComposer.getField(2);
        this.cookTime = this.tileComposer.getField(0);
        this.composerBurnTime = this.tileComposer.getField(1);
        this.totalCookTime = this.tileComposer.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileComposer.setField(id, data);
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileComposer.isUseableByPlayer(playerIn);
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
             * Shift clicking out of the composer, into the inventory
             */
            if (slotIndex < TileEntityComposer.INVENTORY_SIZE)
            {
                if (!mergeItemStack(slotStack, TileEntityComposer.INVENTORY_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            /**
             * Shift clicking out of the inventory into the composer
             */
            else
            {
                /**
                 * if the item is a fuel, try to put it in either the input slot, or fuel slot, in reverse
                 */
                if (ItemStackHelper.isItemFuel(slotStack))
                {
                    if (!mergeItemStack(slotStack, TileEntityComposer.INPUT_INVENTORY_INDEX1, TileEntityComposer.DATA_CARD_INVENTORY_INDEX, true))
                    {
                        return null;
                    }
                }

                else if (slotStack.getItem() == ModItems.dataCard && slotStack.getTagCompound().getBoolean("isScanned"))
                {
                    if (!mergeItemStack(slotStack, TileEntityComposer.DATA_CARD_INVENTORY_INDEX, TileEntityComposer.OUTPUT_INVENTORY_INDEX, false))
                    {
                        return null;
                    }
                }
                /**
                 * try to put it in the input slot
                 */
                else
                {
                    if (!mergeItemStack(slotStack, TileEntityComposer.INPUT_INVENTORY_INDEX1, TileEntityComposer.FUEL_INVENTORY_INDEX, false))
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
