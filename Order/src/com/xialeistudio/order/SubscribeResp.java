package com.xialeistudio.order;

import java.io.Serializable;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/11/30
 */
public class SubscribeResp implements Serializable {
    public static final long serialVersionUID = 1L;

    private int subReqID;
    private int respCode;
    private String desc;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SubscribeResp [subReqId=" + getSubReqID() + ", respCode=" + getRespCode() + ", desc=" + getDesc() + "]";
    }
}
