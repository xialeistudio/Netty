package com.xialeistudio.chineseproverb;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/1
 */
public class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String resp = msg.content().toString(CharsetUtil.UTF_8);
        if (resp.startsWith("谚语查询结果：")){
            System.out.println(resp);
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
