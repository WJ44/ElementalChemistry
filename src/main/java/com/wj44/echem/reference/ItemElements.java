package com.wj44.echem.reference;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.util.ElementHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wesley "WJ44" Joosten on 22-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemElements
{
    public static void init()
    {
        Map logoList = new HashMap();
        logoList.put(Elements.BOHRIUM, 5);
        ElementHelper.addItemToItemElementList(ModItems.logo, logoList);

        Map woodList = new HashMap();
        woodList.put(Elements.RADON, 3);
        woodList.put(Elements.CADMIUM, 2);
        ElementHelper.addItemToItemElementList(ItemBlock.getItemFromBlock(Blocks.planks), woodList);

        Map spongeList = new HashMap();
        spongeList.put(Elements.ERBIUM, 3);
        spongeList.put(Elements.FERMIUM, 2);
        spongeList.put(Elements.CAESIUM, 7);
        spongeList.put(Elements.FRANCIUM, 8);
        ElementHelper.addItemToItemElementList(ItemBlock.getItemFromBlock(Blocks.sponge), spongeList);
    }
}
