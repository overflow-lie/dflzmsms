package net.sirun.sms.dflzm.service;

/**
 * Created by sirun on 2016/9/27.
 */
public interface ISMSService {

    public static final String username="sirun";
    public static final String password="40359b4b561687d95df9385829d8db92";

    /**
     * <pre>
     * http://172.20.4.9/sms/mt?
     * username=sirun&
     * password=40359b4b561687d95df9385829d8db92&
     * phones=13146305556&
     * content=您的设备短信验证码为123456。为了保护您的账号安全，请不要把验证码透露给别人。
     */
    public static final String smsServiceUrl="http://172.20.4.9/sms/mt?username={username}&password={password}&phones={phones}&content={content}";

    public boolean sendSMS(String phones,String smsBody);

    public int getCount(String vin);
}
