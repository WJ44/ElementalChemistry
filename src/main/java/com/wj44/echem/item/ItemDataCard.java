package com.wj44.echem.item;

import com.wj44.echem.api.Element;
import com.wj44.echem.reference.Names;
import com.wj44.echem.util.APIHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

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
            list.add("Item: " + ItemStack.loadItemStackFromNBT(itemStack.getTagCompound()).getDisplayName());

            String contains = "Contains: ";
            for (Element element : APIHelper.getElementList(ItemStack.loadItemStackFromNBT(itemStack.getTagCompound())).getElements())
            {
                int amount = APIHelper.getElementAmount(ItemStack.loadItemStackFromNBT(itemStack.getTagCompound()), element);
                contains += amount + " " + element.symbol + ", ";
            }
            contains = contains.replace(contains.substring(contains.length() - 2), "");
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                list.add(contains);
            }
            else
            {
                list.remove(contains);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
            {
                list.add("Formula: " + itemStack.getTagCompound().getString("Formula"));
                list.add("Density: " + itemStack.getTagCompound().getInteger("Density") + " kg/m³");
                list.add("Mass: " + itemStack.getTagCompound().getInteger("Mass") + " g");
                list.add("Volume: " + itemStack.getTagCompound().getFloat("Volume")+ " m³");
            }
            else
            {
                list.remove("Formula: " + itemStack.getTagCompound().getString("Formula"));
                list.remove("Density: " + itemStack.getTagCompound().getInteger("Density") + " kg/m³");
                list.remove("Mass: " + itemStack.getTagCompound().getInteger("Mass") + " g");
                list.remove("Volume: " + itemStack.getTagCompound().getFloat("Volume")+ " m³");
            }
        }
        else
        {
            list.add("Empty");
        }
    }
}


