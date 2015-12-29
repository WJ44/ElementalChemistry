package com.wj44.echem.init;

import com.wj44.echem.item.ItemEChem;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Reference;
import com.wj44.echem.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 29/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
    public static final net.minecraft.item.Item logo = new ItemEChem().setUnlocalizedName(Names.Items.LOGO);

    public static void init()
    {
        GameRegistry.registerItem(logo, Names.Items.LOGO);
    }

    public static void registerRenders()
    {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        renderItem.getItemModelMesher().register(logo, 0, Textures.Item.LOGO);
    }
}
