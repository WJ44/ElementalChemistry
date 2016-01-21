package com.wj44.echem.reference;

import com.wj44.echem.util.ResourceLocationHelper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Wesley "WJ44" Joosten on 29/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.LOWERCASE_MOD_ID + ":";

    public static final class Block
    {
        public static final ModelResourceLocation DATA_BANK = ResourceLocationHelper.getModelResourceLocation(Names.Blocks.DATA_BANK);
        public static final ModelResourceLocation DECOMPOSER = ResourceLocationHelper.getModelResourceLocation(Names.Blocks.DECOMPOSER);
        public static final ModelResourceLocation ITEM_SCANNER = ResourceLocationHelper.getModelResourceLocation(Names.Blocks.ITEM_SCANNER);
    }

    public static final class Gui
    {
        public static final String GUI_SHEET_LACATION = "textures/gui/";
        public static final ResourceLocation DATA_BANK = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LACATION + "dataBank.png");
        public static final ResourceLocation DECOMPOSER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LACATION + "decomposer.png");
        public static final ResourceLocation ITEM_SCANNER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LACATION + "itemScanner.png");
    }

    public static final class Item
    {
        public static final ModelResourceLocation DATA_CARD = ResourceLocationHelper.getModelResourceLocation(Names.Items.DATA_CARD);
        public static final ModelResourceLocation[] ELEMENT_CONTAINERS = ResourceLocationHelper.getModelResourceLocations(Names.Items.ELEMENT_CONTAINER, Names.Items.ELEMENT_CONTAINER_SUBTYPES);
        public static final ModelResourceLocation LOGO = ResourceLocationHelper.getModelResourceLocation(Names.Items.LOGO);
    }
}
