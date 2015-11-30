package com.xialeistudio.order;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/11/30
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(subReq(i));
        }
    }

    private SubscribeReq subReq(int i) {
        SubscribeReq req = new SubscribeReq();
        req.setSubReqID(i);
        req.setAddress("广东省广州市");
        req.setPhoneNumber("13666666666");
        req.setProductName("Netty 权威指南");
        req.setUserName("Xialei");
        return req;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeResp resp = (SubscribeResp)msg;
        System.out.println("Receive server response: ["+resp+"]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
