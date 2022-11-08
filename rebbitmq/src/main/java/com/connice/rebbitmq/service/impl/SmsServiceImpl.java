package com.connice.rebbitmq.service.impl;

import com.connice.common.redis.cache.RedisUtils;
import com.connice.common.util.CommonUtils;
import com.connice.rebbitmq.entity.MessageLog;
import com.connice.rebbitmq.service.MessageLogService;
import com.connice.rebbitmq.service.SmsService;
import com.connice.rebbitmq.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private MessageLogService messageLogService;


    @Override
    @Transactional
    public void sendSms(String iphone) {
        String code = CommonUtils.code();
        //       2 保存到rabbitMQ中
        Map map = new HashMap();
        map.put("iphone",iphone);
        map.put("code",code);
        MessageLog log = new MessageLog();
        log.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        map.put("messageId",log.getMessageId());
        String json;
        try {
            json = objectMapper.writeValueAsString(map);
            log.setMessage(json);
        } catch (Exception e) {
            throw new IllegalArgumentException("json 序列化 异常");
        }
        messageLogService.insert(log);
        rabbitTemplate.convertAndSend("sms.exchange", "sms.routing.key", map,new CorrelationData(log.getMessageId()));
        redisUtils.set(iphone, code);
    }
}
