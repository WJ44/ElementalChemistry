package com.wj44.echem.inventory;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityElementMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 21-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class SlotDataCard extends Slot
{
    TileEntityElementMachine tileEntity;

    public SlotDataCard(TileEntityElementMachine inventoryIn, int index, int xPosition, int yPosition)
    {
        super(inventoryIn, index, xPosition, yPosition);
        tileEntity = inventoryIn;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        if (tileEntity.dataBankConnected)
        {
            return false;
        }
        if (itemStack.getItem() == ModItems.dataCard)
        {
            return itemStack.getTagCompound().getBoolean("isScanned");
        }

        return false;
    }

    @Override
    public boolean canTakeStack(EntityPlayer player)
    {
        if (tileEntity.dataBankConnected)
        {
            return false;
        }
        return true;
    }
}
