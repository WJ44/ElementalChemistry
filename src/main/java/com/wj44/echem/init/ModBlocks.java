package com.wj44.echem.init;

import com.wj44.echem.block.*;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 12-7-2014.
 *
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final Block testBlock = new BlockEChem(); //TODO Delete
    public static final Block platinumOre = new BlockEChemOre(Names.Blocks.PLATINUM_ORE, 3.0F, 2);
    public static final Block silverOre = new BlockEChemOre(Names.Blocks.SILVER_ORE, 3.0F, 2);
    public static final Block decomposer = new BlockDecomposer();
    public static final Block itemScanner = new BlockItemScanner();

    public static void init()
    {
        GameRegistry.registerBlock(testBlock, "testBlock"); //TODO Delete
        GameRegistry.registerBlock(platinumOre, Names.Blocks.PLATINUM_ORE);
        GameRegistry.registerBlock(silverOre, Names.Blocks.SILVER_ORE);
        GameRegistry.registerBlock(decomposer, Names.Blocks.DECOMPOSER);
        GameRegistry.registerBlock(itemScanner, Names.Blocks.ITEM_SCANNER);
    }
}
