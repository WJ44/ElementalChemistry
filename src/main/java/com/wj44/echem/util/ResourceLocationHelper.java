package com.wj44.echem.util;

import com.wj44.echem.reference.Textures;
import net.minecraft.client.resources.model.ModelResourceLocation;

/**
 * Created by Wesley "WJ44" Joosten on 29/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ResourceLocationHelper
{
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
