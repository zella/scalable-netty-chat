package org.zella.chat.client;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.zella.chat.messages.chat.IChatMessage;
import org.zella.chat.messages.presence.IPresenceMessage;

/**
 * Created by dru on 02.02.2015.
 */
public class PresenceMessageClientHandler extends SimpleChannelInboundHandler<IPresenceMessage> {

    private IChat.IChatMessageCallback callback;

    public PresenceMessageClientHandler(IChat.IChatMessageCallback callback) {
        this.callback = callback;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IPresenceMessage msg) throws Exception {
        System.out.println("Presence client handler: " + msg.toString());
        callback.onPresenceMessage(msg);
    }


}
