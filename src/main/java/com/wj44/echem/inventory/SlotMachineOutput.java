package com.wj44.echem.inventory;


import com.wj44.echem.init.ModItems;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

/**
 * Created by Wesley "WJ44" Joosten on 24-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class SlotMachineOutput extends Slot
{

    public SlotMachineOutput(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        super.onPickupFromSlot(entityPlayer, itemStack);
        FMLCommonHandler.instance().firePlayerCraftingEvent(entityPlayer, itemStack, inventory);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack)
    {
        if (inventory instanceof TileEntityDecomposer)
        {
            return itemStack.getItem() == ModItems.elementContainer;
        }
        return false;
    }
}
