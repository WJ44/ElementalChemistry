package com.wj44.echem.item;

import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.init.ModItems;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Textures;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
        itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setBoolean("isScanned", false);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setBoolean("isScanned", false);
        }

        if (itemStack.getTagCompound().getBoolean("isScanned"))
        {
            list.remove("Empty");
            list.add("Formula: " + itemStack.getTagCompound().getString("Formula"));
        }
        else
        {
            list.add("Empty");
        }
    }
}


