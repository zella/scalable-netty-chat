package org.zella.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.zella.chat.messages.chat.RoomMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;


/**
 * Created by dru on 02.02.2015.
 */
public class ChatClient {


    public static void main(String[] args) throws IOException, InterruptedException {
        //   new ChatClient("localhost", 6668).run();

        CompletableFuture<String> future = compute();
        for (int i = 0; i < 40; i++) {
            System.out.println(i);
            Thread.sleep(100);
        }
        future.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(s);
            }
        }).join();
        System.out.println("40");

    }

    private final String host;
    private final int port;

    private Channel channel;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class);
                  //  .handler(new ChatClientInitialiser());
//            ChannelFuture future = bootstrap.connectAndLogin(host, port);
//            future.addListener(new ChannelFutureListener() {
//                                   @Override
//                                   public void operationComplete(ChannelFuture future) throws Exception {
//                                       // check to see if we succeeded
//                                       if (future.isSuccess()) {
//                                           channel = future.channel();
//                                           channel.writeAndFlush(new LoginRequestMessage("dru", "big mamas"));
//                                           // remember, the write is asynchronous too !
//                                       }
//                                   }
//                               }
//            );

            //or sync
            channel = bootstrap.connect(host, port).sync().channel();
         //   channel.writeAndFlush(new LoginRequestMessage("dru2", "big mamas")).sync();


            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter something:");
            while (true)

            {
                // System.in.read();
                String text = in.readLine();
                channel.writeAndFlush(new RoomMessage("dru", "big mamas", text));

            }

        } finally {
            group.shutdownGracefully();
        }
    }

    static CompletableFuture<String> compute() {
        final CompletableFuture<String> future = new CompletableFuture<>();

        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (1 == 1)
                    throw new NullPointerException();
                //  return "result";
                return "result";
            }
        });
        //start async coding and
        //in asucn future.complete(done")


    }


}
