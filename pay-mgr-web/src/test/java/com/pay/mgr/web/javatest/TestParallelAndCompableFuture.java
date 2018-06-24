package com.pay.mgr.web.javatest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
 * Created by yw on 2018/6/10.
 */
public class TestParallelAndCompableFuture {


    private List<Shop> shopList = Arrays.asList(new Shop("shop1")
    ,new Shop("shop2")
    ,new Shop("shop3")
            ,new Shop("shop4")
            ,new Shop("shop5")
            ,new Shop("shop6")
            ,new Shop("shop7")
            ,new Shop("shop8")
            //我是8核cpu
//            ,new Shop("shop9")
    );



    //演示parallel和completableFuture

    public List<String> getListString(){

        //[shop1 的价格:92.67, shop2 的价格:61.87, shop3 的价格:94.57, shop4 的价格:67.19, shop5 的价格:34.32, shop6 的价格:71.41, shop7 的价格:42.20, shop8 的价格:66.52]
        //        耗时：1190,当正好是8个shop的时候，并发时候正好1秒
        //如果是9个shop的时候，耗时：2145，8个核线程用完，必须等待释放后继续，所以parallel是跟核心cpu绑定的
//        return shopList.stream().parallel()
//                .map((shop)->String.format("%s 的价格:%.2f",shop.getName(),shop.getPrice()))
//                .collect(Collectors.toList());


       List<CompletableFuture<Double>> futureList =  shopList.stream()
               .map((shop)->CompletableFuture.supplyAsync(()->shop.getPrice()))
               .collect(Collectors.toList());
       return futureList.stream().map((d)->String.format("价格%.2f",d.join())).collect(Collectors.toList());
    }


    public static void main(String[] args) {

        int num = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU合数:"+num);
        long t1 = System.currentTimeMillis();
        System.out.println(new TestParallelAndCompableFuture().getListString());
        System.out.println("耗时："+(System.currentTimeMillis()-t1));


    }



}
