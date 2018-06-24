package com.pay.mgr.web.javatest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

@RunWith(JUnit4.class)
public class TestAsynMethod {


    @Test
    public void testAsyn() throws ExecutionException, InterruptedException {
        Shop shop = new Shop("shop1");
        long t1 = System.currentTimeMillis();
        shop.getPrice();
        System.out.println("syn call:"+(System.currentTimeMillis()-t1));
        t1 = System.currentTimeMillis();
        Future<Double> future = shop.getPriceAsyn();
        System.out.println("asyn call:"+(System.currentTimeMillis()-t1));
        Double price = future.get();
        System.out.println("asyn call res:"+(System.currentTimeMillis()-t1));




    }

}

class Shop{

    private Random random = new Random();
    private Double price;
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice()  {
        try {
            delay();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextDouble()*100;
    }


    public void delay() throws InterruptedException {
        Thread.sleep(1000);
    }

    /**
     * syn to asyn
     * 比建议自已用线程构建
     * @return
     */
    public Future<Double> getPriceAsyn(){
        CompletableFuture completableFuture = new CompletableFuture();
        new Thread(() -> {
            completableFuture.complete(getPrice());
        }).start();
        return completableFuture;
    }

    /**
     *
     * 常用于当前线程调用之后还有其他工作要做的情况
     * 尽量用此方法处理，帮你处理异常及释放资源
     * syn to asyn
     * @return
     */
    public Future<Double> getPriceAsynOther(){
        return CompletableFuture.supplyAsync(()->getPrice());
    }


}
