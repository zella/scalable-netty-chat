package org.zella.chat;

import com.hazelcast.core.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.zella.chat.hazelcast.HazelcastMessage;


/**
 * Created by dru on 02.02.2015.
 */
public class ChatServer {

    public static void main(String[] args) throws InterruptedException {
        new ChatServer(6668).run();
    }

    //TODO refactor
    static final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
    //distributed pub/sub mechanism
    static final ITopic distributedTopic = hazelcastInstance.getTopic("default");

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    static {
        distributedTopic.addMessageListener(message -> {
            HazelcastMessage msg = (HazelcastMessage) message.getMessageObject();

            for (Channel c : channels) {
                //are you sender?
                if (!c.attr(ChatServerHandler.channelId).get().equals(msg.getChannelUUID())) {
                    c.writeAndFlush("[" + c.remoteAddress() + "] " + msg.getText() + '\n');
                } else {
                    c.writeAndFlush("[you] " + msg.getText() + '\n');
                }
            }

        });
    }

    private final int port;


    public ChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitialiser());
            bootstrap.bind(port).sync().channel().closeFuture().sync();

        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
