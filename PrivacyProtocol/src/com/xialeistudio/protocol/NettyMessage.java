package com.xialeistudio.protocol;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/1
 */
public class NettyMessage {
    private Header header;//消息头
    private Object boby;//消息体

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBoby() {
        return boby;
    }

    public void setBoby(Object boby) {
        this.boby = boby;
    }

    @Override
    public String toString() {
        return "NettyMessage [header=" + header + "]";
    }
}
