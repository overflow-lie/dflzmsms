package net.sirun.sms.dflzm.service.impl;

import hoperun.adapter.comm.util.HttpSendUtil;
import hoperun.loginfo.SelfLogger;
import net.sirun.sms.dflzm.data.ISMSMessageDao;
import net.sirun.sms.dflzm.data.model.SMSMessageEntity;
import net.sirun.sms.dflzm.service.ISMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * Created by sirun on 2016/9/27.
 */
@Service
public class SMSServiceImpl implements ISMSService{

    @Autowired
    private ISMSMessageDao smsMessageDaoImpl;

    /**
     * @descript 发送短信
     * @param phones
     * @param smsBody
     * @return
     */
    public boolean sendSMS(String phones,String smsBody) {
        String url = "";
        try {
            url = smsServiceUrl.replace("{phones}", phones);

            url = url.replace("{content}", URLEncoder.encode(smsBody, "UTF-8"));
            url = url.replace("{username}", username);
            url = url.replace("{password}", password);
            SelfLogger.info("",String.format("url:%s\nphones: %s,body: %s", url, phones, smsBody));
            HttpSendUtil.httpSend(url);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取当前vin的计数器
     * @param vin
     */
    public int getCount(String vin){
        SMSMessageEntity sme = smsMessageDaoImpl.findSMSMsgAndInc(vin);
        if(sme == null){
            return 0;
        }
        return sme.getCount();
    }


}
