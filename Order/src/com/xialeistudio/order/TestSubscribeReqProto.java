package com.xialeistudio.order;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xialeistudio.order.protobuf.SubscribeReqProto;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/11/30
 */
public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();

        builder
                .setSubReqID(1)
                .setUserName("Xialei")
                .setProductName("Netty 权威指南");
        List<String> address = new ArrayList<>();
        address.add("NanJing YuHuaTai");
        address.add("BeiJing LiuLiChang");
        address.add("ShenZhen HongShuLin");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode: "+req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After encode: "+req2.toString());
        System.out.println("Assert equal: --> "+req2.equals(req));
    }
}
