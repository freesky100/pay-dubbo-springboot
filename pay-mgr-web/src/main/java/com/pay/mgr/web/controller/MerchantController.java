package com.pay.mgr.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jhf.pay.common.core.Exception.BizException;
import com.jhf.pay.common.core.enums.BizCodeEnum;
import com.pay.mgr.web.dto.MerchantDto;
import com.pay.mgr.web.entity.Merchant;
import com.pay.mgr.web.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * Created by yw on 2018/5/25.
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @JsonView(MerchantDto.MerchantSimple.class)
    @GetMapping()
    public List<MerchantDto> queryList(){
        Pageable pageable = PageRequest.of(0,10);
        return merchantService.queryList(pageable);
    }

    @GetMapping("/{id}")
    @JsonView(MerchantDto.MerchantDetail.class)
    public MerchantDto queryOne(@Valid @PathVariable(value = "id",required = true) Long id){
        MerchantDto  merchantDto = merchantService.queryOne(id);
        return merchantDto;
    }

    @PutMapping("/{id}")
    public MerchantDto updateOne(@Valid @RequestBody MerchantDto merchantDto, BindingResult result){
        System.out.println("coming..");
        if(result.hasErrors()){
            result.getAllErrors().stream().forEach(error-> System.out.println(error.getObjectName()+error.getCode()+error.getArguments()+error.getDefaultMessage()));
            throw new RuntimeException("系统异常");
        }
        return merchantService.update(merchantDto);
    }


    @DeleteMapping("/{id}")
    public void delOne(@NotNull @PathVariable(value = "id") long id) {
        System.out.println("del...");
        merchantService.delete(id);
    }

    @GetMapping("/ex/{id}")
    @JsonView(MerchantDto.MerchantDetail.class)
    public MerchantDto queryOneExeption(@Valid @PathVariable(value = "id",required = true) Long id){
        MerchantDto  merchantDto = merchantService.queryOne(id);
        throw new BizException(BizCodeEnum.PAY_USER_ERROR);
//        return merchantDto;
    }
}
