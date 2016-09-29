package net.sirun.sms.dflzm.data.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import hoperun.loginfo.SelfLogger;
import net.sirun.sms.dflzm.mongo.MongoDbFactory;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import net.sirun.sms.dflzm.data.ISMSMessageDao;
import net.sirun.sms.dflzm.data.model.SMSMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.regex.Pattern;

/**
 * Created by sirun on 2016/9/28.
 */
@Repository
public class SMSMessageDaoImpl implements ISMSMessageDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //Query var1, Update var2, FindAndModifyOptions var3, Class<T> var4, String var5
    @Override
    public SMSMessageEntity findSMSMsgAndInc(String vin) {
        Query query = new Query(Criteria.where("vin").is(vin));
        Update update = new Update().inc("count",1);
        SMSMessageEntity smsMessageEntity = mongoTemplate.findAndModify(query, update,
                new FindAndModifyOptions()
                        .returnNew(true)
                        .upsert(true), SMSMessageEntity.class);
        SelfLogger.info("",JSONObject.toJSONString(smsMessageEntity));
        return smsMessageEntity;
    }

}
