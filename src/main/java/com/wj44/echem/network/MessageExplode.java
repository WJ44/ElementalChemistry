package com.wj44.echem.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class MessageExplode extends MessageBase<MessageExplode> //TODO delete class
{
    private float explosionSize;

    public MessageExplode()
    {

    }

    public MessageExplode(float explosionSize)
    {
        this.explosionSize = explosionSize;
    }

    @Override
    public void handleClientSide(MessageExplode message, EntityPlayer player)
    {

    }

    @Override
    public void handleServerSide(MessageExplode message, EntityPlayer player)
    {
        player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, message.explosionSize, true);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        explosionSize = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeFloat(explosionSize);
    }
}
