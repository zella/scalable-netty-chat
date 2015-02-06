package org.zella.chat.server;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zella.chat.messages.chat.RoomMessage;

/**
 * Created by dru on 02.02.2015.
 */
public class RoomMessageServerHandler extends SimpleChannelInboundHandler<RoomMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RoomMessage msg) throws Exception {
        System.out.println("Room server handler: " + msg.toString());
        ChatServer.channelsByRoom.get(msg.getToRoom()).writeAndFlush(msg);
    }


}
