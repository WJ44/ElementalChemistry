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
public class ContainerDataBankItem extends ContainerEChem
{
    private final EntityPlayer player;
    public final InventoryDataBankItem inventoryDataBankItem;

    public ContainerDataBankItem(EntityPlayer player, InventoryDataBankItem inventoryDataBankItem)
    {
        this.player = player;
        this.inventoryDataBankItem = inventoryDataBankItem;

        this.addSlotToContainer(new Slot(inventoryDataBankItem, 0, 50, 50));

        addPlayerSlots(player.inventory, 8, 84);
    }
}
