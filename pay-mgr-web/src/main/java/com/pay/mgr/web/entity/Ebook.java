package com.pay.mgr.web.entity;

import net.bytebuddy.asm.Advice;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

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

/**
 * 默认继承策略
 * 1：会在数据库中建立相应字段，父类和子类差异不大，且子类字段可以为空
 * 2：问题，默认继承字段必须能为NULL，否者会报错
 *
 *
 */
@Entity
public class Ebook extends Book {

    private String format;

}
