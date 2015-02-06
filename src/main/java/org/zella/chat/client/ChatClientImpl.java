package org.zella.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.zella.chat.messages.LogoutMessage;
import org.zella.chat.messages.chat.IChatMessage;
import org.zella.chat.messages.login.ILoginResponseMessage;
import org.zella.chat.messages.login.LoginRequestMessage;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

/**
 * Created by dru on 06.02.2015.
 */
public class ChatClientImpl {

    private boolean isConnected;
    private IChat.IChatMessageCallback callback;

    private Channel channel;

    public ChatClientImpl(IChat.IChatMessageCallback callback) {
        this.callback = callback;
    }


    public boolean isConnected() {
        return isConnected;
    }

    //TODO room, name mandatory - builder pattern ctor
    //TODO SOLID pattern. and exception. how mix futures?
    public CompletableFuture<ILoginResponseMessage> connectAndLogin(String host, int port, String room, String userName) {
        final CompletableFuture<ILoginResponseMessage> loginFuture = new CompletableFuture<>();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitialiser(callback));
            ChannelFuture future = bootstrap.connect(host, port);
            future.addListener(new ChannelFutureListener() {
                                   @Override
                                   public void operationComplete(ChannelFuture future) throws Exception {
                                       // check to see if we succeeded
                                       if (future.isSuccess()) {
                                           isConnected = true;
                                           channel = future.channel();
                                           //connect ok? send login message
                                           channel.pipeline().addLast("loginHandler", new SimpleChannelInboundHandler<ILoginResponseMessage>() {

                                               @Override
                                               protected void channelRead0(ChannelHandlerContext ctx, ILoginResponseMessage msg) throws Exception {
                                                   // remove handler after receiving response
                                                   ctx.pipeline().remove(this);
                                                   loginFuture.complete(msg);
                                               }
                                           });
                                           channel.writeAndFlush(new LoginRequestMessage(userName, room));


                                       }
                                   }
                               }
            );

            return loginFuture;
        } finally {
            System.out.println("client EventLoopGroup shutdown");
        //    group.shutdownGracefully();
        }

    }

    public void sendMessage(IChatMessage message) {
        channel.writeAndFlush(message);
    }

//    //example request/response arch
//    private CompletableFuture<ILoginResponseMessage> login(LoginRequestMessage message) {
//        final CompletableFuture<ILoginResponseMessage> future = new CompletableFuture<>();
//        channel.pipeline().addLast("yourHandlerName", new SimpleChannelInboundHandler<ILoginResponseMessage>() {
//
//            @Override
//            protected void channelRead0(ChannelHandlerContext ctx, ILoginResponseMessage msg) throws Exception {
//                // remove handler after receiving response
//                ctx.pipeline().remove(this);
//                future.complete(msg);
//            }
//        });
//        channel.writeAndFlush(message);
//        return future;
//    }


    public void disconnect() {
        channel.disconnect();
    }
}
