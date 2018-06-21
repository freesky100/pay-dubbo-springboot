package com.pay.mgr.web.repository;

import com.jhf.pay.common.core.enums.DelStateEnum;
import com.jhf.pay.common.core.utils.DateUtils;
import com.pay.mgr.web.entity.HomeAddress;
import com.pay.mgr.web.entity.IdCard;
import com.pay.mgr.web.entity.Merchant;
import com.pay.mgr.web.entity.QMerchant;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.data.jpa.domain.Specification.*;
import static com.pay.mgr.web.repository.MerchantSpecificationUtils.*;

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
@RunWith(SpringRunner.class)
@SpringBootTest
public class MerchantRepositoryTest {

    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private IdCardRepository idCardRepository;


    @Test
    public void testSave() {
        Merchant merchant = new Merchant();
        merchant.setName("张三");
        merchant.setPhone("15800000000");
        IdCard idCard = new IdCard();
        idCard.setIdNum("410012341234123412");
        idCard.setAge(22);
        idCard.setState(DelStateEnum.NOT_DEL.getState());
        idCard.setValidDate(DateUtils.strToDateLong("2020-01-01", "yyyy-MM-dd"));
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance();
        try {
            date = dateFormat.parse("2017-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        idCard.setBirthDay(date);
        merchant.setIdCard(idCard);
//        idCard.setMerchant(merchant);
//        idCardRepository.save(idCard);
        merchant.setHomeAddress(new HomeAddress("北京市", "北京市", "海淀区", "马甸东路", 100000));

        //使用级联更新的时候，不会走我们的save，所以显示制定state的值

        Merchant merchant1 = merchantRepository.save(merchant);
//        idCard.setMerchant(merchant1);
        System.out.println(merchant1);

    }

    @Test
    //注意toString方法，在存在相互关联关系的时候
    public void testFindById() {
        Optional<Merchant> optional = merchantRepository.findById(35L);
        System.out.println(optional);
        if (optional.isPresent())
            System.out.println(optional.isPresent());
        Merchant merchant = optional.get();
        System.out.println(merchant);
        System.out.println(merchant.getIdCard());
    }

    @Test
    //注意toString方法，在存在相互关联关系的时候
    public void testFindByOther() {
        Merchant merchant = merchantRepository.findByIdAndName(37L, "张三");
        System.out.println(merchant);
        System.out.println(merchant);
        System.out.println(merchant.getIdCard());
    }

    @Test
    //注意toString方法，在存在相互关联关系的时候
    public void testQueryAnnation() {
       List<Object[]> objects = merchantRepository.findByParam(37L);
        System.out.println(objects.size());
        for (int i = 0; i < objects.size(); i++) {
            Object[] object =objects.get(i);
            for (int j = 0; j < object.length; j++) {
                Object o = object[j];
                System.out.println(o);
            }
        }
    }

    @Test
    //注意toString方法，在存在相互关联关系的时候
    public void testQueryAnnation1() {
       Merchant merchant = merchantRepository.findByParamPart(37L);
        System.out.println(merchant);
        Assert.notNull(merchant,"空");
    }


    /**
     *   A、实体对象：在持久化框架中与Table对应的域对象，一个对象代表数据库表中的一条记录，
     *   如上例中Customer对象。在构建查询条件时，一个实体对象代表的是查询条件中的“数值”部分。
     *   如：要查询姓“刘”的客户，实体对象只能存储条件值“刘”。

     B、匹配器：ExampleMatcher对象，它是匹配“实体对象”的，表示了如何使用“实体对象”中的“值”进行查询，
     它代表的是“查询方式”，解释了如何去查的问题。如：要查询姓“刘”的客户，即姓名以“刘”开头的客户，
     该对象就表示了“以某某开头的”这个查询方式，
     如上例中:withMatcher("name", GenericPropertyMatchers.startsWith())

     C、实例：即Example对象，代表的是完整的查询条件。由实体对象（查询条件值）和匹配器（查询方式）共同创建。

     再来理解“实例查询”，顾名思义，就是通过一个例子来查询。要查询的是Customer对象，
     查询条件也是一个Customer对象，通过一个现有的客户对象作为例子，查询和这个例子相匹配的对象。
     *
     *
     *  1、支持动态查询。即支持查询条件个数不固定的情况，如：客户列表中有多个过滤条件，用户使用时在“地址”查询框中输入了值，就需要按地址进行过滤，如果没有输入值，就忽略这个过滤条件。对应的实现是，在构建查询条件Customer对象时，将address属性值置具体的条件值或置为null。

     2、不支持过滤条件分组。即不支持过滤条件用 or(或) 来连接，所有的过滤查件，都是简单一层的用 and(并且) 连接。

     3、仅支持字符串的开始/包含/结束/正则表达式匹配 和 其他属性类型的精确匹配。查询时，对一个要进行匹配的属性（如：姓名 name），只能传入一个过滤条件值，如以Customer为例，要查询姓“刘”的客户，“刘”这个条件值就存储在表示条件对象的Customer对象的name属性中，针对于“姓名”的过滤也只有这么一个存储过滤值的位置，没办法同时传入两个过滤值。正是由于这个限制，有些查询是没办法支持的，例如要查询某个时间段内添加的客户，对应的属性是 addTime，需要传入“开始时间”和“结束时间”两个条件值，而这种查询方式没有存两个值的位置，所以就没办法完成这样的查询。
     *
     *
     *
     * 、需要考虑的因素

     查询条件的表示，有两部分，一是条件值，二是查询方式。条件值用实体对象（如Customer对象）来存储，相对简单，当页面传入过滤条件值时，存入相对应的属性中，没入传入时，属性保持默认值。查询方式是用匹配器ExampleMatcher来表示，情况相对复杂些，需要考虑的因素有：
     （1）Null值的处理。当某个条件值为Null,是应当忽略这个过滤条件呢，还是应当去匹配数据库表中该字段值是Null的记录？
     （2）基本类型的处理。如客户Customer对象中的年龄age是int型的，当页面不传入条件值时，它默认是0，是有值的，那是否参与查询呢？
     （3）忽略某些属性值。一个实体对象，有许多个属性，是否每个属性都参与过滤？是否可以忽略某些属性？
     （4）不同的过滤方式。同样是作为String值，可能“姓名”希望精确匹配，“地址”希望模糊匹配，如何做到？
     （5）大小写匹配。字符串匹配时，有时可能希望忽略大小写，有时则不忽略，如何做到？

     public class ExampleMatcher {
     NullHandler nullHandler; //Null值处理方式
     StringMatcher defaultStringMatcher; //默认字符串匹配方式
     boolean defaultIgnoreCase; //默认大小写忽略方式
     PropertySpecifiers propertySpecifiers; //各属性特定查询方式
     Set<String> ignoredPaths; //忽略属性列表
     ......
     }

     （1）nullHandler：Null值处理方式，枚举类型，有2个可选值，INCLUDE（包括）,IGNORE（忽略）。标识作为条件的实体对象中，一个属性值（条件值）为Null是，是否参与过滤。当该选项值是INCLUDE时，表示仍参与过滤，会匹配数据库表中该字段值是Null的记录；若为IGNORE值，表示不参与过滤。

     （2）defaultStringMatcher：默认字符串匹配方式，枚举类型，有6个可选值，DEFAULT（默认，效果同EXACT）,EXACT（相等）,STARTING（开始匹配）,ENDING（结束匹配）,CONTAINING（包含，模糊匹配）,REGEX（正则表达式）。该配置对所有字符串属性过滤有效，除非该属性在 propertySpecifiers 中单独定义自己的匹配方式。
     （3）defaultIgnoreCase：默认大小写忽略方式，布尔型，当值为false时，即不忽略，大小不相等。该配置对所有字符串属性过滤有效，除非该属性在 propertySpecifiers 中单独定义自己的忽略大小写方式。
     （4）propertySpecifiers：各属性特定查询方式，描述了各个属性单独定义的查询方式，每个查询方式中包含4个元素：属性名、字符串匹配方式、大小写忽略方式、属性转换器。如果属性未单独定义查询方式，或单独查询方式中，某个元素未定义（如：字符串匹配方式），则采用 ExampleMatcher 中定义的默认值，即上面介绍的 defaultStringMatcher 和 defaultIgnoreCase 的值。
     （5）ignoredPaths：忽略属性列表，忽略的属性不参与查询过滤。


     */
    @Test
    public void testFindAllByExample(){
        Pageable page = PageRequest.of(1,2);
        Merchant merchant = new Merchant();
        merchant.setId(35l);
        merchant.setName("张三");
        //默认time赋值了，所以查不到
        Example<Merchant> example = Example.of(merchant);
        List<Merchant> merchantList = merchantRepository.findAll(example);

        System.out.println(merchantList);
        Assert.notNull(merchantList,"null");

        IdCard idCard = new IdCard();
//        idCard.setIdNum("410012341234123412");
        idCard.setIdNum("\\d");
        merchant.setIdCard(idCard);

        //Matcher
        ExampleMatcher matcher= ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)//改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true)//改变默认大小写忽略方式：忽略大小写
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())//姓名模糊查询
                .withMatcher("idNum",ExampleMatcher.GenericPropertyMatchers.startsWith())
//                .withStringMatcher(ExampleMatcher.StringMatcher.REGEX)
                .withIgnoreCase("name")//忽略name字段大小写
                .withIgnorePaths("createTime","modifyTime");//不查询creteTime和modifyTime

        example = Example.of(merchant,matcher);
        Page<Merchant> merchantPage = merchantRepository.findAll(example,page);
        System.out.println(merchantPage);
        Assert.notNull(merchantPage,"NULL");

        merchantList = merchantRepository.findAll(example,Sort.by(Sort.Direction.ASC,"id"));
        System.out.println(merchantList);
        Assert.notNull(merchantList,"NULL");

    }


    /**
     * specification主要用于动态查询，列入前台传值为空不查询，不为空就查询
     *Predicate相当于条件，builder相当于组装器
     *
     */

    @Test
    public void testSpecial(){
        Pageable page = PageRequest.of(0,2);
//        Specification<Merchant>;
//        Page<Merchant> pageList = merchantRepository.findAll(new Specification<Merchant>() {
//            @Nullable
//            @Override
//            //['predɪkət] 断言
//            public Predicate toPredicate(Root<Merchant> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//
//                /**
//                 *     select
//                 merchant0_.t_id as t_id1_4_,
//                 merchant0_.t_create_time as t_create2_4_,
//                 merchant0_.t_modify_time as t_modify3_4_,
//                 merchant0_.t_state as t_state4_4_,
//                 merchant0_.t_version as t_versio5_4_,
//                 merchant0_.t_address as t_addres6_4_,
//                 merchant0_.t_area as t_area7_4_,
//                 merchant0_.t_city as t_city8_4_,
//                 merchant0_.t_province as t_provin9_4_,
//                 merchant0_.t_zip_code as t_zip_c10_4_,
//                 merchant0_.t_id_card_fk as t_id_ca14_4_,
//                 merchant0_.t_name as t_name11_4_,
//                 merchant0_.t_phone as t_phone12_4_,
//                 merchant0_.t_sex as t_sex13_4_
//                 from
//                 t_merchant merchant0_
//                 where
//                 merchant0_.t_id=1
//                 and merchant0_.t_state=0 limit ?, ?
//                 */
////                Predicate p1 = criteriaBuilder.equal(root.get("id").as(Long.class),1);
//                Predicate p2 = criteriaBuilder.equal(root.get("state").as(Integer.class),0);
////                Predicate p4 = criteriaBuilder.like(root.get("idCard").get("idNum").as(String.class),"41%");
//                //为了复用，可以吧Predicate做成一个静态工具类
////                Predicate p1 = MerchantSpecificationUtils.idCardLike();
//
//                Predicate p3 = criteriaBuilder.and(p2);
//                System.out.println(root.getModel().getName());
//                return p3;
//            }
//        }, page);
        Page<Merchant> pageList = merchantRepository.findAll(where(idCardLike()).and(stateIsNotDel()),page);
        System.out.println(pageList.getContent());
    }


    @Test
    public void testFindAll(){
        Pageable pageable = PageRequest.of(0,10);
        Page<Merchant> page = merchantRepository.findAll(pageable);
        System.out.println(page.getContent());

    }


    /**
     * 存在问题
     */
    @Test
    public void testNameQuery(){
//        List<Merchant> list = merchantRepository.findByTheUserName("张三");
//        System.out.println(list);
//        Assert.notNull(list,"NOt null");
    }

    /**
     * 存在问题
     */
    @Test
    public void testDslFind(){
//        QMerchant qMerchant = QMerchant.merchant;
//
//        BooleanExpression t1 = qMerchant.id.eq(27l);
//        BooleanExpression t2 = qMerchant.name.like("张三%");
//        Iterable<Merchant> iterable = merchantRepository.findAll(t1.and(t2));
//        while(iterable.iterator().hasNext()){
//            Merchant merchant= iterable.iterator().next();
//            System.out.println(merchant);
//        }
    }

}