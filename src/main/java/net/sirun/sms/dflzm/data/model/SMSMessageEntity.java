package net.sirun.sms.dflzm.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sirun on 2016/9/27.
 */
@Document(collection="dflzmsms_message")
public class SMSMessageEntity implements Serializable{

    private String _id;
    private String vin;
//    private List<String> smsMessage;
    private int count;

    public int getCount() {
        return count;
    }

    public String getVin(){
        return vin;
    }

    public String get_id(){
        return _id;
    }

}
