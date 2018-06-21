package com.pay.mgr.web.service;

import com.pay.mgr.web.dto.MerchantDto;
import com.pay.mgr.web.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
public interface MerchantService {

    public MerchantDto queryOne(Long id);

    public List<MerchantDto> queryList(Pageable page);

    public MerchantDto save(MerchantDto merchantDto);

    public MerchantDto update(MerchantDto merchantDto);

    public void delete(Long id);


}