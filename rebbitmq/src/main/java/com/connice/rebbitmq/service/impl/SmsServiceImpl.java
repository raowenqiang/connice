package com.connice.rebbitmq.service.impl;

import com.connice.common.redis.cache.RedisUtils;
import com.connice.common.util.CommonUtils;
import com.connice.rebbitmq.service.SmsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 17:04 2022/10/31
 * Modified By:
 **/
@Service
public class SmsServiceImpl implements SmsService {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisUtils redisUtils;

    @Override
    @Transactional
    public void sendSms(String iphone) {
        String code = CommonUtils.code();
        //       2 保存到rabbitMQ中
        Map map = new HashMap();
        map.put("iphone",iphone);
        map.put("code",code);
        rabbitTemplate.convertAndSend("sms.exchange", "sms.routing.key", map);
        redisUtils.set(iphone, code);
    }
}
