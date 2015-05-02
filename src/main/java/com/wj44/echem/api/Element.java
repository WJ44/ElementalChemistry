package com.wj44.echem.api;

import java.util.LinkedHashMap;

/**
 * Created by Wesley "WJ44" Joosten on 2-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class Element
{
    public int number;
    public String symbol;

    public Element(int number, String symbol)
    {
        this.number = number;
        this.symbol = symbol;
        elements.put(number, this);
    }

    public static LinkedHashMap<Integer, Element> elements = new LinkedHashMap<Integer, Element>();

    public static final Element NULL = new Element(0, null);
    public static final Element HYDROGEN = new Element(1, "H");
    public static final Element HELIUM = new Element(2, "He");
    public static final Element LITHIUM = new Element(3, "Li");
    public static final Element BERYLLIUM = new Element(4, "Be");
    public static final Element BORON = new Element(5, "B");
    public static final Element CARBON = new Element(6, "C");
    public static final Element NITROGEN = new Element(7, "N");
    public static final Element OXYGEN = new Element(8, "O");
    public static final Element FLUORINE = new Element(9, "F");
    public static final Element NEON = new Element(10, "Ne");
    public static final Element SODIUM = new Element(11, "Na");
    public static final Element MAGNESIUM = new Element(12, "Mg");
    public static final Element ALUMINIUM = new Element(13, "Al");
    public static final Element SILICON = new Element(14, "Si");
    public static final Element PHOSPHORUS = new Element(15, "P");
    public static final Element SULFUR = new Element(16, "S");
    public static final Element CHLORINE = new Element(17, "Cl");
    public static final Element ARGON = new Element(18, "Ar");
    public static final Element POTASSIUM = new Element(19, "K");
    public static final Element CALCIUM = new Element(20, "Ca");
    public static final Element SCANDIUM = new Element(21, "Sc");
    public static final Element TITANIUM = new Element(22, "Ti");
    public static final Element VANADIUM = new Element(23, "V");
    public static final Element CHROMIUM = new Element(24, "Cr");
    public static final Element MANGANESE = new Element(25, "Mn");
    public static final Element IRON = new Element(26, "Fe");
    public static final Element COBALT = new Element(27, "Co");
    public static final Element NICKEL = new Element(28, "Ni");
    public static final Element COPPER = new Element(29, "Cu");
    public static final Element ZINC = new Element(30, "Zn");
    public static final Element GALLIUM = new Element(31, "Ga");
    public static final Element GERMANIUM = new Element(32, "Ge");
    public static final Element ARSENIC = new Element(33, "As");
    public static final Element SELENIUM = new Element(34, "Se");
    public static final Element BROMINE = new Element(35, "Br");
    public static final Element KRYPTON = new Element(36, "Kr");
    public static final Element RUBIDIUM = new Element(37, "Rb");
    public static final Element STRONTIUM = new Element(38, "Sr");
    public static final Element YTTRIUM = new Element(39, "Y");
    public static final Element ZIRCONIUM = new Element(40, "Zr");
    public static final Element NIOBIUM = new Element(41, "Nb");
    public static final Element MOLYBDENUM = new Element(42, "Mo");
    public static final Element TECHNETIUM = new Element(43, "Tc");
    public static final Element RUTHENIUM = new Element(44, "Ru");
    public static final Element RHODIUM = new Element(45, "Rh");
    public static final Element PALLADIUM = new Element(46, "Pd");
    public static final Element SILVER = new Element(47, "Ag");
    public static final Element CADMIUM = new Element(48, "Cd");
    public static final Element INDIUM = new Element(49, "In");
    public static final Element TIN = new Element(50, "Sn");
    public static final Element ANTIMONY = new Element(51, "Sb");
    public static final Element TELLURIUM = new Element(52, "Te");
    public static final Element IODINE = new Element(53, "I");
    public static final Element XENON = new Element(54, "Xe");
    public static final Element CAESIUM = new Element(55, "Cs");
    public static final Element BARIUM = new Element(56, "Ba");
    public static final Element LANTHANUM = new Element(57, "La");
    public static final Element CERIUM = new Element(58, "Ce");
    public static final Element PRASEODYMIUM = new Element(59, "Pr");
    public static final Element NEODYMIUM = new Element(60, "Nd");
    public static final Element PROMETHIUM = new Element(61, "Pm");
    public static final Element SAMARIUM = new Element(62, "Sm");
    public static final Element EUROPIUM = new Element(63, "Eu");
    public static final Element GADOLINIUM = new Element(64, "Gd");
    public static final Element TERBIUM = new Element(65, "Tb");
    public static final Element DYSPROSIUM = new Element(66, "Dy");
    public static final Element HOLMIUM = new Element(67, "Ho");
    public static final Element ERBIUM = new Element(68, "Er");
    public static final Element THULIUM = new Element(69, "Tm");
    public static final Element YTTERBIUM = new Element(70, "Yb");
    public static final Element LUTETIUM = new Element(71, "Lu");
    public static final Element HAFNIUM = new Element(72, "Hf");
    public static final Element TANTALUM = new Element(73, "Ta");
    public static final Element TUNGSTEN = new Element(74, "W");
    public static final Element RHENIUM = new Element(75, "Re");
    public static final Element OSMIUM = new Element(76, "Os");
    public static final Element IRIDIUM = new Element(77, "Ir");
    public static final Element PLATINUM = new Element(78, "Pt");
    public static final Element GOLD = new Element(79, "Au");
    public static final Element MERCURY = new Element(80, "Hg");
    public static final Element THALLIUM = new Element(81, "Tl");
    public static final Element LEAD = new Element(82, "Pb");
    public static final Element BISMUTH = new Element(83, "Bi");
    public static final Element POLONIUM = new Element(84, "Po");
    public static final Element ASTATINE = new Element(85, "At");
    public static final Element RADON = new Element(86, "Rn");
    public static final Element FRANCIUM = new Element(87, "Fr");
    public static final Element RADIUM = new Element(88, "Ra");
    public static final Element ACTINIUM = new Element(89, "Ac");
    public static final Element THORIUM = new Element(90, "Th");
    public static final Element PROTACTINIUM = new Element(91, "Pa");
    public static final Element URANIUM = new Element(92, "U");
    public static final Element NEPTUNIUM = new Element(93, "Np");
    public static final Element PLUTONIUM = new Element(94, "Pu");
    public static final Element AMERICIUM = new Element(95, "Am");
    public static final Element CURIUM = new Element(96, "Cm");
    public static final Element BERKELIUM = new Element(97, "Bk");
    public static final Element CALIFORNIUM = new Element(98, "Cf");
    public static final Element EINSTEINIUM = new Element(99, "Es");
    public static final Element FERMIUM = new Element(100, "Fm");
    public static final Element MENDELEVIUM = new Element(101, "Md");
    public static final Element NOBELIUM = new Element(102, "No");
    public static final Element LAWRENCIUM = new Element(103, "Lr");
    public static final Element RUTHERFORDIUM = new Element(104, "Rf");
    public static final Element DUBNIUM = new Element(105, "Db");
    public static final Element SEABORGIUM = new Element(106, "Sg");
    public static final Element BOHRIUM = new Element(107, "Bh");
    public static final Element HASSIUM = new Element(108, "Hs");
    public static final Element MEITNERIUM = new Element(109, "Mt");
    public static final Element DARMSTADTIUM = new Element(110, "Ds");
    public static final Element ROENTGENIUM = new Element(111, "Rg");
    public static final Element COPERNICIUM = new Element(112, "Cn");
    public static final Element UNUNTRIUM = new Element(113, "Uut");
    public static final Element FLEROVIUM = new Element(114, "Fl");
    public static final Element UNUNPENTIUM = new Element(115, "Uup");
    public static final Element LIVERMORIUM = new Element(116, "Lv");
    public static final Element UNUNSEPTIUM = new Element(117, "Uus");
    public static final Element UNUNOCTIUM = new Element(118, "Uuo");

}
