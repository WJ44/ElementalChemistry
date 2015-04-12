package com.wj44.echem.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * Created by Wesley "WJ44" Joosten on 12-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ContainerDataBank extends ContainerEChem
{
    private final EntityPlayer player;
    public final InventoryDataBank inventoryDataBank;

    public ContainerDataBank(EntityPlayer player, InventoryDataBank inventoryDataBank)
    {
        this.player = player;
        this.inventoryDataBank = inventoryDataBank;

        this.addSlotToContainer(new Slot(inventoryDataBank, 0, 50, 50));

        addPlayerSlots(player.inventory, 8, 84);
    }
}
