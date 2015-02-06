package org.zella.chat.server;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.zella.chat.messages.login.LoginAcceptedMessage;
import org.zella.chat.messages.login.LoginRequestMessage;
import org.zella.chat.messages.presence.JoinMessage;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dru on 02.02.2015.
 */
public class LoginServerHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    private static final AttributeKey<String> KEY_USER = AttributeKey.valueOf("userName");


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        System.out.println("Login server handler: " + msg.toString());

        final String room = msg.getRoom();
        final String name = msg.getName();

        Channel channel = ctx.channel();

        //TODO validator
        channel.attr(KEY_USER).set(msg.getName());

        //TODO not work send presen
        ChatServer.channelsByRoom.get(msg.getRoom()).add(channel);
        //TODO inverse map.
        ChatServer.channelsByUser.put(msg.getName(), channel);

        List<String> usersInRoom = ChatServer.channelsByRoom.get(msg.getRoom()).stream().map(u -> u.attr(KEY_USER).get()).collect(Collectors.toList());
        channel.writeAndFlush(new LoginAcceptedMessage(usersInRoom, msg.getRoom()));


        ChatServer.channelsByRoom.get(msg.getRoom()).writeAndFlush(new JoinMessage(msg.getName(), msg.getRoom()));
        ChatServer.channelsByRoom.putIfAbsent(msg.getRoom(), new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        //TODO remove ... or automaticaly
        ChatServer.channelsByUser.remove(channel.attr(KEY_USER).get());

        //Channels removes from ChannelsGropup automaticaly,
        //TODO auto remove group if it empty //TODO more level abstraction(incapsulate all in ChatSystem)!


    }


}
