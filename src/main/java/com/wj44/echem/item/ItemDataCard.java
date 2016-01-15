package com.wj44.echem.item;

import com.wj44.echem.reference.Names;
import com.wj44.elementscore.api.Element;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 30/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemDataCard extends ItemEChem
{
    public ItemDataCard()
    {
        setMaxStackSize(1);
        this.setUnlocalizedName(Names.Items.DATA_CARD);
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setBoolean("isScanned", false);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setBoolean("isScanned", false);
        }

        if (itemStack.getTagCompound().getBoolean("isScanned"))
        {
            tooltip.remove("Empty");
            tooltip.add("Item: " + itemStack.getTagCompound().getString("item"));

            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                tooltip.add("Contains: " + itemStack.getTagCompound().getString("elements"));
            }
            else
            {
                tooltip.remove("Contains: " + itemStack.getTagCompound().getString("elements"));
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
            {
                tooltip.add("Formula: " + itemStack.getTagCompound().getString("formula"));
                tooltip.add("Density: " + itemStack.getTagCompound().getInteger("density") + " kg/m³");
                tooltip.add("Mass: " + itemStack.getTagCompound().getInteger("mass") + " g");
                tooltip.add("Volume: " + itemStack.getTagCompound().getFloat("volume")+ " m³");
            }
            else
            {
                tooltip.remove("Formula: " + itemStack.getTagCompound().getString("formula"));
                tooltip.remove("Density: " + itemStack.getTagCompound().getInteger("density") + " kg/m³");
                tooltip.remove("Mass: " + itemStack.getTagCompound().getInteger("mass") + " g");
                tooltip.remove("Volume: " + itemStack.getTagCompound().getFloat("volume")+ " m³");
            }
        }
        else
        {
            tooltip.add("Empty");
        }
    }
}
