package net.sirun.sms.dflzm.controller;

import com.alibaba.fastjson.JSONObject;
import hoperun.loginfo.SelfLogger;
import hoperun.util.spring.ResponseEntityUtil;
import net.sirun.sms.dflzm.bean.SMSMessageUnit;
import net.sirun.sms.dflzm.service.ISMSService;
import net.sirun.sms.dflzm.util.SMSMessageUtil;
import net.sirun.sms.dflzm.util.TimeStampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.Charset;

/**
 * Created by sirun on 2016/9/27.
 */
@Controller
public class SMSController {

    @Autowired
    private ISMSService smsServiceImpl;

    @ResponseBody
    @RequestMapping(value={"send"},method= RequestMethod.POST)
    public ResponseEntity<?> sendSMS(@RequestBody String body){
        SelfLogger.info("",body);
        JSONObject jo = JSONObject.parseObject(body);
        String vin = jo.getString("vin");
        String phone = jo.getString("phone");
        int destId = jo.getInteger("destId");
        String hmacKey = jo.getString("hmacKey");
        int sourceId = jo.getInteger("sourceId");
        SMSMessageUnit smm = new SMSMessageUnit();

        // TODO MessageType需改为根据配置
        smm.setMsgType(31);
        smm.setTimeStamp(TimeStampUtil.buildTimeStamp());
        smm.setDestId(destId);
        smm.setOtaProtocolVersion(0);
        smm.setSourceId(sourceId);

        //TODO 获取计数器
        smm.setCount(smsServiceImpl.getCount(vin));

        String sms = SMSMessageUtil.getBase64SmsData(smm, hmacKey);

        if (!smsServiceImpl.sendSMS(phone, sms) ) {
            return ResponseEntityUtil.getResponseEntity("短信发送失败", 400,
                    new MediaType("application",
                            "json", Charset.forName("UTF-8")));
        }
        JSONObject resJo = new JSONObject();
        resJo.put("result", "已发送");
        resJo.put("sms",sms);
        return ResponseEntityUtil.getResponseEntity(resJo.toJSONString(), 200,
                new MediaType("application",
                        "json", Charset.forName("UTF-8")));
    }
}
