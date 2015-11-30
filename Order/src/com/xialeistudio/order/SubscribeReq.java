package com.xialeistudio.order;

import java.io.Serializable;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/11/30
 */
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;

    private int subReqID;
    private String userName;
    private String productName;
    private String phoneNumber;
    private String address;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SubscribeReq [subReqID="+getSubReqID()+", userName="+getUserName()+", productName="+getProductName()+", phoneNumber="+getPhoneNumber()+", address="+getAddress()+"]";
    }
}
