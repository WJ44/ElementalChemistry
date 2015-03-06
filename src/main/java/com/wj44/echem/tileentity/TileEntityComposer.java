package com.wj44.echem.tileentity;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.inventory.ContainerComposer;
import com.wj44.echem.reference.Elements;
import com.wj44.echem.reference.Names;
import com.wj44.echem.util.ElementHelper;
import com.wj44.echem.util.ItemElementDamageValueHelper;
import com.wj44.echem.util.ItemStackHelper;
import com.wj44.echem.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.logging.Log;

/**
 * Created by Wesley "WJ44" Joosten on 6-3-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityComposer extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    public static final int INVENTORY_SIZE = 9;
    public static final int INPUT_INVENTORY_INDEX1 = 0;
    public static final int INPUT_INVENTORY_INDEX2 = INPUT_INVENTORY_INDEX1 + 1;
    public static final int INPUT_INVENTORY_INDEX3 = INPUT_INVENTORY_INDEX2 + 1;
    public static final int INPUT_INVENTORY_INDEX4 = INPUT_INVENTORY_INDEX3 + 1;
    public static final int INPUT_INVENTORY_INDEX5 = INPUT_INVENTORY_INDEX4 + 1;
    public static final int INPUT_INVENTORY_INDEX6 = INPUT_INVENTORY_INDEX5 + 1;
    public static final int FUEL_INVENTORY_INDEX = INPUT_INVENTORY_INDEX6 + 1;
    public static final int DATA_CARD_INVENTORY_INDEX = FUEL_INVENTORY_INDEX +1;
    public static final int OUTPUT_INVENTORY_INDEX = DATA_CARD_INVENTORY_INDEX +1;
    public static final int input[] = {INPUT_INVENTORY_INDEX1, INPUT_INVENTORY_INDEX2, INPUT_INVENTORY_INDEX3, INPUT_INVENTORY_INDEX4, INPUT_INVENTORY_INDEX5, INPUT_INVENTORY_INDEX6};
    /**
     * The ItemStacks that hold the items currently being used in the composer
     */
    private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
    /**
     * The number of ticks that the composer will keep burning
     */
    private int composerBurnTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the composer burning for
     */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String composerCustomName;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int index)
    {
        return this.inventory[index];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int index, int count)
    {
        if (this.inventory[index] != null)
        {
            ItemStack itemstack;

            if (this.inventory[index].stackSize <= count)
            {
                itemstack = this.inventory[index];
                this.inventory[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[index].splitStack(count);

                if (this.inventory[index].stackSize == 0)
                {
                    this.inventory[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.inventory[index] != null)
        {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        boolean flag = stack != null && stack.isItemEqual(this.inventory[index]) && ItemStack.areItemStackTagsEqual(stack, this.inventory[index]);
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.totalCookTime = 200;
            this.cookTime = 0;
            this.markDirty();
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    public String getCommandSenderName()
    {
        return this.hasCustomName() ? this.composerCustomName : Names.Containers.COMPOSER;
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.composerCustomName != null && this.composerCustomName.length() > 0;
    }

    public void setCustomInventoryName(String name)
    {
        this.composerCustomName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
        inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slot = tagCompound.getByte("Slot");

            if (slot >= 0 && slot < inventory.length)
            {
                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }

        this.composerBurnTime = nbtTagCompound.getShort("BurnTime");
        this.cookTime = nbtTagCompound.getShort("CookTime");
        this.totalCookTime = nbtTagCompound.getShort("CookTimeTotal");
        this.currentItemBurnTime = ItemStackHelper.getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

        if (nbtTagCompound.hasKey("CustomName", 8))
        {
            this.composerCustomName = nbtTagCompound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("BurnTime", (short) this.composerBurnTime);
        nbtTagCompound.setShort("CookTime", (short) this.cookTime);
        nbtTagCompound.setShort("CookTimeTotal", (short) this.totalCookTime);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < inventory.length; ++i)
        {
            if (inventory[i] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);

        if (this.hasCustomName())
        {
            nbtTagCompound.setString("CustomName", composerCustomName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Composer isBurning
     */
    public boolean isBurning()
    {
        return this.composerBurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory)
    {
        return inventory.getField(0) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    @Override
    public void update()
    {
        boolean burning = this.isBurning();
        boolean sendUpdate = false;

        if (this.isBurning())
        {
            --this.composerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (!this.isBurning() && (this.inventory[FUEL_INVENTORY_INDEX] == null || this.inventory[DATA_CARD_INVENTORY_INDEX] == null))
            {
                if (!this.isBurning() && this.cookTime > 0)
                {
                    this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
                }
            }
            else
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.currentItemBurnTime = this.composerBurnTime = ItemStackHelper.getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

                    if (this.isBurning())
                    {
                        sendUpdate = true;

                        if (this.inventory[FUEL_INVENTORY_INDEX] != null)
                        {
                            --this.inventory[FUEL_INVENTORY_INDEX].stackSize;

                            if (this.inventory[FUEL_INVENTORY_INDEX].stackSize == 0)
                            {
                                this.inventory[FUEL_INVENTORY_INDEX] = inventory[FUEL_INVENTORY_INDEX].getItem().getContainerItem(inventory[FUEL_INVENTORY_INDEX]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = 200;
                        this.smeltItem();
                        sendUpdate = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }

            if (burning != this.isBurning())
            {
                sendUpdate = true;
                //BlockComposer.setState(this.isBurning(), this.worldObj, this.pos); TODO
            }
        }

        if (sendUpdate)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the composer can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (inventory[DATA_CARD_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = ItemStack.loadItemStackFromNBT((inventory[DATA_CARD_INVENTORY_INDEX].getTagCompound()));
            if (!ElementHelper.itemElementsList.containsKey(itemstack.getItem())) return false;
            if (!new ElementHelper(itemstack.getItem()).compareElementsWithContainers(new ItemStack[]{inventory[INPUT_INVENTORY_INDEX1], inventory[INPUT_INVENTORY_INDEX2], inventory[INPUT_INVENTORY_INDEX3], inventory[INPUT_INVENTORY_INDEX4], inventory[INPUT_INVENTORY_INDEX5], inventory[INPUT_INVENTORY_INDEX6]})) return false;
            if (inventory[OUTPUT_INVENTORY_INDEX] == null) return true;
            if (inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) return false;
            return true;
        }
    }

    /**
     * Turn one item from the composer source stack into the appropriate smelted item in the composer result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
//            for (Elements element : new ElementHelper(inventory[INPUT_INVENTORY_INDEX].getItem()).getElements())
//            {
//                int amount = new ElementHelper(inventory[INPUT_INVENTORY_INDEX].getItem()).getAmount(element);
//                ItemStack output = new ItemStack(ModItems.elementContainer, amount, element.ordinal());
//                if (inventory[OUTPUT_INVENTORY_INDEX1] == null)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX1] = output.copy();
//                }
//                else if (output.getItemDamage() == inventory[OUTPUT_INVENTORY_INDEX1].getItemDamage() && output.stackSize + inventory[OUTPUT_INVENTORY_INDEX1].stackSize <= 64)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX1].stackSize += output.stackSize;
//                }
//                else if (inventory[OUTPUT_INVENTORY_INDEX2] == null)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX2] = output.copy();
//                }
//                else if (output.getItemDamage() == inventory[OUTPUT_INVENTORY_INDEX2].getItemDamage() && output.stackSize + inventory[OUTPUT_INVENTORY_INDEX2].stackSize <= 64)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX2].stackSize += output.stackSize;
//                }
//                else if (inventory[OUTPUT_INVENTORY_INDEX3] == null)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX3] = output.copy();
//                }
//                else if (output.getItemDamage() == inventory[OUTPUT_INVENTORY_INDEX3].getItemDamage() && output.stackSize + inventory[OUTPUT_INVENTORY_INDEX3].stackSize <= 64)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX3].stackSize += output.stackSize;
//                }
//                else if (inventory[OUTPUT_INVENTORY_INDEX4] == null)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX4] = output.copy();
//                }
//                else if (output.getItemDamage() == inventory[OUTPUT_INVENTORY_INDEX4].getItemDamage() && output.stackSize + inventory[OUTPUT_INVENTORY_INDEX4].stackSize <= 64)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX4].stackSize += output.stackSize;
//                }
//                else if (inventory[OUTPUT_INVENTORY_INDEX5] == null)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX5] = output.copy();
//                }
//                else if (output.getItemDamage() == inventory[OUTPUT_INVENTORY_INDEX5].getItemDamage() && output.stackSize + inventory[OUTPUT_INVENTORY_INDEX5].stackSize <= 64)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX5].stackSize += output.stackSize;
//                }
//                else if (inventory[OUTPUT_INVENTORY_INDEX6] == null)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX6] = output.copy();
//                }
//                else if (output.getItemDamage() == inventory[OUTPUT_INVENTORY_INDEX6].getItemDamage() && output.stackSize + inventory[OUTPUT_INVENTORY_INDEX6].stackSize <= 64)
//                {
//                    inventory[OUTPUT_INVENTORY_INDEX6].stackSize += output.stackSize;
//                }
//            }

            //decrStackSize(INPUT_INVENTORY_INDEX, 1);
            inventory[OUTPUT_INVENTORY_INDEX] = new ItemStack(ModItems.logo, 50);
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player)
    {
    }

    public void closeInventory(EntityPlayer player)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == OUTPUT_INVENTORY_INDEX ? false : (index == FUEL_INVENTORY_INDEX ? ItemStackHelper.isItemFuel(stack) : true);
    }

    public int[] getSlotsForFace(EnumFacing side)
    {
        return side == EnumFacing.DOWN ? new int[]{FUEL_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX} : new int[]{OUTPUT_INVENTORY_INDEX, input[1], input[2], input[3], input[4], input[5], input[6]};
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
    {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
    {
        return index == OUTPUT_INVENTORY_INDEX;
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerComposer(playerInventory, this);
    }

    @Override
    public String getGuiID()
    {
        return null;
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.composerBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.composerBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    public int getFieldCount()
    {
        return 4;
    }

    public void clear()
    {
        for (int i = 0; i < this.inventory.length; ++i)
        {
            this.inventory[i] = null;
        }
    }
}