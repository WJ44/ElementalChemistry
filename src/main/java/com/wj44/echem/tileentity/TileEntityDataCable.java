package com.wj44.echem.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wesley "WJ44" Joosten on 26-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityDataCable extends TileEntity implements IUpdatePlayerListBox
{
    private boolean initialized = false;
    private TileEntityDataCable master;
    public boolean isMaster;
    private TileEntityDataBank connectedDataBank;
    private ItemStack lastSelected;

    public TileEntityDataCable getMaster()
    {
        initializeIfNecessary();
        return master;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        isMaster = compound.getBoolean("isMaster");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        compound.setBoolean("isMaster", isMaster);
    }

    @Override
    public void update()
    {
        if (!initialized)
        {
            initializeIfNecessary();
        }

        for (EnumFacing f : EnumFacing.values())
        {
            TileEntity tileEntity = worldObj.getTileEntity(pos.offset(f));
            if (tileEntity instanceof TileEntityElementMachine && ((lastSelected != getSelected() && ((TileEntityElementMachine) tileEntity).dataBankConnected == true)
                    || (((TileEntityElementMachine) tileEntity).dataBankConnected == false && ((TileEntityElementMachine) tileEntity).getStackInSlot(((TileEntityElementMachine) tileEntity).getDataCardIndex()) == null)))
            {
                ((TileEntityElementMachine) tileEntity).dataBankConnected = true;
                ((TileEntityElementMachine) tileEntity).setInventorySlotContents(((TileEntityElementMachine) tileEntity).getDataCardIndex(), getSelected());
            }
        }

        lastSelected = getSelected();
    }

    private void initializeIfNecessary()
    {
        if (master == null || master.isInvalid())
        {
            TileEntityDataBank connectedDataBank = null;
            List<TileEntityDataCable> connectedCables = new ArrayList<TileEntityDataCable>();
            Stack<TileEntityDataCable> passingCables = new Stack<TileEntityDataCable>();
            TileEntityDataCable master = this;
            passingCables.add(this);

            while (!passingCables.isEmpty())
            {
                TileEntityDataCable cable = passingCables.pop();
                if (cable.isMaster)
                {
                    master = cable;
                }
                connectedCables.add(cable);
                for (EnumFacing f : EnumFacing.values())
                {
                    TileEntity tileEntity = worldObj.getTileEntity(cable.pos.offset(f));
                    if (tileEntity instanceof TileEntityDataCable && !connectedCables.contains(tileEntity))
                    {
                        passingCables.add((TileEntityDataCable) tileEntity);
                    }
                    else if (tileEntity instanceof TileEntityDataBank)
                    {
                        connectedDataBank = (TileEntityDataBank) tileEntity;
                    }
                }
            }
            for (TileEntityDataCable cable : connectedCables)
            {
                cable.setMaster(master, connectedDataBank);
            }
        }
    }

    private void setMaster(TileEntityDataCable master, TileEntityDataBank connectedDataBank)
    {
        this.master = master;
        isMaster = master == this;
        this.connectedDataBank = connectedDataBank;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        for (EnumFacing f : EnumFacing.values())
        {
            TileEntity tileEntity = worldObj.getTileEntity(pos.offset(f));
            if (tileEntity instanceof TileEntityDataCable)
            {
                ((TileEntityDataCable) tileEntity).master = null;
                ((TileEntityDataCable) tileEntity).initializeIfNecessary();
            }
            else if (tileEntity instanceof TileEntityElementMachine && ((TileEntityElementMachine) tileEntity).dataBankConnected)
            {
                ((TileEntityElementMachine) tileEntity).dataBankConnected = false;
                ((TileEntityElementMachine) tileEntity).setInventorySlotContents(((TileEntityElementMachine) tileEntity).getDataCardIndex(), null);
            }
        }
    }

    public ItemStack getSelected()
    {
        if (isMaster)
        {
            if (connectedDataBank != null)
            {
                ItemStack stack = connectedDataBank.getSelected();

                if (stack != null)
                {
                    return stack;
                }
                else
                {
                    return null;
                }
            }
        }
        else
        {
            return getMaster().getSelected();
        }
        return null;
    }
}
