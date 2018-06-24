package com.pay.mgr.web.javatest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

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
public class DelayTaskQuene {

    public static void main(String[] args) throws InterruptedException {

        DelayTask delayTask1 = new DelayTask(5);
        DelayTask delayTask2 = new DelayTask(10);
        DelayTask delayTask3 = new DelayTask(15);
        DelayTask1 delayTask4 = new DelayTask1(6);
        DelayTask1 delayTask5 = new DelayTask1(11);
        DelayTask1 delayTask6 = new DelayTask1(16);


        DelayQueue delayQueue = new DelayQueue();
//        delayQueue.add(delayTask1);
//        delayQueue.add(delayTask2);
//        delayQueue.add(delayTask3);
        delayQueue.add(delayTask4);
        delayQueue.add(delayTask5);
        delayQueue.add(delayTask6);
        System.out.println("sstart....");
        SimpleDateFormat simpeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(delayQueue.size()!=0){
                    Delayed delayTask = delayQueue.poll();
                    System.out.println("时间："+simpeDateFormat.format(new Date()));
                    if(delayTask!=null){
                        System.out.println(delayTask+"出兑");
                    }
            Thread.sleep(1000);

        }
    }
}
