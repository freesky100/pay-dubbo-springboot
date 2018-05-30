package com.pay.mgr.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jhf.pay.common.core.entity.BaseEntity;
import com.pay.mgr.web.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

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
 * Created by yw on 2018/5/25.
 */
/**
 * @ManyToOne
 * optinal ==外键是否可以为空
 * fetch 默认直接加载
 * cascade 不设置类型
 *
 * @OneToMany
 * @orderBY
 * mapby(维护端是哪段,多的一端维护）
 * orphanRemoval 不属于删除
 * cascade    cascadeType.REMOVE (删除1，则删除所有many)
 *
 * @manyToMany 拆分成ManyToOne 和OneToMany
 *
 * 建立一个中间对象
 *
 * 建表原则
 * 尽量是用manyToOne 把关系维护在多的表里，也就是多的表里存放另外表的主键（外键关系）
 * 双向使用的时候，多的一方建立外键，在多的一方维护，在one的一端使用mapby,放弃管理
 * 如果是oneToMany会生成3方表进行维护
 *
 *
 *
 *
 *
 *
 */




@Entity
//@Table(name="t_merchant")
@Data
@NoArgsConstructor
public class Merchant extends BaseEntity {

    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(6)")
    private Sex sex;

    @Embedded
    private HomeAddress homeAddress;

    //默认建一个oneToMany记录
    //集合对象可以是embedded对象 List<Address>
    @ElementCollection(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = "hobbies")
    private List<String> hobbies;


    /**
     * 单项关联的时候，主控merchants ，会建立 t_id_card_t_id
     *双向关联的时候，主控merchants,id card也会同样引用merchant_id
     */
    @OneToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="t_id_card_fk",unique = true,updatable = false,nullable = false)
//    @JsonIgnoreProperties(value={"merchant"})
    private IdCard idCard;


    public Merchant(String name, String phone, IdCard idCard) {
        this.name = name;
        this.phone = phone;
        this.idCard = idCard;
    }
}
