package com.connice.rebbitmq.listener;

import com.connice.common.util.CommonUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 13:42 2022/10/18
 * Modified By:
 **/
@Component
public class SmsListener {

    /**
     * 短信队列监控(消费者)
     * @param mqMessage
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = "sms.queue")
    public void listenerWorkQueue(String mqMessage, Message message, Channel channel) throws Exception {
        Boolean flag = true;
//        Boolean flag = CommonUtils.send(map.get("iphone").toString(),map.get("code").toString());
        System.out.println("---------------------------------------");
        System.out.println(mqMessage);
        if (flag){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }else {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }
}
