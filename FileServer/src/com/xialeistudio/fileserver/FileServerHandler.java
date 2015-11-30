package com.xialeistudio.fileserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/1
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String> {
    private static final String CR = System.getProperty("line.separator");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" connected.");
        ctx.writeAndFlush("Welcome to file server."+CR);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        File file = new File(msg);
        if (file.exists()){
            if (!file.isFile()){
                ctx.writeAndFlush("Not a file: "+file+CR);
                return;
            }
            ctx.write("file "+file.length()+CR);
            RandomAccessFile randomAccessFile = new RandomAccessFile(msg,"r");
            FileRegion region = new DefaultFileRegion(
                    randomAccessFile.getChannel(),0,randomAccessFile.length()
            );
            ctx.write(region);
            ctx.writeAndFlush(CR);
            randomAccessFile.close();
        }else{
            ctx.writeAndFlush("File not found: "+msg+CR);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
