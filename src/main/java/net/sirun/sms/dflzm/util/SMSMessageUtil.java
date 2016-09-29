package net.sirun.sms.dflzm.util;

import hoperun.adapter.comm.util.Base64Util;
import hoperun.adapter.comm.util.HmacSignature;
import hoperun.loginfo.SelfLogger;
import net.sirun.sms.dflzm.bean.SMSMessageUnit;

import java.security.SignatureException;

/**
 * Created by sirun on 2016/9/7.
 */
public class SMSMessageUtil {

    public static String getBase64SmsData(SMSMessageUnit smm, String macKey){
    	String hmacStr = null;
    	String result = null;
		try {
			String smsDataUnitWithCount = smm.getBaseSmsDataUnitWithCount();
			SelfLogger.debug("", smsDataUnitWithCount);
			hmacStr = HmacSignature.calculateRFC2104HMAC(smsDataUnitWithCount, macKey);
//			Base64Util base64 = Base64Util.getBase64Util4LQ();
//			result = base64.encodeToString(hmacStr.getBytes());
			String smsData = smsDataUnitWithCount+hmacStr;
			SelfLogger.debug("", smsData);
			result = smm.getHeadCode()+Base64Util.encode(smsData.getBytes());
		} catch (SignatureException e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
     *
     * @param key
     * @param len key的位数,自动补零
     * @return
     */
    public static String int2HexStr(int key, Integer len){
        String khStr = Integer.toHexString(key);
        if(len == null)
            return khStr;
        while(khStr.length()<len){
            khStr = "0" + khStr;
        }
        return khStr.trim().toUpperCase();
    }

}
