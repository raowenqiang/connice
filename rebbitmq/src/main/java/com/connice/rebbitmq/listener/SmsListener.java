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


    @RabbitListener(queues = "sms.queue")
    public void listenerWorkQueue(Map map, Message message, Channel channel) throws Exception {
        System.out.println(message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//       String a =  MessageHelper.msgToObj(message,String);
//        System.out.println(message.getBody().toString());
//        String[] vals = msg.split("%%%%%%");
//        Boolean flag = CommonUtils.send(vals[0],vals[1]);
//        MessageProperties properties = message.getMessageProperties();
//        long tag = properties.getDeliveryTag();
//        System.out.println(1111111);
//        System.out.println(map);
//        if (flag){
//            channel.basicAck(tag,true);
//        }else {
//            channel.basicNack(tag,false,true);
//        }
    }
}
