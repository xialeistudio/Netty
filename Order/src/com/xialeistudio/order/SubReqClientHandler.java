package com.xialeistudio.order;

import com.xialeistudio.order.protobuf.SubscribeReqProto;
import com.xialeistudio.order.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

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

    //    private SubscribeReq subReq(int i) {
//        SubscribeReq req = new SubscribeReq();
//        req.setSubReqID(i);
//        req.setAddress("广东省广州市");
//        req.setPhoneNumber("13666666666");
//        req.setProductName("Netty 权威指南");
//        req.setUserName("Xialei");
//        return req;
//    }
    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(i);
        builder.setUserName("Xialei");
        builder.setProductName("Netty Book For ProtoBuf");
        List<String> address = new ArrayList<>();

        address.add("NanJing YuHuaTai");
        address.add("BeiJing LiuLiChang");
        address.add("ShenZhen HongShuLin");
        builder.addAllAddress(address);
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        SubscribeResp resp = (SubscribeResp) msg;
        SubscribeRespProto.SubScribeResp resp = (SubscribeRespProto.SubScribeResp)msg;
        System.out.println("Receive server response: [" + resp + "]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
