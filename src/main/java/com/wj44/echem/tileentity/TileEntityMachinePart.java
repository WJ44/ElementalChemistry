package com.wj44.echem.tileentity;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wesley "WJ44" Joosten on 2-6-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityMachinePart extends TileEntity implements IUpdatePlayerListBox
{
    public boolean initialized = false;
    private TileEntityElementMachine master;

    public TileEntityElementMachine getMaster()
    {
        initializeIfNecessary();
        return master;
    }

    @Override
    public void update()
    {
        if (!initialized)
        {
            initializeIfNecessary();
            initialized = true;
        }
    }

    public void initializeIfNecessary()
    {
        int machines = 0;
        if (master == null || master.isInvalid())
        {
            List<TileEntityMachinePart> connectedParts = new ArrayList<TileEntityMachinePart>();
            Stack<TileEntityMachinePart> passingParts = new Stack<TileEntityMachinePart>();
            TileEntityElementMachine master = null;
            passingParts.add(this);

            while (!passingParts.isEmpty())
            {
                TileEntityMachinePart part = passingParts.pop();
                connectedParts.add(part);
                for (EnumFacing f : EnumFacing.values())
                {
                    TileEntity tileEntity = worldObj.getTileEntity(part.pos.offset(f));
                    if (tileEntity instanceof TileEntityMachinePart && !connectedParts.contains(tileEntity))
                    {
                        passingParts.add((TileEntityMachinePart) tileEntity);
                    }
                    else if (tileEntity instanceof TileEntityElementMachine)
                    {
                        master = (TileEntityElementMachine) tileEntity;
                        machines++;
                    }
                }
            }
            for (TileEntityMachinePart part : connectedParts)
            {
                part.setMaster(master);
            }

            if (machines != 1)
            {
                this.invalidate();
            }
            else
            {
                master.machineParts = connectedParts;
            }
        }
    }

    private void setMaster(TileEntityElementMachine master)
    {
        this.master = master;
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        for (EnumFacing f : EnumFacing.values())
        {
            TileEntity tileEntity = worldObj.getTileEntity(pos.offset(f));
            if (tileEntity instanceof TileEntityMachinePart)
            {
                ((TileEntityMachinePart) tileEntity).master = null;
                ((TileEntityMachinePart) tileEntity).initializeIfNecessary();
            }
        }
    }

}
