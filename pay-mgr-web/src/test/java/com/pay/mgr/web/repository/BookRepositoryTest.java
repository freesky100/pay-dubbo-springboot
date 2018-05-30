package com.pay.mgr.web.repository;

import com.pay.mgr.web.entity.Auth;
import com.pay.mgr.web.entity.Book;
import com.pay.mgr.web.entity.Catalog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
 * Created by yw on 2018/5/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EBookRepository eBookRepository;

    @Test
    public void TestSave(){
        Book book = new Book();
        book.setName("莽荒纪");
        book.setPrice(28.01);
        Catalog catalog = new Catalog();
        catalog.setName("玄幻小说");
        catalog.setNums(5);
        book.setCatalog(catalog);
        Auth auth = new Auth();
        auth.setName("我吃西红柿");
        auth.setAge(30);
        List<Auth> authList =new ArrayList<>();
        authList.add(auth);
        book.setAuths(authList);
        Book book1 = bookRepository.save(book);

        System.out.println(book1);

        book1 =  bookRepository.findById(1l).get();
        System.out.println(book1);
    }

    @Test
    public void TestFindById(){
        Optional<Book> book = bookRepository.findById(1l);
        Book bookEntity = null;
        if(book.isPresent()){
            bookEntity= book.get();
        }
        System.out.println(bookEntity);
    }


    @Test
    public void findAll(){
        bookRepository.findAll();
        eBookRepository.findAll();
    }


}