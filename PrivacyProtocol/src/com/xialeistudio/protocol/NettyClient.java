package com.xialeistudio.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/2
 */
public class NettyClient {
    public void connect(String host, int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChildChannelHandler());

            ChannelFuture f = bootstrap.connect(host, port).sync();
            System.out.println("Netty time Client connected at port: " + port);
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4, -8, 0));
            ch.pipeline().addLast(new NettyMessageEncoder());
            ch.pipeline().addLast(new LoginAuthReqHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyClient().connect("127.0.0.1", 8080);
    }
}
