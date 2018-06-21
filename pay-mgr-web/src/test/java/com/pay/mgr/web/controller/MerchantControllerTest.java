package com.pay.mgr.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.*;

import static org.junit.Assert.*;

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
@SpringBootTest
@RunWith(SpringRunner.class)
public class MerchantControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        //
//        mockMvc = MockMvcBuilders.standaloneSetup(new MerchantControllerTest()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void queryList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/merchant")
        .contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("27"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void queryOneSuccess() throws Exception {

        String resJson ="{\"id\":27,\"name\":\"张三\",\"phone\":\"15800000000\",\"sex\":null,\"homeAddress\":{\"province\":\"北京市\",\"city\":\"北京市\",\"area\":\"海淀区\",\"address\":\"马甸东路\",\"zipCode\":100000},\"hobbies\":[],\"idCard\":{}}";
       String res =  mockMvc.perform(MockMvcRequestBuilders.get("/merchant/27").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("27"))
                .andExpect(MockMvcResultMatchers.content().json(resJson))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }

    @Test
    public void queryOneFail() throws Exception {

        String resJson ="{\"id\":27,\"name\":\"张三\",\"phone\":\"15800000000\",\"sex\":null,\"homeAddress\":{\"province\":\"北京市\",\"city\":\"北京市\",\"area\":\"海淀区\",\"address\":\"马甸东路\",\"zipCode\":100000},\"hobbies\":[],\"idCard\":{}}";
        mockMvc.perform(MockMvcRequestBuilders.get("/merchant/28").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("27"))
                .andExpect(MockMvcResultMatchers.content().json(resJson))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateOneSuccess() throws Exception {
       String res =  mockMvc.perform(MockMvcRequestBuilders.put("/merchant/27").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{\"id\":27,\"name\":\"李四\",\"phone\":\"\"}")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(27))
//                .andDo(MockMvcResultHandlers.print());
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }


    @Test
//    @Transactional
    public void delOneSuccess() throws Exception {
        String res =  mockMvc.perform(MockMvcRequestBuilders.delete("/merchant/37")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(27))
//                .andDo(MockMvcResultHandlers.print());
                .andReturn().getResponse().getContentAsString();
        System.out.println(res);
    }
}