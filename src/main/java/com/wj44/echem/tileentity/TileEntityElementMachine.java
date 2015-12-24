package com.wj44.echem.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 21-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public abstract class TileEntityElementMachine extends TileEntityEChem
{
    public boolean dataBankConnected;
    public List<TileEntityMachinePart> machineParts;
    public boolean initialized = false;

    public abstract int getDataCardIndex();

    public abstract boolean isBurning();

    public TileEntityDataBank getDataBank()
    {
        if (dataBankConnected)
        {
            for (EnumFacing f : EnumFacing.values())
            {
                TileEntity tileEntity = worldObj.getTileEntity(pos.offset(f));
                if (tileEntity instanceof TileEntityDataCable)
                {
                    return ((TileEntityDataCable) tileEntity).getDataBank();
                }
            }
        }
        return null;
    }

    public void initialize()
    {
        for (EnumFacing f : EnumFacing.values())
        {
            TileEntity tileEntity = worldObj.getTileEntity(pos.offset(f));
            if (tileEntity instanceof TileEntityMachinePart)
            {
                if (((TileEntityMachinePart) tileEntity).getMaster() != this)
                {
                    tileEntity.invalidate();
                }
            }
        }


    }
}
