package com.wj44.echem.init;

import com.wj44.echem.block.*;
import com.wj44.echem.creativetab.CreativeTabEChem;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Reference;
import com.wj44.echem.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
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

    public static final Block decomposer = new BlockDecomposer(false).setCreativeTab(CreativeTabEChem.ECHEM_TAB);
    public static final Block lit_decomposer = new BlockDecomposer(true);
    public static final Block itemScanner = new BlockItemScanner(false).setCreativeTab(CreativeTabEChem.ECHEM_TAB);
    public static final Block lit_itemScanner = new BlockItemScanner(true);
    public static final Block composer = new BlockComposer(false).setCreativeTab(CreativeTabEChem.ECHEM_TAB);
    public static final Block lit_composer = new BlockComposer(true);
    public static final Block dataBank = new BlockDataBank();
    public static final Block dataCable = new BlockDataCable();

    public static void init()
    {
        GameRegistry.registerBlock(decomposer, Names.Blocks.DECOMPOSER);
        GameRegistry.registerBlock(lit_decomposer, "lit_" + Names.Blocks.DECOMPOSER);
        GameRegistry.registerBlock(itemScanner, Names.Blocks.ITEM_SCANNER);
        GameRegistry.registerBlock(lit_itemScanner, "lit_" + Names.Blocks.ITEM_SCANNER);
        GameRegistry.registerBlock(composer, Names.Blocks.COMPOSER);
        GameRegistry.registerBlock(lit_composer, "lit_" + Names.Blocks.COMPOSER);
        GameRegistry.registerBlock(dataBank, Names.Blocks.DATA_BANK);
        GameRegistry.registerBlock(dataCable, Names.Blocks.DATA_CABLE);
    }

    public static void registerRenders()
    {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        renderItem.getItemModelMesher().register(Item.getItemFromBlock(decomposer), 0, Textures.Block.DECOMPOSER);
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(itemScanner), 0, Textures.Block.ITEM_SCANNER);
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(composer), 0, Textures.Block.COMPOSER);
    }
}
