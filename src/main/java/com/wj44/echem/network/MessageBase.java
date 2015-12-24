package com.wj44.echem.network;

import com.wj44.echem.ElementalChemistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Wesley "WJ44" Joosten on 15-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public abstract class MessageBase<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>
{
    @Override
    public REQ onMessage(REQ message, MessageContext ctx)
    {
        if (ctx.side == Side.SERVER)
        {
            handleServerSide(message, ctx.getServerHandler().playerEntity);
        }
        else
        {
            handleClientSide(message, ElementalChemistry.proxy.getEntityPlayer());
        }
        return null;
    }

    public abstract void handleClientSide(REQ message, EntityPlayer player);

    public abstract void handleServerSide(REQ message, EntityPlayer player);
}
