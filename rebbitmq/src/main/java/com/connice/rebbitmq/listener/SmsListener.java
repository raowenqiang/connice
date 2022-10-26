package com.connice.rebbitmq.listener;

import com.connice.common.redis.cache.RedisCache;
import com.connice.common.util.CommonUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 13:42 2022/10/18
 * Modified By:
 **/

@Component
public class SmsListener {



    @RabbitListener(queues = "sms.queue")
    public void listenerWorkQueue(String msg) throws Exception{

//        String[] vals = msg.split("%%%%%%");
//        CommonUtils.send(vals[0],vals[1]);
        System.out.println("sms接收到的消息:【"+msg+"】发送成功");
    }
}
