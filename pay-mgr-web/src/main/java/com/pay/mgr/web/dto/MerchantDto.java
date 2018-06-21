package com.pay.mgr.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.pay.mgr.web.entity.HomeAddress;
import com.pay.mgr.web.entity.IdCard;
import com.pay.mgr.web.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
 * Created by yw on 2018/5/30.
 */




@Data
@NoArgsConstructor
public class MerchantDto  {

    /**
     * Jsonview需要自定义返回字段，可以使用接口方式实现
     * 注意时间字段，需要gmt+8时区，默认json使用的fastjson
     * 对于对象属性，如果使用jsonview一定也要加上类名，否者返回会为空
     */
    public interface MerchantSimple{};
    public interface MerchantDetail extends MerchantSimple{};
    @JsonView(MerchantSimple.class)
    private Long id;

    @JsonView(MerchantSimple.class)
    @Pattern(regexp = "\\*{2,4}")
    private String name;
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @JsonView(MerchantSimple.class)
    private String phone;
    @JsonView(MerchantSimple.class)
    @NotNull
    private Sex sex;
    @JsonView(MerchantDetail.class)
    private HomeAddress homeAddress;
    @JsonView(MerchantDetail.class)
    //类上引用的记得在对应的类上也得写注释
    private List<String> hobbies;
    @JsonView(MerchantDetail.class)
    private IdCard idCard;


}
