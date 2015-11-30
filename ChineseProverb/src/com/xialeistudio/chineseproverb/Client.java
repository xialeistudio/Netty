package com.xialeistudio.chineseproverb;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/1
 */
public class Client {
    public void run(int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST,true)
                    .handler(new ClientHandler());
            Channel ch = bootstrap.bind(0).sync().channel();
            ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语字典查询？", CharsetUtil.UTF_8),new InetSocketAddress("255.255.255.255",port))).sync();
            if (!ch.closeFuture().await(15000)){
                System.out.println("查询超时");
            }
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Client().run(8080);
    }
}
