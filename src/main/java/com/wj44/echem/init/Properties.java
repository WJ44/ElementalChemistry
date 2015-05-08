package com.wj44.echem.init;

import com.wj44.echem.api.ElementalChemistryAPI;
import com.wj44.echem.api.ItemProperties;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

/**
 * Created by Wesley "WJ44" Joosten on 7-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class Properties
{
    public static void init()
    {
        //Items
//    ElementalChemistryAPI.registerItemElements(Items.coal, new ElementList().add(Element.CARBON, 1));
        ElementalChemistryAPI.registerItemProperties(Items.diamond, new ItemProperties(3500, (float) 1/9));
//    ElementalChemistryAPI.registerItemElements(Items.iron_ingot, new ElementList().add(Element.IRON, 1));
//    ElementalChemistryAPI.registerItemElements(Items.gold_ingot, new ElementList().add(Element.GOLD, 1));
//    ElementalChemistryAPI.registerItemElements(Items.sugar, new ElementList().add(Element.CARBON, 12).add(Element.HYDROGEN, 22).add(Element.OXYGEN, 11));
//    ElementalChemistryAPI.registerItemElements(Items.gold_nugget, new ElementList().add(Element.GOLD, 1));
//    ElementalChemistryAPI.registerItemElements(Items.emerald, new ElementList().add(Element.BERYLLIUM, 3).add(Element.ALUMINIUM, 2).add(Element.SILICON, 6).add(Element.OXYGEN, 18));
//    ElementalChemistryAPI.registerItemElements(Items.quartz, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
        //Blocks
//    ElementalChemistryAPI.registerItemElements(Blocks.iron_block, new ElementList().add(Element.GOLD, 1));
//    ElementalChemistryAPI.registerItemElements(Blocks.obsidian, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
        ElementalChemistryAPI.registerItemProperties(Blocks.diamond_block, new ItemProperties(3500, 1));
//    ElementalChemistryAPI.registerItemElements(Blocks.emerald_block, new ElementList().add(Element.BERYLLIUM, 3).add(Element.ALUMINIUM, 2).add(Element.SILICON, 6).add(Element.OXYGEN, 18));
//    ElementalChemistryAPI.registerItemElements(Blocks.quartz_block, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
        ElementalChemistryAPI.registerItemProperties(Blocks.coal_block, new ItemProperties(1250, 1));
    }
}
