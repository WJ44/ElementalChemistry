package com.wj44.echem.util;

import com.wj44.echem.reference.Elements;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Wesley "WJ44" Joosten on 24-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class DataHelper
{
    public static String getFormulaFromItemStack(ItemStack itemStack)
    {
        String formula = new String();

        ElementHelper elementHelper = new ElementHelper(itemStack.getItem());

        Elements[] elements = {};
        elements = elementHelper.getElements().toArray(elements);

        if (itemStack.getItem() == Items.emerald || itemStack.getItem() == Item.getItemFromBlock(Blocks.emerald_block))
        {
            formula = "Be3Al2(SiO3)6";
        }
        else
        {
            for (Elements element : elements)
            {
                formula += getSymbolFromElement(element) + elementHelper.getAmount(element);
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

    public static String getSymbolFromElement(Elements element)
    {
        switch (element)
        {
            case HYDROGEN: return "H";
            case HELIUM: return "He";
            case LITHIUM: return "Li";
            case BERYLLIUM: return "Be";
            case BORON: return "B";
            case CARBON: return "C";
            case NITROGEN: return "N";
            case OXYGEN: return "O";
            case FLUORINE: return "F";
            case NEON: return "Ne";
            case SODIUM: return "Na";
            case MAGNESIUM: return "Mg";
            case ALUMINIUM: return "Al";
            case SILICON: return "Si";
            case PHOSPHORUS: return "P";
            case SULFUR: return "S";
            case CHLORINE: return "Cl";
            case ARGON: return "Ar";
            case POTASSIUM: return "K";
            case CALCIUM: return "Ca";
            case SCANDIUM: return "Sc";
            case TITANIUM: return "Ti";
            case VANADIUM: return "V";
            case CHROMIUM: return "Cr";
            case MANGANESE: return "Mn";
            case IRON: return "Fe";
            case COBALT: return "Co";
            case NICKEL: return "Ni";
            case COPPER: return "Cu";
            case ZINC: return "Zn";
            case GALLIUM: return "Ga";
            case GERMANIUM: return "Ge";
            case ARSENIC: return "As";
            case SELENIUM: return "Se";
            case BROMINE: return "Br";
            case KRYPTON: return "Kr";
            case RUBIDIUM: return "Rb";
            case STRONTIUM: return "Sr";
            case YTTRIUM: return "Y";
            case ZIRCONIUM: return "Zr";
            case NIOBIUM: return "Nb";
            case MOLYBDENUM: return "Mo";
            case TECHNETIUM: return "Tc";
            case RUTHENIUM: return "Ru";
            case RHODIUM: return "Rh";
            case PALLADIUM: return "Pd";
            case SILVER: return "Ag";
            case CADMIUM: return "Cd";
            case INDIUM: return "In";
            case TIN: return "Sn";
            case ANTIMONY: return "Sb";
            case TELLURIUM: return "Te";
            case IODINE: return "I";
            case XENON: return "Xe";
            case CAESIUM: return "Cs";
            case BARIUM: return "Ba";
            case LANTHANUM: return "La";
            case CERIUM: return "Ce";
            case PRASEODYMIUM: return "Pr";
            case NEODYMIUM: return "Nd";
            case PROMETHIUM: return "Pm";
            case SAMARIUM: return "Sm";
            case EUROPIUM: return "Eu";
            case GADOLINIUM: return "Gd";
            case TERBIUM: return "Tb";
            case DYSPROSIUM: return "Dy";
            case HOLMIUM: return "Ho";
            case ERBIUM: return "Er";
            case THULIUM: return "Tm";
            case YTTERBIUM: return "Yb";
            case LUTETIUM: return "Lu";
            case HAFNIUM: return "Hf";
            case TANTALUM: return "Ta";
            case TUNGSTEN: return "W";
            case RHENIUM: return "Re";
            case OSMIUM: return "Os";
            case IRIDIUM: return "Ir";
            case PLATINUM: return "Pt";
            case GOLD: return "Au";
            case MERCURY: return "Hg";
            case THALLIUM: return "Tl";
            case LEAD: return "Pb";
            case BISMUTH: return "Bi";
            case POLONIUM: return "Po";
            case ASTATINE: return "At";
            case RADON: return "Rn";
            case FRANCIUM: return "Fr";
            case RADIUM: return "Ra";
            case ACTINIUM: return "Ac";
            case THORIUM: return "Th";
            case PROTACTINIUM: return "Pa";
            case URANIUM: return "U";
            case NEPTUNIUM: return "Np";
            case PLUTONIUM: return "Pu";
            case AMERICIUM: return "Am";
            case CURIUM: return "Cm";
            case BERKELIUM: return "Bk";
            case CALIFORNIUM: return "Cf";
            case EINSTEINIUM: return "Es";
            case FERMIUM: return "Fm";
            case MENDELEVIUM: return "Md";
            case NOBELIUM: return "No";
            case LAWRENCIUM: return "Lr";
            case RUTHERFORDIUM: return "Rf";
            case DUBNIUM: return "Db";
            case SEABORGIUM: return "Sg";
            case BOHRIUM: return "Bh";
            case HASSIUM: return "Hs";
            case MEITNERIUM: return "Mt";
            case DARMSTADTIUM: return "Ds";
            case ROENTGENIUM: return "Rg";
            case COPERNICIUM: return "Cn";
            case UNUNTRIUM: return "Uut";
            case FLEROVIUM: return "Fl";
            case UNUNPENTIUM: return "Uup";
            case LIVERMORIUM: return "Lv";
            case UNUNSEPTIUM: return "Uus";
            case UNUNOCTIUM: return "Uuo";
        }
        return "Error";
    }
}
