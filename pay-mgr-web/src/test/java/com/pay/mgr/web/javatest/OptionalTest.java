package com.pay.mgr.web.javatest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

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
 * Created by yw on 2018/5/26.
 */

@RunWith(JUnit4.class)
public class OptionalTest {

    @Test
    public void OptionalTest(){
        Integer a = null;
        Integer b = new Integer(10);

        Optional<Integer>  optional1 = Optional.ofNullable(a);
        Optional<Integer>  optional2 = Optional.ofNullable(b);

        a = optional1.orElse(new Integer(0));
        System.out.println(optional1.isPresent());
        b = optional2.orElse(new Integer(0));
        System.out.println(optional2.isPresent());
        System.out.println(optional1.get()+optional2.get());


    }
}
