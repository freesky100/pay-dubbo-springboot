package com.jhf.pay.common.core.repostory;

import com.jhf.pay.common.core.entity.BaseEntity;
import com.jhf.pay.common.core.enums.DelStateEnum;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;

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

public class BaseRepositoryImpl<T extends BaseEntity,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {

    private  EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        Optional<T> optional = findById(id);
        System.out.println(optional);
        if(optional.isPresent()){
            T t1 = optional.get();
            System.out.println(t1);
            t1.setState(DelStateEnum.DEL.getState());
            super.save(t1);
        }else {
            throw new RuntimeException("not find the target");
        }

    }

    @Override
    @Transactional
    public void delete(T entity) {
        entity.setState(DelStateEnum.DEL.getState());
        super.save(entity);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        System.out.println("保存对象"+entity);
        entity.setState(0);
        return super.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        System.out.println("自定义的repository");
        return super.findById(id);
    }
}
