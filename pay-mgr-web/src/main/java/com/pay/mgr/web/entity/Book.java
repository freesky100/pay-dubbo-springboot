package com.pay.mgr.web.entity;

import com.jhf.pay.common.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
 * Created by yw on 2018/5/28.
 */

/**
 * @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 * 默认继承策略
 * 1：会在数据库中建立相应字段，父类和子类差异不大，且子类字段可以为空
 * 2：他会在表中建立相应的子类字段，查询的时候根据子类字段来区分记录
 * 3：问题，默认继承字段必须能为NULL，否者会报错
 *
 *
 * @Inheritance(strategy = InheritanceType.JOINED)
 * 1：会单独创建一张子表，主键指向父类的ID
 * 2：查询的时候回关联所有子表
 * 3：插入的时候回插入子类所有表
 *
 * @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
 * 1:数据库主键不能使用自增主键
 * 2：会为每一个库建立一个单独的标,且字段是完整的，继承父类和子类
 * 3：用父类repository查询的时候会使用union来进行查询
 * 4：单独查询则只需要查询自己的单表
 * 5：适用于父类和子类查询表较大，但是union查询比较慢，如果子类较多，不适用
 *
 *
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Data
public class Book extends BaseEntity{

    private String name;
    private Double price;
    /**
     * manyToOne
     * 默认的话会产生三方表，如
     * t_book (id,....,t_catalog_t_id)
     * t_catalog(id...)
     * t_catalog_t_book(t_book_t_id,t_catalong_t_id)
     *要想不用三方表，使用mappby,有多的一方来维护
     *
     *
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private Catalog catalog;


    /**
     *
     * 默认不指定mappby 的话会建立2张关联表，表明不一致，外键一致
     * 所以一般manyTomany其实可以转换成manytoone的中间表形式
     */
    @ManyToMany(mappedBy = "books",cascade = CascadeType.ALL)
    private List<Auth> auths;

}
