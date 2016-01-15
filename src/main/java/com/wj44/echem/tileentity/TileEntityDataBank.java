package com.wj44.echem.tileentity;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.reference.Names;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by Wesley "WJ44" Joosten on 09/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityDataBank extends TileEntityEChem
{
    private static int selectedSlot = 0;

    public TileEntityDataBank()
    {
        inventory = new ItemStack[22];
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return stack.getItem() == ModItems.dataCard && stack.getTagCompound().getBoolean("isScanned");
    }

    @Override
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return selectedSlot;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.selectedSlot = value;
                break;
        }
    }

    @Override
    public int getFieldCount()
    {
        return 1;
    }


    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : Names.Containers.DATA_BANK;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.selectedSlot = compound.getInteger("SelectedSlot");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("SelectedSlot", selectedSlot);
    }
}
