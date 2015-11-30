package com.xialeistudio.chineseproverb;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/1
 */
public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private static String[] DICTIONARY = {"只要功夫深，铁杵磨成针。", "一寸光阴一寸金，寸金难买寸光阴。"};

    private String nextQuote() {
        int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
        return DICTIONARY[quoteId];
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String req = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if ("谚语字典查询？".equals(req)){
            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语查询结果："+nextQuote(),CharsetUtil.UTF_8),msg.sender()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
