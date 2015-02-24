package com.wj44.echem.item;

import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 23-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemDataCard extends ItemEChem
{
    public ItemDataCard()
    {
        super();
        setMaxStackSize(1);
        this.setCreativeTab(CreativeTabEChem.ECHEM_TAB);
        this.setUnlocalizedName(Names.Items.DATA_CARD);
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setBoolean("isScanned", false);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
    {
        if (itemStack.stackTagCompound == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        if (itemStack.stackTagCompound.getBoolean("isScanned"))
        {
            list.remove("Empty");
            list.add("Formula: " + itemStack.stackTagCompound.getString("Formula"));
        }
        else
        {
            list.add("Empty");
        }
    }
}


