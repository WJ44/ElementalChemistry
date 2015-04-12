package com.wj44.echem.item;

import com.wj44.echem.ElementalChemistry;
import com.wj44.echem.reference.GUIs;
import com.wj44.echem.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 12-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemDataBank extends ItemEChem
{
    public ItemDataBank()
    {
        setUnlocalizedName(Names.Items.DATA_BANK);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.openGui(ElementalChemistry.instance, GUIs.DATA_BANK.ordinal(), world, (int) player.posX, (int) player.posY, (int) player.posZ);

        return itemStack;
    }
}
