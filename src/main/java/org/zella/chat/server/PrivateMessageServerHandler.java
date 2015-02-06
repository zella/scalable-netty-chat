package org.zella.chat.server;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zella.chat.messages.chat.PrivateMessage;

/**
 * Created by dru on 02.02.2015.
 */
public class PrivateMessageServerHandler extends SimpleChannelInboundHandler<PrivateMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PrivateMessage msg) throws Exception {
        System.out.println("Private server handler: " + msg.toString());
        ChatServer.channelsByUser.get(msg.getTo()).writeAndFlush(msg);
    }


}
