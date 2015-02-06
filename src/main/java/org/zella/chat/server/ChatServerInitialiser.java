package org.zella.chat.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LoggingHandler;


/**
 * Created by dru on 02.02.2015.
 */
public class ChatServerInitialiser extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("log", new LoggingHandler());
        //  pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())));
        pipeline.addLast("encoder", new ObjectEncoder());
        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));


        pipeline.addLast("handler1", new RoomMessageServerHandler());
        pipeline.addLast("handler2", new PrivateMessageServerHandler());
        pipeline.addLast("handler3", new LoginServerHandler());


    }
}
