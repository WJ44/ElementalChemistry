package com.wj44.echem.util;

import com.wj44.echem.api.Element;
import com.wj44.echem.api.ElementList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Wesley "WJ44" Joosten on 2-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class FormulaHelper
{
    public static String getFormulaFromItemStack(ItemStack itemStack)
    {
        String formula = new String();


        if (itemStack.getItem() == Items.emerald || itemStack.getItem() == Item.getItemFromBlock(Blocks.emerald_block))
        {
            formula = "Be3Al2(SiO3)6";
        }
        else
        {
            ElementList elementList = ElementHelper.getElementList(itemStack);
            for (Element element : elementList.getElements())
            {
                formula += element.symbol + elementList.getAmount(element);
            }
        }

        return getProperFormula(formula);
    }

    public static String getProperFormula(String formula)
    {
        formula = formula.replace("10", "₁₀");
        formula = formula.replace("11", "₁₁");
        formula = formula.replace("12", "₁₂");
        formula = formula.replace("13", "₁₃");
        formula = formula.replace("14", "₁₄");
        formula = formula.replace("15", "₁₅");
        formula = formula.replace("16", "₁₆");
        formula = formula.replace("17", "₁₇");
        formula = formula.replace("18", "₁₈");
        formula = formula.replace("19", "₁₉");
        formula = formula.replace("1", "");
        formula = formula.replace("2", "₂");
        formula = formula.replace("3", "₃");
        formula = formula.replace("4", "₄");
        formula = formula.replace("5", "₅");
        formula = formula.replace("6", "₆");
        formula = formula.replace("7", "₇");
        formula = formula.replace("8", "₈");
        formula = formula.replace("9", "₉");

        return formula;
    }
}
