package com.wj44.echem.tileentity;

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

    public abstract int getDataCardIndex();

    public abstract boolean isBurning();
}
