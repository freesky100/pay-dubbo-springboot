package com.pay.mgr.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.annotation.Generated;
import javax.persistence.*;

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

@Entity
@NoArgsConstructor
@Data
public class IdStragery {
    /**
     * hibernate的sequence主键
     */
    @Id
//    @GeneratedValue(generator = "sequenceGenerator")
//    @GenericGenerator(name="sequenceGenerator",strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @Parameter(name= SequenceStyleGenerator.SEQUENCE_PARAM,value="ID_SEQUENCE"),
//                    @Parameter(name=SequenceStyleGenerator.INITIAL_PARAM,value = "0"),
//                    @Parameter(name=SequenceStyleGenerator.INCREMENT_PARAM,value="1"),
//                    @Parameter(name=SequenceStyleGenerator.OPT_PARAM,value="pooled")
//            })

    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "com.jhf.pay.common.core.utils.UUID")
    private String id;

    private String name;

}
