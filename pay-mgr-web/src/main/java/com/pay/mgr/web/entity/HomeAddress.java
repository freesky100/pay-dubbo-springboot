package com.pay.mgr.web.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.pay.mgr.web.controller.MerchantController;
import com.pay.mgr.web.dto.MerchantDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeAddress {


    @JsonView(MerchantDto.MerchantDetail.class)
    @Column(length = 30)
    private String province;
    @JsonView(MerchantDto.MerchantDetail.class)
    @Column(length = 30)
    private String city;
    @JsonView(MerchantDto.MerchantDetail.class)
    @Column(length = 50)
    private String area;
    @JsonView(MerchantDto.MerchantDetail.class)
    @Column(length = 50)
    private String address;
    @JsonView(MerchantDto.MerchantDetail.class)
    @Column(columnDefinition = "int(6)")
    private int zipCode;


}
