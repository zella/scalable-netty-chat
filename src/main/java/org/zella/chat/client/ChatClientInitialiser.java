package org.zella.chat.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


/**
 * Created by dru on 02.02.2015.
 */
public class ChatClientInitialiser extends ChannelInitializer<SocketChannel> {

    private IChat.IChatMessageCallback callback;

    public ChatClientInitialiser(IChat.IChatMessageCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast("decoder", new StringDecoder());
//        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("encoder", new ObjectEncoder()); //TODO try with swap enc with dec
        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));

        pipeline.addLast("handler1", new ChatMessageClientHandler(callback));
        pipeline.addLast("handler2", new PresenceMessageClientHandler(callback));
        // pipeline.addLast("handler3", new LoginClientHandler(callback));

    }
}
