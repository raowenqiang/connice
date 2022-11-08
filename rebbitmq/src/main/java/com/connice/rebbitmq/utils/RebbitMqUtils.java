package com.connice.rebbitmq.utils;

import com.connice.rebbitmq.service.MessageLogService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Component
public class RebbitMqUtils {
    @Resource
    private MessageLogService messageLogService;

    /**
     * mq执行完成之后的返回操作
     * @param mqMessage
     * @param message
     * @param channel
     * @param flag
     * @throws IOException
     */
    public void channelReturn(Map mqMessage, Message message, Channel channel,Boolean flag) throws IOException {
        String messageId = mqMessage.get("messageId").toString();
        if (flag){  //成功之后
            messageLogService.changeBrokerMessageLogStatus(messageId,Constants.ORDER_SEND_SUCCESS,new Date());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } else {   //失败之后
            messageLogService.updateReSend(messageId,new Date());
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
    }
}
