package com.wj44.echem.inventory;

import com.wj44.echem.tileentity.TileEntityDecomposer;
import com.wj44.echem.util.ItemStackHelper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 26-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class SlotMachineFuel extends Slot
{
    private static final String __OBFID = "CL_00002184";

    public SlotMachineFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack stack)
    {
        return ItemStackHelper.isItemFuel(stack);
    }

}
