package com.wj44.echem.reference;

import com.wj44.echem.util.ResourceLocationHelper;
import net.minecraft.client.resources.model.ModelResourceLocation;

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

    public static final class Item
    {
        public static final ModelResourceLocation LOGO = ResourceLocationHelper.getModelResourceLocation(Names.Items.LOGO);
    }
}
