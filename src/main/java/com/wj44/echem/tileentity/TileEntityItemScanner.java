package com.wj44.echem.tileentity;

import com.wj44.echem.api.ElementalChemistryAPI;
import com.wj44.echem.block.BlockItemScanner;
import com.wj44.echem.inventory.ContainerItemScanner;
import com.wj44.echem.reference.Names;
import com.wj44.echem.util.APIHelper;
import com.wj44.echem.util.FormulaHelper;
import com.wj44.echem.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

/**
 * Created by Wesley "WJ44" Joosten on 24-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityItemScanner extends TileEntityEChem implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 3;
    public static final int INPUT_INVENTORY_INDEX = 0;
    public static final int FUEL_INVENTORY_INDEX = 1;
    public static final int OUTPUT_INVENTORY_INDEX = 2;
    /** The ItemStacks that hold the items currently being used in the itemScanner */
    private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];
    /** The number of ticks that the itemScanner will keep burning */
    private int itemScannerBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the itemScanner burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;

    public String getCommandSenderName()
    {
        return this.hasCustomName() ? this.customName : Names.Containers.ITEM_SCANNER;
    }

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

        this.itemScannerBurnTime = nbtTagCompound.getShort("BurnTime");
        this.cookTime = nbtTagCompound.getShort("CookTime");
        this.totalCookTime = nbtTagCompound.getShort("CookTimeTotal");
        this.currentItemBurnTime = ItemStackHelper.getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

        if (nbtTagCompound.hasKey("CustomName", 8))
        {
            this.customName = nbtTagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("BurnTime", (short) this.itemScannerBurnTime);
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
            nbtTagCompound.setString("CustomName", customName);
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
     * ItemScanner isBurning
     */
    public boolean isBurning()
    {
        return this.itemScannerBurnTime > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update()
    {
        boolean burning = this.isBurning();
        boolean sendUpdate = false;

        if (this.isBurning())
        {
            --this.itemScannerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (!this.isBurning() && (this.inventory[FUEL_INVENTORY_INDEX] == null || this.inventory[INPUT_INVENTORY_INDEX] == null))
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
                    this.currentItemBurnTime = this.itemScannerBurnTime = ItemStackHelper.getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

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
                BlockItemScanner.setState(this.isBurning(), this.worldObj, this.pos);
            }
        }

        if (sendUpdate)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the itemScanner can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (inventory[INPUT_INVENTORY_INDEX] == null || inventory[OUTPUT_INVENTORY_INDEX] == null || inventory[OUTPUT_INVENTORY_INDEX].getTagCompound().getBoolean("isScanned"))
        {
            return false;
        }

        return true;
    }

    /**
     * Turn one item from the itemScanner source stack into the appropriate smelted item in the itemScanner result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            if (ElementalChemistryAPI.hasElements(inventory[INPUT_INVENTORY_INDEX]))
            {
                ItemStack itemStack = inventory[INPUT_INVENTORY_INDEX];
                itemStack.stackSize = 1;
                itemStack.writeToNBT(inventory[OUTPUT_INVENTORY_INDEX].getTagCompound());

                inventory[OUTPUT_INVENTORY_INDEX].getTagCompound().setString("Formula", FormulaHelper.getFormulaFromItemStack(inventory[INPUT_INVENTORY_INDEX]));

                inventory[OUTPUT_INVENTORY_INDEX].getTagCompound().setInteger("Density", APIHelper.getProperties(inventory[INPUT_INVENTORY_INDEX]).density);

                inventory[OUTPUT_INVENTORY_INDEX].getTagCompound().setFloat("Volume", APIHelper.getProperties(inventory[INPUT_INVENTORY_INDEX]).volume);

                inventory[OUTPUT_INVENTORY_INDEX].getTagCompound().setInteger("Mass", APIHelper.getProperties(inventory[INPUT_INVENTORY_INDEX]).mass);

                inventory[OUTPUT_INVENTORY_INDEX].getTagCompound().setBoolean("isScanned", true);
            }
        }
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
        return side == EnumFacing.DOWN ? new int[]{FUEL_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX} : new int[]{INPUT_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX};
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
        return new ContainerItemScanner(playerInventory, this);
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
                return this.itemScannerBurnTime;
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
                this.itemScannerBurnTime = value;
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