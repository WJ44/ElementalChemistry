package com.wj44.echem.handler;

import com.wj44.echem.client.gui.inventory.GuiDecomposer;
import com.wj44.echem.client.gui.inventory.GuiItemScanner;
import com.wj44.echem.inventory.ContainerDecomposer;
import com.wj44.echem.inventory.ContainerItemScanner;
import com.wj44.echem.reference.GUIs;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Wesley "WJ44" Joosten on 19-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GUIs.DECOMPOSER.ordinal())
        {
            return new ContainerDecomposer(player.inventory, (TileEntityDecomposer) world.getTileEntity(x, y, z));
        }
        else if (ID == GUIs.ITEM_SCANNER.ordinal())
        {
            return new ContainerItemScanner(player.inventory, (TileEntityItemScanner) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == GUIs.DECOMPOSER.ordinal())
        {
            return new GuiDecomposer(player.inventory, (TileEntityDecomposer) world.getTileEntity(x, y, z));
        }
        else if (ID == GUIs.ITEM_SCANNER.ordinal())
        {
            return new GuiItemScanner(player.inventory, (TileEntityItemScanner) world.getTileEntity(x, y, z));
        }
        return null;
    }
}
