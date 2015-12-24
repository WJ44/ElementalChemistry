package com.wj44.echem.inventory;

import com.google.common.collect.Sets;
import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityDataBank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Wesley "WJ44" Joosten on 12-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerDataBank extends ContainerEChem
{
    private int dragMode = -1;
    private int dragEvent;
    private final Set dragSlots = Sets.newHashSet();

    private final TileEntityDataBank tileEntityDataBank;

    public ContainerDataBank(InventoryPlayer playerInventory, TileEntityDataBank tileEntityDataBank)
    {
        this.tileEntityDataBank = tileEntityDataBank;

        this.addSlotToContainer(new Slot(tileEntityDataBank, tileEntityDataBank.DATA_CARD_INVENTORY_INDEX, 17, 35)
        {
            @Override
            public boolean isItemValid(ItemStack itemStack)
            {
                return false;
            }

            @Override
            public boolean canTakeStack(EntityPlayer player)
            {
                return false;
            }
        });

        addDataCardSlots(tileEntityDataBank, 1, 44, 17);

        addPlayerSlots(playerInventory, 8, 84);
    }

    public void addDataCardSlots(TileEntityDataBank tileEntityDataBank, int indexOffset, int x, int y)
    {
        //Adds the inventory
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                addSlotToContainer(new Slot(tileEntityDataBank, indexOffset + j + i * 7, x + j * 18, y + i * 18)
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
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tileEntityDataBank.isUseableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (itemstack.getItem() == ModItems.dataCard)
            {
                if (index < tileEntityDataBank.getSizeInventory() && index != 0)
                {
                    if (!this.mergeItemStack(itemstack1, tileEntityDataBank.getSizeInventory(), this.inventorySlots.size(), true))
                    {
                        return null;
                    }
                }
                else if (!this.mergeItemStack(itemstack1, 1, tileEntityDataBank.getSizeInventory(), false))
                {
                    return null;
                }

                if (itemstack1.stackSize == 0)
                {
                    slot.putStack((ItemStack) null);
                }
                else
                {
                    slot.onSlotChanged();
                }
            }
            else
            {
                return null;
            }
        }

        return itemstack;
    }

    /**
     * Handles slot click. Args : slotId, clickedButton, mode (0 = basic click, 1 = shift click, 2 = Hotbar, 3 =
     * pickBlock, 4 = Drop, 5 = ?, 6 = Double click), player
     */
    public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn)
    {
        ItemStack itemstack = null;
        InventoryPlayer inventoryplayer = playerIn.inventory;
        int i1;
        ItemStack itemstack3;

        if (mode == 5)
        {
            int l = this.dragEvent;
            this.dragEvent = getDragEvent(clickedButton);

            if ((l != 1 || this.dragEvent != 2) && l != this.dragEvent)
            {
                this.resetDrag();
            }
            else if (inventoryplayer.getItemStack() == null)
            {
                this.resetDrag();
            }
            else if (this.dragEvent == 0)
            {
                this.dragMode = extractDragMode(clickedButton);

                if (isValidDragMode(this.dragMode, playerIn))
                {
                    this.dragEvent = 1;
                    this.dragSlots.clear();
                }
                else
                {
                    this.resetDrag();
                }
            }
            else if (this.dragEvent == 1)
            {
                Slot slot = (Slot)this.inventorySlots.get(slotId);

                if (slot != null && canAddItemToSlot(slot, inventoryplayer.getItemStack(), true) && slot.isItemValid(inventoryplayer.getItemStack()) && inventoryplayer.getItemStack().stackSize > this.dragSlots.size() && this.canDragIntoSlot(slot))
                {
                    this.dragSlots.add(slot);
                }
            }
            else if (this.dragEvent == 2)
            {
                if (!this.dragSlots.isEmpty())
                {
                    itemstack3 = inventoryplayer.getItemStack().copy();
                    i1 = inventoryplayer.getItemStack().stackSize;
                    Iterator iterator = this.dragSlots.iterator();

                    while (iterator.hasNext())
                    {
                        Slot slot1 = (Slot)iterator.next();

                        if (slot1 != null && canAddItemToSlot(slot1, inventoryplayer.getItemStack(), true) && slot1.isItemValid(inventoryplayer.getItemStack()) && inventoryplayer.getItemStack().stackSize >= this.dragSlots.size() && this.canDragIntoSlot(slot1))
                        {
                            ItemStack itemstack1 = itemstack3.copy();
                            int j1 = slot1.getHasStack() ? slot1.getStack().stackSize : 0;
                            computeStackSize(this.dragSlots, this.dragMode, itemstack1, j1);

                            if (itemstack1.stackSize > itemstack1.getMaxStackSize())
                            {
                                itemstack1.stackSize = itemstack1.getMaxStackSize();
                            }

                            if (itemstack1.stackSize > slot1.getItemStackLimit(itemstack1))
                            {
                                itemstack1.stackSize = slot1.getItemStackLimit(itemstack1);
                            }

                            i1 -= itemstack1.stackSize - j1;
                            slot1.putStack(itemstack1);
                        }
                    }

                    itemstack3.stackSize = i1;

                    if (itemstack3.stackSize <= 0)
                    {
                        itemstack3 = null;
                    }

                    inventoryplayer.setItemStack(itemstack3);
                }

                this.resetDrag();
            }
            else
            {
                this.resetDrag();
            }
        }
        else if (this.dragEvent != 0)
        {
            this.resetDrag();
        }
        else
        {
            Slot slot2;
            int l1;
            ItemStack itemstack5;

            if ((mode == 0 || mode == 1) && (clickedButton == 0 || clickedButton == 1))
            {
                if (slotId == -999)
                {
                    if (inventoryplayer.getItemStack() != null)
                    {
                        if (clickedButton == 0)
                        {
                            playerIn.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack(), true);
                            inventoryplayer.setItemStack((ItemStack)null);
                        }

                        if (clickedButton == 1)
                        {
                            playerIn.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack().splitStack(1), true);

                            if (inventoryplayer.getItemStack().stackSize == 0)
                            {
                                inventoryplayer.setItemStack((ItemStack)null);
                            }
                        }
                    }
                }
                else if (mode == 1)
                {
                    if (slotId < 0)
                    {
                        return null;
                    }

                    slot2 = (Slot)this.inventorySlots.get(slotId);

                    if (slot2 != null && slot2.canTakeStack(playerIn))
                    {
                        itemstack3 = this.transferStackInSlot(playerIn, slotId);

                        if (itemstack3 != null)
                        {
                            Item item = itemstack3.getItem();
                            itemstack = itemstack3.copy();

                            if (slot2.getStack() != null && slot2.getStack().getItem() == item)
                            {
                                this.retrySlotClick(slotId, clickedButton, true, playerIn);
                            }
                        }
                    }
                }
                else
                {
                    if (slotId < 0)
                    {
                        return null;
                    }

                    slot2 = (Slot)this.inventorySlots.get(slotId);

                    if (slot2 != null)
                    {
                        itemstack3 = slot2.getStack();
                        ItemStack itemstack4 = inventoryplayer.getItemStack();

                        if (itemstack3 != null)
                        {
                            itemstack = itemstack3.copy();
                        }

                        if (itemstack3 == null)
                        {
                            if (itemstack4 != null && slot2.isItemValid(itemstack4))
                            {
                                l1 = clickedButton == 0 ? itemstack4.stackSize : 1;

                                if (l1 > slot2.getItemStackLimit(itemstack4))
                                {
                                    l1 = slot2.getItemStackLimit(itemstack4);
                                }

                                if (itemstack4.stackSize >= l1)
                                {
                                    slot2.putStack(itemstack4.splitStack(l1));
                                }

                                if (itemstack4.stackSize == 0)
                                {
                                    inventoryplayer.setItemStack((ItemStack)null);
                                }
                            }
                        }
                        else if (slot2.canTakeStack(playerIn))
                        {
                            if (itemstack4 == null)
                            {
                                l1 = clickedButton == 0 ? itemstack3.stackSize : (itemstack3.stackSize + 1) / 2;
                                itemstack5 = slot2.decrStackSize(l1);
                                inventoryplayer.setItemStack(itemstack5);

                                if (itemstack3.stackSize == 0)
                                {
                                    slot2.putStack((ItemStack)null);
                                }

                                slot2.onPickupFromSlot(playerIn, inventoryplayer.getItemStack());
                            }
                            else if (slot2.isItemValid(itemstack4))
                            {
                                if (itemstack3.getItem() == itemstack4.getItem() && itemstack3.getMetadata() == itemstack4.getMetadata() && ItemStack.areItemStackTagsEqual(itemstack3, itemstack4))
                                {
                                    l1 = clickedButton == 0 ? itemstack4.stackSize : 1;

                                    if (l1 > slot2.getItemStackLimit(itemstack4) - itemstack3.stackSize)
                                    {
                                        l1 = slot2.getItemStackLimit(itemstack4) - itemstack3.stackSize;
                                    }

                                    if (l1 > itemstack4.getMaxStackSize() - itemstack3.stackSize)
                                    {
                                        l1 = itemstack4.getMaxStackSize() - itemstack3.stackSize;
                                    }

                                    itemstack4.splitStack(l1);

                                    if (itemstack4.stackSize == 0)
                                    {
                                        inventoryplayer.setItemStack((ItemStack)null);
                                    }

                                    itemstack3.stackSize += l1;
                                }
                                else if (itemstack4.stackSize <= slot2.getItemStackLimit(itemstack4))
                                {
                                    slot2.putStack(itemstack4);
                                    inventoryplayer.setItemStack(itemstack3);
                                }
                            }
                            else if (itemstack3.getItem() == itemstack4.getItem() && itemstack4.getMaxStackSize() > 1 && (!itemstack3.getHasSubtypes() || itemstack3.getMetadata() == itemstack4.getMetadata()) && ItemStack.areItemStackTagsEqual(itemstack3, itemstack4))
                            {
                                l1 = itemstack3.stackSize;

                                if (l1 > 0 && l1 + itemstack4.stackSize <= itemstack4.getMaxStackSize())
                                {
                                    itemstack4.stackSize += l1;
                                    itemstack3 = slot2.decrStackSize(l1);

                                    if (itemstack3.stackSize == 0)
                                    {
                                        slot2.putStack((ItemStack)null);
                                    }

                                    slot2.onPickupFromSlot(playerIn, inventoryplayer.getItemStack());
                                }
                            }
                        }

                        slot2.onSlotChanged();
                    }
                }
            }
            else if (mode == 2 && clickedButton >= 0 && clickedButton < 9)
            {
                slot2 = (Slot)this.inventorySlots.get(slotId);

                if (slot2.canTakeStack(playerIn))
                {
                    itemstack3 = inventoryplayer.getStackInSlot(clickedButton);
                    boolean flag = itemstack3 == null || slot2.inventory == inventoryplayer && slot2.isItemValid(itemstack3);
                    l1 = -1;

                    if (!flag)
                    {
                        l1 = inventoryplayer.getFirstEmptyStack();
                        flag |= l1 > -1;
                    }

                    if (slot2.getHasStack() && flag)
                    {
                        itemstack5 = slot2.getStack();
                        inventoryplayer.setInventorySlotContents(clickedButton, itemstack5.copy());

                        if ((slot2.inventory != inventoryplayer || !slot2.isItemValid(itemstack3)) && itemstack3 != null)
                        {
                            if (l1 > -1)
                            {
                                inventoryplayer.addItemStackToInventory(itemstack3);
                                slot2.decrStackSize(itemstack5.stackSize);
                                slot2.putStack((ItemStack)null);
                                slot2.onPickupFromSlot(playerIn, itemstack5);
                            }
                        }
                        else
                        {
                            slot2.decrStackSize(itemstack5.stackSize);
                            slot2.putStack(itemstack3);
                            slot2.onPickupFromSlot(playerIn, itemstack5);
                        }
                    }
                    else if (!slot2.getHasStack() && itemstack3 != null && slot2.isItemValid(itemstack3))
                    {
                        inventoryplayer.setInventorySlotContents(clickedButton, (ItemStack)null);
                        slot2.putStack(itemstack3);
                    }
                }
            }
            //On middle mouse click, select item stack
            else if (mode == 3)
            {
                if (playerIn.capabilities.isCreativeMode && inventoryplayer.getItemStack() == null && slotId >= 0)
                {
                    slot2 = (Slot) this.inventorySlots.get(slotId);

                    if (slot2 != null && slot2.getHasStack())
                    {
                        itemstack3 = slot2.getStack().copy();
                        itemstack3.stackSize = itemstack3.getMaxStackSize();
                        inventoryplayer.setItemStack(itemstack3);
                    }
                }
                else if (slotId < tileEntityDataBank.getSizeInventory() && slotId != 0)
                {
                    tileEntityDataBank.setInventorySlotContents(tileEntityDataBank.DATA_CARD_INVENTORY_INDEX, getSlot(slotId).getStack());
                }
            }
            else if (mode == 4 && inventoryplayer.getItemStack() == null && slotId >= 0)
            {
                slot2 = (Slot)this.inventorySlots.get(slotId);

                if (slot2 != null && slot2.getHasStack() && slot2.canTakeStack(playerIn))
                {
                    itemstack3 = slot2.decrStackSize(clickedButton == 0 ? 1 : slot2.getStack().stackSize);
                    slot2.onPickupFromSlot(playerIn, itemstack3);
                    playerIn.dropPlayerItemWithRandomChoice(itemstack3, true);
                }
            }
            else if (mode == 6 && slotId >= 0)
            {
                slot2 = (Slot)this.inventorySlots.get(slotId);
                itemstack3 = inventoryplayer.getItemStack();

                if (itemstack3 != null && (slot2 == null || !slot2.getHasStack() || !slot2.canTakeStack(playerIn)))
                {
                    i1 = clickedButton == 0 ? 0 : this.inventorySlots.size() - 1;
                    l1 = clickedButton == 0 ? 1 : -1;

                    for (int i2 = 0; i2 < 2; ++i2)
                    {
                        for (int j2 = i1; j2 >= 0 && j2 < this.inventorySlots.size() && itemstack3.stackSize < itemstack3.getMaxStackSize(); j2 += l1)
                        {
                            Slot slot3 = (Slot)this.inventorySlots.get(j2);

                            if (slot3.getHasStack() && canAddItemToSlot(slot3, itemstack3, true) && slot3.canTakeStack(playerIn) && this.canMergeSlot(itemstack3, slot3) && (i2 != 0 || slot3.getStack().stackSize != slot3.getStack().getMaxStackSize()))
                            {
                                int k1 = Math.min(itemstack3.getMaxStackSize() - itemstack3.stackSize, slot3.getStack().stackSize);
                                ItemStack itemstack2 = slot3.decrStackSize(k1);
                                itemstack3.stackSize += k1;

                                if (itemstack2.stackSize <= 0)
                                {
                                    slot3.putStack((ItemStack)null);
                                }

                                slot3.onPickupFromSlot(playerIn, itemstack2);
                            }
                        }
                    }
                }

                this.detectAndSendChanges();
            }
        }

        return itemstack;
    }
}
