package com.wj44.echem.init;

import com.wj44.echem.api.Element;
import com.wj44.echem.api.ElementList;
import com.wj44.echem.api.ElementalChemistryAPI;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

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
        //Items
        ElementalChemistryAPI.registerItemElements(Items.coal, new ElementList().add(Element.CARBON, 1));
        ElementalChemistryAPI.registerItemElements(Items.diamond, new ElementList().add(Element.CARBON, 1));
        ElementalChemistryAPI.registerItemElements(Items.iron_ingot, new ElementList().add(Element.IRON, 1));
        ElementalChemistryAPI.registerItemElements(Items.gold_ingot, new ElementList().add(Element.GOLD, 1));
        ElementalChemistryAPI.registerItemElements(Items.sugar, new ElementList().add(Element.CARBON, 12).add(Element.HYDROGEN, 22).add(Element.OXYGEN, 11));
        ElementalChemistryAPI.registerItemElements(Items.gold_nugget, new ElementList().add(Element.GOLD, 1));
        ElementalChemistryAPI.registerItemElements(Items.emerald, new ElementList().add(Element.BERYLLIUM, 3).add(Element.ALUMINIUM, 2).add(Element.SILICON, 6).add(Element.OXYGEN, 18));
        ElementalChemistryAPI.registerItemElements(Items.quartz, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
        //Blocks
        ElementalChemistryAPI.registerItemElements(Blocks.iron_block, new ElementList().add(Element.GOLD, 1));
        ElementalChemistryAPI.registerItemElements(Blocks.obsidian, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
        ElementalChemistryAPI.registerItemElements(Blocks.diamond_block, new ElementList().add(Element.CARBON, 1));
        ElementalChemistryAPI.registerItemElements(Blocks.emerald_block, new ElementList().add(Element.BERYLLIUM, 3).add(Element.ALUMINIUM, 2).add(Element.SILICON, 6).add(Element.OXYGEN, 18));
        ElementalChemistryAPI.registerItemElements(Blocks.quartz_block, new ElementList().add(Element.SILICON, 1).add(Element.OXYGEN, 2));
        ElementalChemistryAPI.registerItemElements(Blocks.coal_block, new ElementList().add(Element.CARBON, 1));
    }
}
