package com.pay.mgr.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.jhf.pay.common.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
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
 * Created by yw on 2018/5/26.
 */
@Data
@Entity
//@Table(name="t_idcard") 采用全局注解
public class IdCard extends BaseEntity {

    @Column(nullable = false,length = 18)
    private String idNum;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    @Temporal(TemporalType.DATE)
    private Date validDate;
    @Column(columnDefinition = "int(3)")
    private Integer age;

//    @OneToOne(mappedBy = "idCard") mapperBy会在主控表建立外键
    //其实相当于这一边不写

    @OneToOne(mappedBy = "idCard")
//    @JoinColumn(name="t_merchant_fk")
//    @JsonIgnoreProperties({"idCard"})
    @JsonIgnore
    private Merchant merchant;


    /**
     * 如果此处打印merchant，会出现死循环调用
     * @return
     */
    @Override
    public String toString() {
        return "IdCard{" +
                "idNum='" + idNum + '\'' +
                ", birthDay=" + birthDay +
                ", validDate=" + validDate +
                ", age=" + age +
                '}';
    }
}
