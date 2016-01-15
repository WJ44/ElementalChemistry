package com.wj44.echem.handler;

import com.wj44.echem.client.gui.inventory.GuiDataBank;
import com.wj44.echem.inventory.ContainerDataBank;
import com.wj44.echem.reference.Guis;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


/**
 * Created by Wesley "WJ44" Joosten on 15/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        if (ID == Guis.DATA_BANK.ordinal())
        {
            return new ContainerDataBank(player.inventory, (IInventory) world.getTileEntity(pos));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        if (ID == Guis.DATA_BANK.ordinal())
        {
            return new GuiDataBank(player.inventory, (IInventory) world.getTileEntity(pos));
        }
        return null;
    }
}
