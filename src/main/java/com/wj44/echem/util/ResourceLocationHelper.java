package com.wj44.echem.util;

import com.wj44.echem.reference.Reference;
import com.wj44.echem.reference.Textures;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Wesley "WJ44" Joosten on 19-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String modId, String path)
    {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation(Reference.LOWERCASE_MOD_ID, path);
    }

    public static ModelResourceLocation getModelResourceLocation(String itemName)
    {
        return new ModelResourceLocation(Textures.RESOURCE_PREFIX + itemName, "inventory");
    }

    public static ModelResourceLocation[] getModelResourceLocations(String itemName, String[] subtypes)
    {
        ModelResourceLocation[] resourceLocations = new ModelResourceLocation[subtypes.length];

        for (int i = 0; i < subtypes.length; i++)
        {
            resourceLocations[i] = new ModelResourceLocation(Textures.RESOURCE_PREFIX + itemName + subtypes[i], "inventory");
        }

        return resourceLocations;
    }
}
