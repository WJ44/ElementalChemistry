package com.wj44.echem.reference;

import com.wj44.echem.util.ResourceLocationHelper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Wesley "WJ44" Joosten on 19-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class Textures
{
    public static final String RESOURCE_PREFIX = Reference.LOWERCASE_MOD_ID + ":";

    public static final class Gui
    {
        private static final String GUI_SHEET_LOCATION = "textures/gui/";
        public static final ResourceLocation DECOMPOSER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "decomposer.png");
        public static final ResourceLocation ITEM_SCANNER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "itemScanner.png");
        public static final ResourceLocation COMPOSER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "composer.png");
    }

    public static final class Item
    {
        public static final ModelResourceLocation DATA_CARD = ResourceLocationHelper.getModelResourceLocation(Names.Items.DATA_CARD);
        public static final ModelResourceLocation LOGO = ResourceLocationHelper.getModelResourceLocation(Names.Items.LOGO);
        public static final ModelResourceLocation[] ELEMENT_CONTAINERS = ResourceLocationHelper.getModelResourceLocations(Names.Items.ELEMENT_CONTAINER, Names.Items.ELEMENT_CONTAINER_SUBTYPES);
    }
}
