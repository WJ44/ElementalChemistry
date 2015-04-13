package com.wj44.echem.reference;

/**
 * Created by Wesley "WJ44" Joosten on 31-8-2014.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public final class Names
{
    public static final class Blocks
    {
        public static final String PLATINUM_ORE = "platinumOre";
        public static final String SILVER_ORE = "silverOre";
        public static final String DECOMPOSER = "decomposer";
        public static final String ITEM_SCANNER = "itemScanner";
        public static final String COMPOSER = "composer";
        public static final String DATA_BANK = "dataBank";
    }

    public static final class Items
    {
        public static final String LOGO = "logo";
        public static final String ELEMENT_CONTAINER = "elementContainer";
        public static final String[] ELEMENT_CONTAINER_SUBTYPES = {"Empty", "Hydrogen", "Helium", "Lithium", "Beryllium", "Boron", "Carbon", "Nitrogen", "Oxygen", "Fluorine", "Neon", "Sodium", "Magnesium", "Aluminium", "Silicon", "Phosphorus", "Sulfur", "Chlorine", "Argon", "Potassium", "Calcium", "Scandium", "Titanium", "Vanadium", "Chromium", "Manganese", "Iron", "Cobalt", "Nickel", "Copper", "Zinc", "Gallium", "Germanium", "Arsenic", "Selenium", "Bromine", "Krypton", "Rubidium", "Strontium", "Yttrium", "Zirconium", "Niobium", "Molybdenum", "Technetium", "Ruthenium", "Rhodium", "Palladium", "Silver", "Cadmium", "Indium", "Tin", "Antimony", "Tellurium", "Iodine", "Xenon", "Caesium", "Barium", "Lanthanum", "Cerium", "Praseodymium", "Neodymium", "Promethium", "Samarium", "Europium", "Gadolinium", "Terbium", "Dysprosium", "Holmium", "Erbium", "Thulium", "Ytterbium", "Lutetium", "Hafnium", "Tantalum", "Tungsten", "Rhenium", "Osmium", "Iridium", "Platinum", "Gold", "Mercury", "Thallium", "Lead", "Bismuth", "Polonium", "Astatine", "Radon", "Francium", "Radium", "Actinium", "Thorium", "Protactinium", "Uranium", "Neptunium", "Plutonium", "Americium", "Curium", "Berkelium", "Californium", "Einsteinium", "Fermium", "Mendelevium", "Nobelium", "Lawrencium", "Rutherfordium", "Dubnium", "Seaborgium", "Bohrium", "Hassium", "Meitnerium", "Darmstadtium", "Roentgenium", "Copernicium", "Ununtrium", "Flerovium", "Ununpentium", "Livermorium", "Ununseptium", "Ununoctium"};
        public static final String DATA_CARD = "dataCard";
        public static final String DATA_BANK = "dataBank";
    }

    public static final class Containers
    {
        private static final String PREFIX = "container." + Textures.RESOURCE_PREFIX;
        public static final String DECOMPOSER = PREFIX + Blocks.DECOMPOSER;
        public static final String ITEM_SCANNER = PREFIX + Blocks.ITEM_SCANNER;
        public static final String COMPOSER = PREFIX + Blocks.COMPOSER;
        public static final String DATA_BANK = PREFIX + Blocks.DATA_BANK;

    }

    public static final class NBT
    {
        public static final String ITEMS = "Items";
    }
}
