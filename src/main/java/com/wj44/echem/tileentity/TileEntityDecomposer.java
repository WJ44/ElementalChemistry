package com.wj44.echem.tileentity;

import com.wj44.echem.item.crafting.DecomposerRecipes;
import com.wj44.echem.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Wesley "WJ44" Joosten on 20-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityDecomposer extends TileEntityEChem implements ISidedInventory
{
    public static final int INVENTORY_SIZE = 3;
    public static final int INPUT_INVENTORY_INDEX = 0;
    public static final int FUEL_INVENTORY_INDEX = 1;
    public static final int OUTPUT_INVENTORY_INDEX = 2;

    public int decomposerBurnTime;
    public int decomposerCookTime;
    public int currentItemBurnTime;

    private ItemStack[] inventory = new ItemStack[INVENTORY_SIZE];

    private String customName;

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int decrementAmount)
    {
        if (inventory[slot] != null)
        {
            ItemStack itemstack;

            if (inventory[slot].stackSize <= decrementAmount)
            {
                itemstack = inventory[slot];
                inventory[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = inventory[slot].splitStack(decrementAmount);

                if (inventory[slot].stackSize == 0)
                {
                    inventory[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (inventory[slot] != null)
        {
            ItemStack itemstack = inventory[slot];
            inventory[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        inventory[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? customName : Names.Containers.DECOMPOSER;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return customName != null && customName.length() > 0;
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

        decomposerBurnTime = nbtTagCompound.getShort("BurnTime");
        decomposerCookTime = nbtTagCompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(inventory[FUEL_INVENTORY_INDEX]);

        if (nbtTagCompound.hasKey("CustomName", 8))
        {
            customName = nbtTagCompound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("BurnTime", (short) decomposerBurnTime);
        nbtTagCompound.setShort("CookTime", (short) decomposerCookTime);
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

        if (this.hasCustomInventoryName())
        {
            nbtTagCompound.setString("CustomName", customName);
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale)
    {
        return decomposerCookTime * scale / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return decomposerBurnTime * scale / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.decomposerBurnTime > 0;
    }

    @Override
    public void updateEntity()
    {
        boolean isBurning = this.decomposerBurnTime > 0;
        boolean sendUpdate = false;

        if (this.decomposerBurnTime > 0)
        {
            --this.decomposerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.decomposerBurnTime != 0 || this.inventory[FUEL_INVENTORY_INDEX] != null && this.inventory[INPUT_INVENTORY_INDEX] != null)
            {
                if (this.decomposerBurnTime == 0 && this.canSmelt())
                {
                    this.currentItemBurnTime = this.decomposerBurnTime = getItemBurnTime(this.inventory[FUEL_INVENTORY_INDEX]);

                    if (this.decomposerBurnTime > 0)
                    {
                        sendUpdate = true;

                        if (this.inventory[FUEL_INVENTORY_INDEX] != null)
                        {
                            --this.inventory[FUEL_INVENTORY_INDEX].stackSize;

                            if (this.inventory[FUEL_INVENTORY_INDEX].stackSize == 0)
                            {
                                this.inventory[FUEL_INVENTORY_INDEX] = this.inventory[FUEL_INVENTORY_INDEX].getItem().getContainerItem(inventory[FUEL_INVENTORY_INDEX]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.decomposerCookTime;

                    if (this.decomposerCookTime == 200)
                    {
                        this.decomposerCookTime = 0;
                        this.smeltItem();
                        sendUpdate = true;
                    }
                }
                else
                {
                    this.decomposerCookTime = 0;
                }
            }

            if (isBurning != this.decomposerBurnTime > 0)
            {
                sendUpdate = true;
                //BlockDecomposer.updateDecomposerBlockState(this.decomposerBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord); //TODO
            }
        }

        if (sendUpdate)
        {
            this.markDirty();
        }
    }

    private boolean canSmelt()
    {
        if (inventory[INPUT_INVENTORY_INDEX] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = DecomposerRecipes.smelting().getSmeltingResult(this.inventory[INPUT_INVENTORY_INDEX]);
            if (itemstack == null) return false;
            if (this.inventory[OUTPUT_INVENTORY_INDEX] == null) return true;
            if (!this.inventory[OUTPUT_INVENTORY_INDEX].isItemEqual(itemstack)) return false;
            int result = inventory[OUTPUT_INVENTORY_INDEX].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.inventory[OUTPUT_INVENTORY_INDEX].getMaxStackSize();
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = DecomposerRecipes.smelting().getSmeltingResult(this.inventory[INPUT_INVENTORY_INDEX]);

            if (this.inventory[OUTPUT_INVENTORY_INDEX] == null)
            {
                this.inventory[OUTPUT_INVENTORY_INDEX] = itemstack.copy();
            }
            else if (this.inventory[OUTPUT_INVENTORY_INDEX].getItem() == itemstack.getItem())
            {
                this.inventory[OUTPUT_INVENTORY_INDEX].stackSize += itemstack.stackSize;
            }

            decrStackSize(INPUT_INVENTORY_INDEX, 1);
        }
    }

    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }


    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
        //NOOP
    }

    @Override
    public void closeInventory()
    {
        //NOOP
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
    return slot == OUTPUT_INVENTORY_INDEX ? false : (slot == FUEL_INVENTORY_INDEX ? isItemFuel(stack) : true);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        return side == ForgeDirection.DOWN.ordinal() ? new int[]{FUEL_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX} : new int[]{INPUT_INVENTORY_INDEX, OUTPUT_INVENTORY_INDEX};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side)
    {
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side)
    {
        return slot == OUTPUT_INVENTORY_INDEX;
    }

}
