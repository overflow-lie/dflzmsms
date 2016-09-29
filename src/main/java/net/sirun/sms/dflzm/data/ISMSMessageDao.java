package net.sirun.sms.dflzm.data;

import net.sirun.sms.dflzm.data.model.SMSMessageEntity;

/**
 * Created by sirun on 2016/9/28.
 */
public interface ISMSMessageDao{

    /**
     * 获取自增count后的SMSMessageEntity
     * @param vin
     * @return
     */
    public SMSMessageEntity findSMSMsgAndInc(String vin);

}
