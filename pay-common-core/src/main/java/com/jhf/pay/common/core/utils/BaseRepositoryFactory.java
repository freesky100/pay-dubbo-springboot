package com.jhf.pay.common.core.utils;

import com.jhf.pay.common.core.entity.BaseEntity;
import com.jhf.pay.common.core.repostory.BaseRepository;
import com.jhf.pay.common.core.repostory.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

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


/**
 *
 * 为了区别继承BaseENtity和非继承BaseEntity
 * @param <T>
 * @param <S>
 * @param <ID>
 */
public class BaseRepositoryFactory<T extends BaseRepository<S ,ID>,S extends BaseEntity,ID extends Serializable> extends JpaRepositoryFactoryBean<T,S,ID> {

    public BaseRepositoryFactory(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager){
            @Override
            protected Object getTargetRepository(RepositoryInformation information) {

                Class<T> domain = (Class<T>) information.getDomainType();
                if(BaseEntity.class.isAssignableFrom(domain)){
                    return new BaseRepositoryImpl(domain,entityManager);
                }else{
                    return new SimpleJpaRepository(domain,entityManager);
                }
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                if(metadata.getDomainType().isAssignableFrom(BaseEntity.class)){
                    return BaseRepositoryImpl.class;
                }else if(isQueryDslExecutor(metadata.getRepositoryInterface())){
                    return QuerydslJpaRepository.class;
                }else{
                    return SimpleJpaRepository.class;
                }
            }

            private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
                return QuerydslUtils.QUERY_DSL_PRESENT && QuerydslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
            }

        };
    }
}
