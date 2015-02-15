package com.wj44.echem.block;

import com.wj44.echem.init.ModBlocks;
import com.wj44.echem.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 12-7-2014.
 * -
 * Part of the Elemental Chemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockTest extends BlockEChem
{
    public BlockTest()
    {
        super();
        this.setBlockName("testBlock");
        setBlockTextureName(Reference.LOWERCASE_MOD_ID + ";" + "testBlock");
    }
}

//TODO Delete class