package org.zella.chat.server;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zella.chat.messages.chat.RoomMessage;
import org.zella.chat.messages.presence.IPresenceMessage;

/**
 * Created by dru on 02.02.2015.
 */
public class PresenceMessageServerHandler extends SimpleChannelInboundHandler<IPresenceMessage> {

    //TODO no handler for it. Sends messages to room, when client connects or disconects
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IPresenceMessage msg) throws Exception {
        System.out.println("Presence server handler: " + msg.toString());
        // ChatServer.channelsByRoom.get(msg.getToRoom()).writeAndFlush(msg);
    }


}
