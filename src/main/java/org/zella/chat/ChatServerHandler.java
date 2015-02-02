package org.zella.chat;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.zella.chat.hazelcast.HazelcastMessage;
import java.util.UUID;

/**
 * Created by dru on 02.02.2015.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {


    static final AttributeKey<UUID> channelId = AttributeKey.valueOf("ChannelId");


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        for (Channel c : channels) {
//            c.writeAndFlush("[SERVER] - " + ctx.channel().remoteAddress() + " has joined!\n");
//        }
        //we need determine who sender
        Channel channel = ctx.channel();
        channel.attr(channelId).set(UUID.randomUUID());
        ChatServer.channels.add(channel);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        //we need determine who sender
        UUID channelUuid = channel.attr(channelId).get();
        ChatServer.distributedTopic.publish(new HazelcastMessage(msg, channelUuid));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        for (Channel c : channels) {
//            c.writeAndFlush("[SERVER] - " + ctx.channel().remoteAddress() + " has left\n");
//        }
        ChatServer.channels.remove(ctx.channel());
    }


}
