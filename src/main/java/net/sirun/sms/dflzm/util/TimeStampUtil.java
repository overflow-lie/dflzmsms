package net.sirun.sms.dflzm.util;

import net.sirun.sms.dflzm.bean.protobuf.Message.TimeStamp;

import java.util.Calendar;

/**
 * Created by sirun on 2016/9/28.
 */
public class TimeStampUtil {

    /**
     * 生成指定时间
     *
     * @Description:
     * @Title: buildTimeStamp
     * @param:
     * @return:
     * @throws:
     * @author: RAY
     * @Date: 2016年9月5日 下午5:57:59
     */
    public static TimeStamp buildTimeStamp(int year, int month, int day,
                                                   int hour, int minute, int second) {
        TimeStamp timeStamp = TimeStamp.newBuilder().setYear(year - 2000)
                .setMonth(month).setDay(day).setHour(hour).setMinute(minute)
                .setSecond(second).build();
        return timeStamp;
    }

    /**
     * 生成当前时间
     *
     * @Description:
     * @Title: buildTimeStamp
     * @param:
     * @return:
     * @throws:
     * @author: RAY
     * @Date: 2016年9月5日 下午5:56:34
     */
    public static TimeStamp buildTimeStamp() {
        Calendar c = Calendar.getInstance();// 获得系统当前日期
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        TimeStamp timeStamp = TimeStamp.newBuilder().setYear(year)
                .setMonth(month).setDay(day).setHour(hour).setMinute(minute)
                .setSecond(second).build();
        return timeStamp;
    }

}
