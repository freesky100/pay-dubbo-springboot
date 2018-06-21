package com.pay.mgr.web.service.impl;

import com.pay.mgr.web.dto.MerchantDto;
import com.pay.mgr.web.entity.IdCard;
import com.pay.mgr.web.entity.Merchant;
import com.pay.mgr.web.repository.MerchantRepository;
import com.pay.mgr.web.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

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
@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Override
    public MerchantDto queryOne(Long id) {
        Merchant merchant = merchantRepository.findById(id).orElse(null);
        MerchantDto merchantDto = new MerchantDto();
        if(null!=merchant){
            BeanUtils.copyProperties(merchant,merchantDto);
        }
        System.out.println(merchant+"--"+merchantDto);
        return merchantDto;
    }

    @Override
    public List<MerchantDto> queryList(Pageable page) {
        List<MerchantDto> list  = new ArrayList<>(page.getPageSize());
       Page<Merchant> merchantPage = merchantRepository.findAll(page);
       if(merchantPage!=null){
           List<Merchant> merchantList = merchantPage.getContent();
           merchantList.stream().forEach((merchant)->{
               MerchantDto  merchantDto = new MerchantDto();
               BeanUtils.copyProperties(merchant,merchantDto);
               list.add(merchantDto);
           });
       }
        System.out.println(merchantPage);
        return list;
    }

    @Override
    public MerchantDto save(MerchantDto merchantDto) {
        return null;
    }

    @Override
    public MerchantDto update(MerchantDto merchantDto) {
        Merchant merchant;

        merchant = merchantRepository.findById(merchantDto.getId()).orElse(new Merchant());
        System.out.println("merchant copy before"+merchant);
        if(merchantDto!=null){
            //Null会被覆盖掉
//            try {
//                BeanUtils.copyProperties(merchantDto,merchant,notNull(merchant));
                BeanUtils.copyProperties(merchantDto,merchant,getNullPropertyNames(merchantDto));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("merchant copy:"+merchant);
        merchantRepository.save(merchant);
        return merchantDto;
    }

    @Override
    public void delete(Long id) {
        merchantRepository.deleteById(id);
    }


    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        System.out.println(result);
        return emptyNames.toArray(result);
    }




    public String[] notNull(Object target) throws IllegalAccessException {
        if(target==null){
            return null;
        }
        Field[] fields = target.getClass().getDeclaredFields();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            String fieldName = field.getName();
            Object val = field.get(target);
            System.out.println(fieldName+"=="+val);
            if(val!=null){
                System.out.println("add--"+fieldName);
                list.add(fieldName);
            }
        }

        String[] strings = new String[list.size()];

        for (int i = 0; i < strings.length; i++) {
            String val = list.get(i);
            strings[i] =val;
            System.out.println("---"+strings[i] );
        }
        return strings;
    }

    public static void main(String[] args) {
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setId(1l);
        merchantDto.setIdCard(new IdCard());
        try {
            new MerchantServiceImpl().notNull(merchantDto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
