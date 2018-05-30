package com.pay.mgr.web.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;

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
//@Configuration
public class BootYmlConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer yamlProperties(){
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("application-myyml.yml"));

//        factoryBean.setResources(new ClassPathResource("application.yml"));
        placeholderConfigurer.setProperties(factoryBean.getObject());
        return placeholderConfigurer;
    }


    /**
     *
     * 第二种方式
     */
//    @Configuration
//    public class BootstrapConfig {
//
////        @Bean
////        public PropertySourcesPlaceholderConfigurer properties2() {
////            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
////            YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
////            MutablePropertySources sources = new MutablePropertySources();
//            try {
//                sources.addLast(loader.load("db", new ClassPathResource("config/appprops.yml"), null));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            configurer.setPropertySources(sources);
//            return configurer;
//        }


    /**
     * 采用spring的xml方式配置
     */
    //<context:annotation-config/>

//<bean id="yamlProperties" class="org.springframework.beans.factory.config.YamlPropertiesFactoryBean">
//    <property name="resources" value="classpath:test.yml"/>
//</bean>
//
//<context:property-placeholder properties-ref="yamlProperties"/>
}
