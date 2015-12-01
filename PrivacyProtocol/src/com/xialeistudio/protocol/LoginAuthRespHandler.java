package com.xialeistudio.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/2
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null && message.getHeader().getType() == (byte) 1) {
            System.out.println("Login is OK");
            String body = (String) message.getBoby();
            System.out.println("Received message body from client is: " + body);
        }
        ctx.writeAndFlush(buildLoginResponse((byte) 3));
    }

    private NettyMessage buildLoginResponse(byte b) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType((byte) 2);
        message.setHeader(header);
        message.setBoby(b);
        return message;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
