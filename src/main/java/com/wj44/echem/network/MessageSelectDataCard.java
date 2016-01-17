package com.wj44.echem.network;

import com.wj44.echem.inventory.ContainerDataBank;
import com.wj44.echem.util.LogHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Wesley "WJ44" Joosten on 17/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class MessageSelectDataCard implements IMessage, IMessageHandler<MessageSelectDataCard, IMessage>
{
    public MessageSelectDataCard()
    {
    }

    private int selectedSlot;

    public MessageSelectDataCard(int selectedSlot)
    {
        this.selectedSlot = selectedSlot;
    }


    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(selectedSlot);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        selectedSlot = buf.readInt();
    }

    @Override
    public IMessage onMessage(final MessageSelectDataCard message, final MessageContext ctx)
    {
        EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
        serverPlayer.getServerForPlayer().addScheduledTask(new Runnable()
        {
            @Override
            public void run()
            {
                processMessage(message, ctx);
            }
        });
        return null;
    }

    private static void processMessage(MessageSelectDataCard message, MessageContext ctx)
    {
        EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
        ((ContainerDataBank)serverPlayer.openContainer).selectSlot(message.selectedSlot);

    }
}
