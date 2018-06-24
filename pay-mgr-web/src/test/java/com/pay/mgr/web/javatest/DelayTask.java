package com.pay.mgr.web.javatest;

import com.mysql.jdbc.TimeUtil;

import java.sql.Time;
import java.util.Calendar;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 * Created by yw on 2018/5/31.
 */
public class DelayTask implements Delayed {

    private int expireSecond;

    public DelayTask(int expireSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,expireSecond);
        this.expireSecond = (int) (calendar.getTimeInMillis()/1000);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Calendar calendar = Calendar.getInstance();
        return expireSecond-(calendar.getTimeInMillis()/1000);

    }

    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
        return d==0?0:((d<0)?-1:1);
    }

    @Override
    public String toString() {
        return "DelayTask{" +
                "expireSecond=" + expireSecond +
                '}';
    }
}
