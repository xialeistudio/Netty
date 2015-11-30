package com.xialeistudio.time;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/11/30
 */
public class TimeClient {
    public void connect(int port,String host)throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            ChannelFuture f = bootstrap.connect(host,port).sync();
            f.channel().close().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        try{
            if (args != null && args.length>0){
                port = Integer.valueOf(args[0]);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        new TimeClient().connect(port,"127.0.0.1");
    }
}
