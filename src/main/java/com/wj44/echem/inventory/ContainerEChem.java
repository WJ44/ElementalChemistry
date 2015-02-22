package com.wj44.echem.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by Wesley "WJ44" Joosten on 19-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public abstract class ContainerEChem extends Container
{
    /**
     * Adds the player inventory and the hotbar
     * @param playerInventory
     * @param x The x-position where the inventory should start (default 8)
     * @param y The y-position where the inventory should start (default 84)
     */
    protected void addPlayerSlots(InventoryPlayer playerInventory, int x, int y)
    {
        //Adds the inventory
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }

        //Adds the hotbar
        for (int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(playerInventory, i, x + i * 18, y + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }

    //When using mergeItemStack, the first int is the first slot it tries to use, the second when the last + 1, the boolean is whether to do it in reverse or not
}
