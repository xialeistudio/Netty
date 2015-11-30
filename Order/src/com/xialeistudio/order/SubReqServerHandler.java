package com.xialeistudio.order;

import com.xialeistudio.order.protobuf.SubscribeReqProto;
import com.xialeistudio.order.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/11/30
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        SubscribeReq req = (SubscribeReq) msg;
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
        if ("Xialei".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscribe req :[" + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

//    private SubscribeResp resp(int subReqID) {
//        SubscribeResp resp = new SubscribeResp();
//        resp.setSubReqID(subReqID);
//        resp.setRespCode(0);
//        resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
//        return resp;
//    }
    private SubscribeRespProto.SubScribeResp resp(int subReqID){
       SubscribeRespProto.SubScribeResp.Builder builder = SubscribeRespProto.SubScribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
