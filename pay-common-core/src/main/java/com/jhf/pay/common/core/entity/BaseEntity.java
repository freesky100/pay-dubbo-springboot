package com.jhf.pay.common.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
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
 * Created by yw on 2018/5/25.
 */

/**
 *
 * 如果小项目可以使用base，通过重写factory实现
 * 如果大项目表比较复杂的话，可以使用embablle方式引用公共字段，否则重写factory麻烦
 */


@Data
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue()
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime = new Date();

    @Version
    private Integer version;


    //int长度
    @Column(columnDefinition = "int(1)")
    private Integer state;

}
