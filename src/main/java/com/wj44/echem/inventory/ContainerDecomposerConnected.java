package com.wj44.echem.inventory;


import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import com.wj44.echem.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Wesley "WJ44" Joosten on 20-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerDecomposerConnected extends ContainerDataBankConnected
{
    private int currentItemBurnTime;
    private int totalCookTime;
    private int cookTime;
    private int decomposerBurnTime;
    private int slots;

    public ContainerDecomposerConnected(InventoryPlayer playerInventory, TileEntityDecomposer tileEntityDecomposer)
    {
        this.tileEntity = tileEntityDecomposer;
        this.tileEntityDataBank = tileEntityDecomposer.getDataBank();
        slots = tileEntityDecomposer.getSlotsAmount();
        this.addSlotToContainer(new Slot(tileEntityDecomposer, tileEntityDecomposer.INPUT_INVENTORY_INDEX, 56, 17));
        this.addSlotToContainer(new SlotMachineFuel(tileEntityDecomposer, tileEntityDecomposer.FUEL_INVENTORY_INDEX, 56, 53));
        this.addSlotToContainer(new SlotDataCard(tileEntityDecomposer, tileEntityDecomposer.DATA_CARD_INVENTORY_INDEX, 17, 35));
        if (slots > 0) this.addSlotToContainer(new SlotMachineOutput(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX1, 116, 17));
        if (slots > 1) this.addSlotToContainer(new SlotMachineOutput(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX2, 134, 17));
        if (slots > 2) this.addSlotToContainer(new SlotMachineOutput(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX3, 116, 35));
        if (slots > 3) this.addSlotToContainer(new SlotMachineOutput(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX4, 134, 35));
        if (slots > 4) this.addSlotToContainer(new SlotMachineOutput(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX5, 116, 53));
        if (slots > 5) this.addSlotToContainer(new SlotMachineOutput(tileEntityDecomposer, tileEntityDecomposer.OUTPUT_INVENTORY_INDEX6, 134, 53));

        addDataBankSlots(tileEntityDecomposer.getDataBank(), 1, -121, 17);
        addPlayerSlots(playerInventory, 8, 84);
    }

    public void onCraftGuiOpened(ICrafting listener)
    {
        super.onCraftGuiOpened(listener);
        listener.func_175173_a(this, this.tileEntity);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.currentItemBurnTime != this.tileEntity.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.getField(2));
            }

            if (this.cookTime != this.tileEntity.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.getField(0));
            }

            if (this.decomposerBurnTime != this.tileEntity.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.getField(1));
            }

            if (this.totalCookTime != this.tileEntity.getField(3))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.getField(3));
            }
        }

        this.currentItemBurnTime = this.tileEntity.getField(2);
        this.cookTime = this.tileEntity.getField(0);
        this.decomposerBurnTime = this.tileEntity.getField(1);
        this.totalCookTime = this.tileEntity.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileEntity.setField(id, data);
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileEntity.isUseableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) //TODO
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
                if (ItemStackHelper.isItemFuel(slotStack))
                {
                    if (!mergeItemStack(slotStack, TileEntityDecomposer.INPUT_INVENTORY_INDEX, TileEntityDecomposer.DATA_CARD_INVENTORY_INDEX, true))
                    {
                        return null;
                    }
                }

                else if (slotStack.getItem() == ModItems.dataCard && slotStack.getTagCompound().getBoolean("isScanned"))
                {
                        if (!mergeItemStack(slotStack, TileEntityDecomposer.DATA_CARD_INVENTORY_INDEX, TileEntityDecomposer.OUTPUT_INVENTORY_INDEX1, false))
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