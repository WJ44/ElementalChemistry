package com.wj44.echem.init;

import com.wj44.echem.block.BlockDataBank;
import com.wj44.echem.reference.Names;
import com.wj44.echem.reference.Reference;
import com.wj44.echem.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Wesley "WJ44" Joosten on 29/12/2015.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final Block dataBank = new BlockDataBank();

    public static void init()
    {
        GameRegistry.registerBlock(dataBank, Names.Blocks.DATA_BANK);
    }

    public static void registerRenders()
    {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        renderItem.getItemModelMesher().register(Item.getItemFromBlock(dataBank), 0, Textures.Block.DATA_BANK);
    }
}
