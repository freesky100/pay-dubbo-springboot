package com.jhf.pay.common.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import java.io.Serializable;

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
 * Created by yw on 2018/5/25.
 */
@Data
public class Reponse<T> implements Serializable {

    private static final long serialVersionUID = -4577255781088498763L;
    private static final int OK = 0;
    private static final int FAIL = 1;
    private static final int UNAUTHORIZED = 2;

    private T data; //服务端数据
    private int code = OK; //状态码
    private String msg = ""; //描述信息

    @Data
    @AllArgsConstructor
     static class User{

        private long id;
        private String name;
     }

    public static void main(String[] args) {
       Reponse<User> reponse = new Reponse<User>();
       reponse.setData(new User(1L,"张三"));
        System.out.println(reponse);
    }

}
