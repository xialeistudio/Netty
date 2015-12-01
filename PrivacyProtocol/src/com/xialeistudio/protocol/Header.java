package com.xialeistudio.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xialeistudio<1065890063@qq.com>
 * @date: 2015/12/1
 */
public class Header {
    private int crcCode = 0xabef0101;
    private int length;//消息长度
    private long sessionID;//会话长度
    private byte priority;//消息优先级

    private Map<String, Object> attachment = new HashMap<String, Object>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header [crcCode=" + crcCode + ", length=" + length + ", sessionID=" + sessionID + ", priority=" + priority + ", attachment=" + attachment + "]";
    }
}
