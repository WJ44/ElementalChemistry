package com.wj44.echem.tileentity;

import com.wj44.echem.network.DescriptionHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityEChem extends TileEntity
{
    @Override
    public Packet getDescriptionPacket()
    {
        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);

        writeToPacket(buf);

        return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
    }

    public void writeToPacket(ByteBuf buf)
    {

    }

    public void readFromPacket(ByteBuf buf)
    {

    }
}
