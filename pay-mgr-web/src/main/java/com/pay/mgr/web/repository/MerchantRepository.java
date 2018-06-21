package com.pay.mgr.web.repository;


import com.jhf.pay.common.core.repostory.BaseRepository;
import com.pay.mgr.web.entity.Merchant;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.NoRepositoryBean;

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
 * 使用自动生懂生产的时候，是类型不安全的。比如你更改了entity中字段的名字，那么操作方法中方法都需要改动
 * 所以jpa2.0中使用CreiteraQuery API保证安全，意思就是在编译期提示问题，官方列子
 *
 * LocalDate today = new LocalDate();
 CriteriaBuilder builder = em.getCriteriaBuilder();
 CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
 Root<Customer> root = query.from(Customer.class);
 Predicate hasBirthday = builder.equal(root.get(Customer_.birthday), today);
 Predicate isLongTermCustomer = builder.lessThan(root.get(Customer_.createdAt), today.minusYears(2);
 query.where(builder.and(hasBirthday, isLongTermCustomer));
 em.createQuery(query.select(root)).getResultList();
 *当然看起来相当的繁琐
 *
 * 所以出现了QueryDsl来优化查询，三方开元项目
 * LocalDate today = new LocalDate();

 QCustomer customer = QCustomer.customer;
 BooleanExpression hasBirthday = customer.birthday.eq(today);
 BooleanExpression isLongTermCustomer = customer.createdAt.lt(today.minusYears(2));
 new JPAQuery(em)
 .from(customer)
 .where(hasBirthday.and(isLongTermCustomer))
 .list(customer);

 *
 */

public interface MerchantRepository extends BaseRepository<Merchant,Long>{

    Merchant findByIdAndName(Long id,String name);

    @Query("select t.name,t.phone,t.idCard  from Merchant t  where t.id=?1")
   List<Object[]> findByParam(Long id);

    @Query("select new Merchant(name,phone,idCard) from Merchant  t where t.id=?1")
    Merchant findByParamPart(Long id);

//    List<Merchant> findByTheUserName(String userName);

}
