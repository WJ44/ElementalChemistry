package com.wj44.echem.init;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.reference.Elements;
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
        Map coalList = new HashMap();
        coalList.put(Elements.CARBON, 1);
        ElementHelper.addItemToItemElementList(Items.coal, coalList);

        Map diamondList = new HashMap();
        diamondList.put(Elements.CARBON, 1);
        ElementHelper.addItemToItemElementList(Items.diamond, diamondList);

        Map ironIngotList = new HashMap();
        ironIngotList.put(Elements.IRON, 1);
        ElementHelper.addItemToItemElementList(Items.iron_ingot, ironIngotList);

        Map goldIngotList = new HashMap();
        goldIngotList.put(Elements.GOLD, 1);
        ElementHelper.addItemToItemElementList(Items.gold_ingot, goldIngotList);

        Map sugarIngotList = new HashMap();
        sugarIngotList.put(Elements.CARBON, 12);
        sugarIngotList.put(Elements.HYDROGEN, 22);
        sugarIngotList.put(Elements.OXYGEN, 11);
        ElementHelper.addItemToItemElementList(Items.sugar, sugarIngotList);

        Map goldNuggetList = new HashMap();
        goldNuggetList.put(Elements.GOLD, 1);
        ElementHelper.addItemToItemElementList(Items.gold_nugget, goldNuggetList);

        Map emeraldList = new HashMap();
        emeraldList.put(Elements.BERYLLIUM, 3);
        emeraldList.put(Elements.ALUMINIUM, 2);
        emeraldList.put(Elements.SILICON, 6);
        emeraldList.put(Elements.OXYGEN, 18);
        ElementHelper.addItemToItemElementList(Items.emerald, emeraldList);

        Map quartzList = new HashMap();
        quartzList.put(Elements.SILICON, 1);
        quartzList.put(Elements.OXYGEN, 2);
        ElementHelper.addItemToItemElementList(Items.quartz, quartzList);

        Map ironBlockList = new HashMap();
        ironBlockList.put(Elements.IRON, 1);
        ElementHelper.addItemToItemElementList(Blocks.iron_block, ironBlockList);

        Map goldBlockList = new HashMap();
        goldBlockList.put(Elements.GOLD, 1);
        ElementHelper.addItemToItemElementList(Blocks.gold_block, goldBlockList);

        Map obsidianList = new HashMap();
        obsidianList.put(Elements.SILICON, 1);
        obsidianList.put(Elements.OXYGEN, 2);
        ElementHelper.addItemToItemElementList(Blocks.obsidian, obsidianList);

        Map diamondBlockList = new HashMap();
        diamondBlockList.put(Elements.CARBON, 1);
        ElementHelper.addItemToItemElementList(Blocks.diamond_block, diamondBlockList);

        Map emeraldBlockList = new HashMap();
        emeraldBlockList.put(Elements.BERYLLIUM, 3);
        emeraldBlockList.put(Elements.ALUMINIUM, 2);
        emeraldBlockList.put(Elements.SILICON, 6);
        emeraldBlockList.put(Elements.OXYGEN, 18);
        ElementHelper.addItemToItemElementList(Blocks.emerald_block, emeraldBlockList);

        Map quartzBlockList = new HashMap();
        quartzBlockList.put(Elements.SILICON, 1);
        quartzBlockList.put(Elements.OXYGEN, 2);
        ElementHelper.addItemToItemElementList(Blocks.quartz_block, quartzBlockList);

        Map coalBlockList = new HashMap();
        coalBlockList.put(Elements.CARBON, 1);
        ElementHelper.addItemToItemElementList(Blocks.coal_block, coalBlockList);
    }
}