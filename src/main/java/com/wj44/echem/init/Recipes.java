package com.wj44.echem.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Wesley "WJ44" Joosten on 25-7-2014.
 *
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class Recipes
{
    public static void init()
    {
        // TODO proper recipe
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.logo), "ppp", "pbp", "ppp", 'p', new ItemStack(Items.paper), 'b', "dyeBlack"));
    }
}
