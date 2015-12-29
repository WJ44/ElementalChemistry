package com.wj44.echem.init;

import com.wj44.echem.item.ItemEChem;
import com.wj44.echem.item.ItemElementContainer;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Reference;
import com.wj44.echem.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
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
    public static final Item elementContainer = new ItemElementContainer();
    public static final Item logo = new ItemEChem().setUnlocalizedName(Names.Items.LOGO);

    public static void init()
    {
        GameRegistry.registerItem(elementContainer, Names.Items.ELEMENT_CONTAINER);
        GameRegistry.registerItem(logo, Names.Items.LOGO);
    }

    public static void registerRenders()
    {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        renderItem.getItemModelMesher().register(logo, 0, Textures.Item.LOGO);

        registerHasSubtypes(elementContainer, Textures.Item.ELEMENT_CONTAINERS, renderItem);
    }

    private static void registerHasSubtypes(Item item, ModelResourceLocation[] resourceLocations, RenderItem renderItem)
    {
        for (int i = 0; i < resourceLocations.length; i++)
        {
            renderItem.getItemModelMesher().register(item, i, resourceLocations[i]);
        }
    }
}
