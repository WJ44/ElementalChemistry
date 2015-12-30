package com.wj44.echem.init;

import com.wj44.echem.item.crafting.RecipeElementContainerCombining;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 30/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ModRecipes
{
   public static void init()
   {
       GameRegistry.addRecipe(new RecipeElementContainerCombining());
   }
}
