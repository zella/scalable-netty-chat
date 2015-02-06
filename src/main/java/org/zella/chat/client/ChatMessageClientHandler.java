package org.zella.chat.client;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zella.chat.messages.chat.IChatMessage;
import org.zella.chat.messages.chat.PrivateMessage;

/**
 * Created by dru on 02.02.2015.
 */
public class ChatMessageClientHandler extends SimpleChannelInboundHandler<IChatMessage> {

    private IChat.IChatMessageCallback callback;

    public ChatMessageClientHandler(IChat.IChatMessageCallback callback) {
        this.callback = callback;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IChatMessage msg) throws Exception {
        System.out.println("Chat client handler: " + msg.toString());
        callback.onChatMessage(msg);
    }


}
