package com.wj44.elementscore.api;

import java.util.LinkedHashMap;

/**
 * Created by Wesley "WJ44" Joosten on 2-5-2015.
 *
 * Part of the ElementsCore Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class Element
{
    public int number;
    public String symbol;
    public int mass;

    public Element(int number, String symbol, int mass)
    {
        this.number = number;
        this.symbol = symbol;
        this.mass = mass;
        elements.put(number, this);
    }

    public static LinkedHashMap<Integer, Element> elements = new LinkedHashMap<Integer, Element>();

    public static final Element NULL = new Element(0, null, 0);
    public static final Element HYDROGEN = new Element(1, "H", 1);
    public static final Element HELIUM = new Element(2, "He", 4);
    public static final Element LITHIUM = new Element(3, "Li", 7);
    public static final Element BERYLLIUM = new Element(4, "Be", 9);
    public static final Element BORON = new Element(5, "B", 11);
    public static final Element CARBON = new Element(6, "C", 12);
    public static final Element NITROGEN = new Element(7, "N", 14);
    public static final Element OXYGEN = new Element(8, "O", 16);
    public static final Element FLUORINE = new Element(9, "F", 19);
    public static final Element NEON = new Element(10, "Ne", 20);
    public static final Element SODIUM = new Element(11, "Na", 23);
    public static final Element MAGNESIUM = new Element(12, "Mg", 24);
    public static final Element ALUMINIUM = new Element(13, "Al", 27);
    public static final Element SILICON = new Element(14, "Si", 28);
    public static final Element PHOSPHORUS = new Element(15, "P", 31);
    public static final Element SULFUR = new Element(16, "S", 32);
    public static final Element CHLORINE = new Element(17, "Cl", 35);
    public static final Element ARGON = new Element(18, "Ar", 40);
    public static final Element POTASSIUM = new Element(19, "K", 39);
    public static final Element CALCIUM = new Element(20, "Ca", 40);
    public static final Element SCANDIUM = new Element(21, "Sc", 45);
    public static final Element TITANIUM = new Element(22, "Ti", 48);
    public static final Element VANADIUM = new Element(23, "V", 51);
    public static final Element CHROMIUM = new Element(24, "Cr", 52);
    public static final Element MANGANESE = new Element(25, "Mn", 55);
    public static final Element IRON = new Element(26, "Fe", 56);
    public static final Element COBALT = new Element(27, "Co", 59);
    public static final Element NICKEL = new Element(28, "Ni", 59);
    public static final Element COPPER = new Element(29, "Cu", 64);
    public static final Element ZINC = new Element(30, "Zn", 65);
    public static final Element GALLIUM = new Element(31, "Ga", 70);
    public static final Element GERMANIUM = new Element(32, "Ge", 73);
    public static final Element ARSENIC = new Element(33, "As", 75);
    public static final Element SELENIUM = new Element(34, "Se", 79);
    public static final Element BROMINE = new Element(35, "Br", 80);
    public static final Element KRYPTON = new Element(36, "Kr", 84);
    public static final Element RUBIDIUM = new Element(37, "Rb", 85);
    public static final Element STRONTIUM = new Element(38, "Sr", 88);
    public static final Element YTTRIUM = new Element(39, "Y", 89);
    public static final Element ZIRCONIUM = new Element(40, "Zr", 91);
    public static final Element NIOBIUM = new Element(41, "Nb", 93);
    public static final Element MOLYBDENUM = new Element(42, "Mo", 96);
    public static final Element TECHNETIUM = new Element(43, "Tc", 99);
    public static final Element RUTHENIUM = new Element(44, "Ru", 101);
    public static final Element RHODIUM = new Element(45, "Rh", 103);
    public static final Element PALLADIUM = new Element(46, "Pd", 106);
    public static final Element SILVER = new Element(47, "Ag", 108);
    public static final Element CADMIUM = new Element(48, "Cd", 112);
    public static final Element INDIUM = new Element(49, "In", 115);
    public static final Element TIN = new Element(50, "Sn", 119);
    public static final Element ANTIMONY = new Element(51, "Sb", 122);
    public static final Element TELLURIUM = new Element(52, "Te", 128);
    public static final Element IODINE = new Element(53, "I", 127);
    public static final Element XENON = new Element(54, "Xe", 131);
    public static final Element CAESIUM = new Element(55, "Cs", 133);
    public static final Element BARIUM = new Element(56, "Ba", 137);
    public static final Element LANTHANUM = new Element(57, "La", 139);
    public static final Element CERIUM = new Element(58, "Ce", 140);
    public static final Element PRASEODYMIUM = new Element(59, "Pr", 141);
    public static final Element NEODYMIUM = new Element(60, "Nd", 144);
    public static final Element PROMETHIUM = new Element(61, "Pm", 147);
    public static final Element SAMARIUM = new Element(62, "Sm", 150);
    public static final Element EUROPIUM = new Element(63, "Eu", 152);
    public static final Element GADOLINIUM = new Element(64, "Gd", 157);
    public static final Element TERBIUM = new Element(65, "Tb", 159);
    public static final Element DYSPROSIUM = new Element(66, "Dy", 163);
    public static final Element HOLMIUM = new Element(67, "Ho", 165);
    public static final Element ERBIUM = new Element(68, "Er", 167);
    public static final Element THULIUM = new Element(69, "Tm", 169);
    public static final Element YTTERBIUM = new Element(70, "Yb", 173);
    public static final Element LUTETIUM = new Element(71, "Lu", 175);
    public static final Element HAFNIUM = new Element(72, "Hf", 178);
    public static final Element TANTALUM = new Element(73, "Ta", 181);
    public static final Element TUNGSTEN = new Element(74, "W", 184);
    public static final Element RHENIUM = new Element(75, "Re", 186);
    public static final Element OSMIUM = new Element(76, "Os", 190);
    public static final Element IRIDIUM = new Element(77, "Ir", 192);
    public static final Element PLATINUM = new Element(78, "Pt", 195);
    public static final Element GOLD = new Element(79, "Au", 197);
    public static final Element MERCURY = new Element(80, "Hg", 201);
    public static final Element THALLIUM = new Element(81, "Tl", 204);
    public static final Element LEAD = new Element(82, "Pb", 207);
    public static final Element BISMUTH = new Element(83, "Bi", 209);
    public static final Element POLONIUM = new Element(84, "Po", 209);
    public static final Element ASTATINE = new Element(85, "At", 210);
    public static final Element RADON = new Element(86, "Rn", 222);
    public static final Element FRANCIUM = new Element(87, "Fr", 223);
    public static final Element RADIUM = new Element(88, "Ra", 226);
    public static final Element ACTINIUM = new Element(89, "Ac", 227);
    public static final Element THORIUM = new Element(90, "Th", 232);
    public static final Element PROTACTINIUM = new Element(91, "Pa", 231);
    public static final Element URANIUM = new Element(92, "U", 238);
    public static final Element NEPTUNIUM = new Element(93, "Np", 237);
    public static final Element PLUTONIUM = new Element(94, "Pu", 244);
    public static final Element AMERICIUM = new Element(95, "Am", 243);
    public static final Element CURIUM = new Element(96, "Cm", 247);
    public static final Element BERKELIUM = new Element(97, "Bk", 247);
    public static final Element CALIFORNIUM = new Element(98, "Cf", 251);
    public static final Element EINSTEINIUM = new Element(99, "Es", 252);
    public static final Element FERMIUM = new Element(100, "Fm", 257);
    public static final Element MENDELEVIUM = new Element(101, "Md", 258);
    public static final Element NOBELIUM = new Element(102, "No", 259);
    public static final Element LAWRENCIUM = new Element(103, "Lr", 260);
    public static final Element RUTHERFORDIUM = new Element(104, "Rf", 261);
    public static final Element DUBNIUM = new Element(105, "Db", 262);
    public static final Element SEABORGIUM = new Element(106, "Sg", 263);
    public static final Element BOHRIUM = new Element(107, "Bh", 262);
    public static final Element HASSIUM = new Element(108, "Hs", 265);
    public static final Element MEITNERIUM = new Element(109, "Mt", 266);
    public static final Element DARMSTADTIUM = new Element(110, "Ds", 269);
    public static final Element ROENTGENIUM = new Element(111, "Rg", 272);
    public static final Element COPERNICIUM = new Element(112, "Cn", 277);
    public static final Element UNUNTRIUM = new Element(113, "Uut", 284);
    public static final Element FLEROVIUM = new Element(114, "Fl", 289);
    public static final Element UNUNPENTIUM = new Element(115, "Uup", 295);
    public static final Element LIVERMORIUM = new Element(116, "Lv", 297);
    public static final Element UNUNSEPTIUM = new Element(117, "Uus", 291);
    public static final Element UNUNOCTIUM = new Element(118, "Uuo", 293);


}
