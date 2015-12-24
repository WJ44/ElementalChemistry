package com.wj44.echem.item;

import com.wj44.echem.api.Element;
import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.init.ModItems;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Textures;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 19-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemElementContainer extends ItemEChem
{
    public ItemElementContainer()
    {
        setCreativeTab(CreativeTabEChem.ELEMENT_CONTAINER_TAB);
        setHasSubtypes(true);
        setUnlocalizedName(Names.Items.ELEMENT_CONTAINER);
        setMaxStackSize(1);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s%s", Textures.RESOURCE_PREFIX, Names.Items.ELEMENT_CONTAINER, Names.Items.ELEMENT_CONTAINER_SUBTYPES[itemStack.getItemDamage()]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Element.elements.values().size(); ++meta)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    public static void registerVariants()
    {
        String[] variantNames = new String[Names.Items.ELEMENT_CONTAINER_SUBTYPES.length];
        for (int i = 0; i < Names.Items.ELEMENT_CONTAINER_SUBTYPES.length; i++)
        {
            variantNames[i] = Textures.RESOURCE_PREFIX + Names.Items.ELEMENT_CONTAINER + Names.Items.ELEMENT_CONTAINER_SUBTYPES[i];
        }
        ModelBakery.addVariantName(ModItems.elementContainer, variantNames);
    }

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setInteger("Amount", 0);
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b)
    {
        if (!itemStack.hasTagCompound())
        {
            itemStack.setTagCompound(new NBTTagCompound());
            itemStack.getTagCompound().setInteger("Amount", 0);
        }

        if (itemStack.getTagCompound().getInteger("Amount") != 0)
        {
            list.remove("Empty");
            list.add("Amount: " + itemStack.getTagCompound().getInteger("Amount"));
        }
        else
        {
            list.add("Empty");
        }
    }
}
