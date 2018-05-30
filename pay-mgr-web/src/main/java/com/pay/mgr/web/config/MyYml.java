package com.pay.mgr.web.config;

import lombok.Data;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import java.util.List;
import java.util.Map;

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
@Configuration
@ConfigurationProperties(prefix = "myyaml")

/**
 * Spring Framework provides two convenient classes that can be used to load YAML documents.
 * The YamlPropertiesFactoryBean loads YAML as Properties and the YamlMapFactoryBean loads YAML as a Map.
 YAML files cannot be loaded by using the
@PropertySource annotation. So, in the case that you need to load values that way,
 you need to use a properties file.
 */
//@PropertySource(value = "classpath:application-myyml.yml")
@Data
public class MyYml {

    private String simplePro;

    private int[] simpleArr;

    private List<Map<String ,String>> complexList;

    private Map<String,String> simpleMap;

    private List<String> simpleList;



}
