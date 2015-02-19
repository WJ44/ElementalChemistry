package com.wj44.echem.item;

import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.reference.Elements;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

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
    @SideOnly(Side.CLIENT)
    private  IIcon[] icons;

    public ItemElementContainer()
    {
        super();
        this.setCreativeTab(CreativeTabEChem.ELEMENT_CONTAINER_TAB);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Names.Items.ELEMENT_CONTAINER);
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
        for (int meta = 0; meta < Elements.values().length; ++meta)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return icons[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[Elements.values().length];

        for (int i = 0; i < Elements.values().length; i++)
        {
            icons[i] = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + "/elementContainer/" + Names.Items.ELEMENT_CONTAINER + Names.Items.ELEMENT_CONTAINER_SUBTYPES[i]);
        }
    }
}
