package com.wj44.echem.block;

import net.minecraft.block.BlockOre;

/**
 * Created by Wesley "WJ44" Joosten on 14-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockEChemOre extends BlockEChem
{
    public BlockEChemOre(String oreType, float hardness, int harverstLevel)
    {
        this.setUnlocalizedName(oreType);
        this.setHardness(hardness);
        this.setHarvestLevel("pickaxe", harverstLevel);
    }
}
