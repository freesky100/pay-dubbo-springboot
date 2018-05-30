package com.pay.mgr.web.javatest;

import org.hibernate.boot.jaxb.SourceType;

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
 * Created by yw on 2018/5/29.
 */
public class GenericMethod<T> {

    private T t;

    public GenericMethod(T t) {
        this.t = t;
    }

    public T getT(){
        return t;
    }

    public <K> void  getK(K k){
        System.out.println(k.getClass());
    }

//    public E setT(E key){
//        ;
//    }

    /**
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * 这个T可以出现在这个泛型方法的任意位置.
     * 泛型的数量也可以为任意多个
     * @param method
     * @param <T>
     * @return
     */
    public <T> T showKeyName(GenericMethod<T>method){
        T test = method.getT();
        return test;
    }

    /**
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * 这个T可以出现在这个泛型方法的任意位置.
     * 泛型的数量也可以为任意多个
     * @param method
     * @param <K>
     * @param <K>
     * @return
     */
    public <K> K showKeyName2(GenericMethod<T> method,K k){
            return k;
    }

    public T showKeyName1(GenericMethod<T> method){
        return method.getT();
    };


    //在泛型方法中添加上下边界限制的时候，必须在权限声明与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
//public <T> T showKeyName(Generic<T extends Number> container)，编译器会报错："Unexpected bound"
    public <T extends Number> T showKeyName3(GenericMethod<T> container){
        System.out.println("container key :" + container.getT());
        T test = container.getT();
        return test;
    }

}
