package com.wj44.echem.reference;

import com.wj44.echem.util.ResourceLocationHelper;
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
    }
}
