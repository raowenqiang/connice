package com.connice.rebbitmq.controller;

import com.connice.common.util.Result;
import com.connice.rebbitmq.service.SmsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.soap.SAAJResult;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 16:59 2022/10/31
 * Modified By:
 **/
@RestController
@RequestMapping("/connice/sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    /**
     * 短信验证码生成以及发送到mq跟redis
     * @param iphone
     * @return
     */
    @GetMapping("/sendSms")
    public Result<String> sendSms(@RequestParam(value = "iphone") String iphone){
        Result<String> result = new Result<>();
        smsService.sendSms(iphone);
        return result;
    }
}
