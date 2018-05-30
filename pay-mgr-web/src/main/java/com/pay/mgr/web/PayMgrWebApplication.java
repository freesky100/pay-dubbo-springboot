package com.pay.mgr.web;

import com.jhf.pay.common.core.repostory.BaseRepositoryImpl;
import com.jhf.pay.common.core.utils.BaseRepositoryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactory.class,repositoryBaseClass = BaseRepositoryImpl.class)
//@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class,basePackages = "com.pay.mgr.web.repository",
//excludeFilters = {@ComponentScan.Filter(type= FilterType.REGEX,pattern = (".IdStrageryRepository"))})
public class PayMgrWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayMgrWebApplication.class, args);
	}
}
