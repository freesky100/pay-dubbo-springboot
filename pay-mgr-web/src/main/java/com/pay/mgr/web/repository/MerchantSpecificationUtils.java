package com.pay.mgr.web.repository;

import com.pay.mgr.web.entity.Merchant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
public class MerchantSpecificationUtils {

    public static Specification<Merchant> stateIsNotDel() {
        return new Specification<Merchant>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Merchant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("state"), 0);
            }
        };
    }

    public static Specification<Merchant> idCardLike() {
        return new Specification<Merchant>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Merchant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("idCard").get("idNum"),"41%");
            }
        };

    }

}
