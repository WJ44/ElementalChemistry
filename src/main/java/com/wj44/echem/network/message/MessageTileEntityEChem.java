package com.wj44.echem.network.message;

import com.wj44.echem.tileentity.TileEntityEChem;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Wesley on 2-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class MessageTileEntityEChem implements IMessage, IMessageHandler<MessageTileEntityEChem, IMessage>
{
    public int x, y, z;
    public byte orientation, state;
    public String customName, owner;

    public MessageTileEntityEChem()
    {
    }

    public MessageTileEntityEChem(TileEntityEChem tileEntityEChem)
    {
        this.x = tileEntityEChem.xCoord;
        this.y = tileEntityEChem.yCoord;
        this.z = tileEntityEChem.zCoord;
        this.orientation = (byte) tileEntityEChem.getOrientation().ordinal();
        this.state = (byte) tileEntityEChem.getState();
        this.customName = tileEntityEChem.getCustomName();
        this.owner = tileEntityEChem.getOwner();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.orientation = buf.readByte();
        this.state = buf.readByte();
        int customNameLength = buf.readInt();
        this.customName = new String(buf.readBytes(customNameLength).array());
        int ownerLength = buf.readInt();
        this.owner = new String(buf.readBytes(ownerLength).array());
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeByte(orientation);
        buf.writeByte(state);
        buf.writeInt(customName.length());
        buf.writeBytes(customName.getBytes());
        buf.writeInt(owner.length());
        buf.writeBytes(owner.getBytes());
    }

    @Override
    public IMessage onMessage(MessageTileEntityEChem message, MessageContext ctx)
    {
        TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);

        if (tileEntity instanceof TileEntityEChem)
        {
            ((TileEntityEChem) tileEntity).setOrientation(message.orientation);
            ((TileEntityEChem) tileEntity).setState(message.state);
            ((TileEntityEChem) tileEntity).setCustomName(message.customName);
            ((TileEntityEChem) tileEntity).setOwner(message.owner);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return String.format("MessageTileEntityEChem - x:%s, y:%s, z:%s, orientation:%s, state:%s, customName:%s, owner:%s", x, y, z, orientation, state, customName, owner);
    }
}
