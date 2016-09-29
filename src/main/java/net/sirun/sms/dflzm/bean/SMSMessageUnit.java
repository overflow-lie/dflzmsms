package net.sirun.sms.dflzm.bean;

import net.sirun.sms.dflzm.bean.protobuf.Message;
import net.sirun.sms.dflzm.util.SMSMessageUtil;

/**
 * Created by sirun on 2016/9/27.
 */
public class SMSMessageUnit {

    private String msgSignature = "【斯润】";
    private String headCodeStr= "FFFF";
    private int otaProtocolVersion;
    private int msgType ;
    private int destId;
    private int sourceId;
    private Message.TimeStamp timeStamp;

    private int length= 37;

    private int count;

    public String getMsgSignature() {
        return msgSignature;
    }

    public void setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
    }

    public String getHeadCode() {
        return headCodeStr;
    }

    public void setHeadCode(String headCodeStr) {
        this.headCodeStr = headCodeStr;
    }

    public void setHeadCode(int headCode){
        this.headCodeStr = SMSMessageUtil.int2HexStr(headCode, 4);
    }

    public void setTimeStamp(Message.TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public SMSMessageUnit(){}

    public SMSMessageUnit(int otaProtocolVersion,int msgType, int destId, int sourceId,int count){
        this.otaProtocolVersion = otaProtocolVersion;
        this.msgType = msgType;
        this.destId = destId;
        this.sourceId = sourceId;
        this.count = count;
    }

    public SMSMessageUnit(int otaProtocolVersion, int msgType, int destId, int sourceId, Message.TimeStamp timeStamp,int count) {
        this.otaProtocolVersion = otaProtocolVersion;
        this.msgType = msgType;
        this.destId = destId;
        this.sourceId = sourceId;
        this.timeStamp = timeStamp;
        this.count = count;
    }

    public int getOtaProtocolVersion() {
        return otaProtocolVersion;
    }

    public void setOtaProtocolVersion(int otaProtocolVersion) {
        this.otaProtocolVersion = otaProtocolVersion;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getDestId() {
        return destId;
    }

    public void setDestId(int destId) {
        this.destId = destId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public Message.TimeStamp getTemplate() {
        return timeStamp;
    }

    public void setTemplate(Message.TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String baseSmsDataUnit(){
        StringBuilder sb = new StringBuilder();
        sb.append(SMSMessageUtil.int2HexStr(this.length,2))
                .append(SMSMessageUtil.int2HexStr(this.otaProtocolVersion, 2))
                .append(SMSMessageUtil.int2HexStr(this.msgType, 2))
                .append(SMSMessageUtil.int2HexStr(this.destId, 2))
                .append(SMSMessageUtil.int2HexStr(this.sourceId, 2))
                .append(SMSMessageUtil.int2HexStr(this.timeStamp.getYear()+"".length()>=4?Integer.parseInt((this.timeStamp.getYear()+"").substring(2, 4)):this.timeStamp.getYear(), 2))
                .append(SMSMessageUtil.int2HexStr(this.timeStamp.getMonth(), 2))
                .append(SMSMessageUtil.int2HexStr(this.timeStamp.getDay(), 2))
                .append(SMSMessageUtil.int2HexStr(this.timeStamp.getHour(), 2))
                .append(SMSMessageUtil.int2HexStr(this.timeStamp.getMinute(), 2))
                .append(SMSMessageUtil.int2HexStr(this.timeStamp.getSecond(), 2));

        return sb.toString().toUpperCase();
    }

    public String getBaseSmsDataUnitWithCount(){
        return baseSmsDataUnit() + SMSMessageUtil.int2HexStr(this.count, 8);
    }
}
