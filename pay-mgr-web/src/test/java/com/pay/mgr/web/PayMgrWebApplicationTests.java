package com.pay.mgr.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayMgrWebApplicationTests {
	@Value("${myname}")
	private String driver;
	@Test
	public void contextLoads() {
	}

	@Test
	public void getPropertis(){
		System.out.println(driver);

	}



}
