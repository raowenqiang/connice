package com.connice.api.mq;

import com.connice.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 16:54 2022/10/31
 * Modified By:
 **/
//@Component
@FeignClient(name = "mqservice")
public interface SmsFeign {

    /**
     * 短信验证码接收
     *
     * @param iphone   手机号
     * @return
     */
    @GetMapping(value = "connice/sms/sendSms")
    Result<?> sendSms(@RequestParam(value = "iphone") String iphone);

}
