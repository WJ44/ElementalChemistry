package com.wj44.echem.world.gen;

import com.wj44.echem.init.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by Wesley "WJ44" Joosten on 14-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class WorldGeneratorEChem implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int x = chunkX * 16;
        int z = chunkZ * 16;
        switch (world.provider.dimensionId)
        {
            case 0:
                generateSurface(world, x, z, random);
            case -1:
                generateNether(world, x, z, random);
            case 1:
                generateEnd(world, x, z, random);
                break;
            default:
                generateSurface(world, x, z, random);
        }
    }

    //Generation for the overworld
    private void generateSurface(World world, int x, int z, Random random)
    {;
        oreGen(ModBlocks.platinumOre, 0, 64, 8, 10, world, x, z, random);
        oreGen(ModBlocks.silverOre, 0, 64, 8, 10, world, x, z, random);
    }

    //Generation for the nether
    private void generateNether(World world, int x, int z, Random random)
    {

    }

    //Generation for the end
    private void generateEnd(World world, int x, int z, Random random)
    {

    }

    /**
     * Adds ore generation
     * @param block The ore
     * @param minHeight The lowest level the ore generates at
     * @param heightModifier How many levels above the minHeight
     * @param veinSize The (average?) size of a vein
     * @param density How often per chunk the ore generates
     * @param world
     * @param x
     * @param z
     * @param random
     */
    private void oreGen(Block block, int minHeight, int heightModifier, int veinSize, int density, World world, int x, int z, Random random)
    {
        for (int i = 0; i < density; i++)
        {
            int randX = x + random.nextInt(16);
            int randY = minHeight + random.nextInt(heightModifier);
            int randZ = z + random.nextInt(16);
            new WorldGenMinable(block, veinSize).generate(world, random, randX, randY, randZ);
        }
    }
}
