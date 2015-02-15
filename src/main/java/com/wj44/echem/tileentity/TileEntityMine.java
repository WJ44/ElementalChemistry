package com.wj44.echem.tileentity;

import com.wj44.echem.network.NetworkHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityMine extends TileEntityEChem
{
    private int timer = 60;
    private ItemStack camoStack;


    @Override
    public void updateEntity()
    {
        if (timer > 0) timer--;
        if (timer == 0 && !worldObj.isRemote)
        {
            List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2));

            if (entities.size() > 0)
            {
                worldObj.createExplosion(null, xCoord, yCoord, zCoord, 3.0F, true);

            }
        }
    }

    public void setCamo(ItemStack stack)
    {
        camoStack = stack;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public ItemStack getCamo()
    {
        return camoStack;
    }

    public void writeToPacket(ByteBuf buf)
    {
        ByteBufUtils.writeItemStack(buf, camoStack);
    }

    public void readFromPacket(ByteBuf buf)
    {
        camoStack = ByteBufUtils.readItemStack(buf);

        worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        timer = tag.getInteger("timer");

        if (tag.hasKey("camoStack"))
        {
            camoStack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("camoStack"));
        }
        else
        {
            camoStack = null;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("timer", timer);

        if (camoStack != null)
        {
            NBTTagCompound t = new NBTTagCompound();
            camoStack.writeToNBT(t);
            tag.setTag("camoStack", t);

        }
    }
}
