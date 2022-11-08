package com.connice.rebbitmq.listener;

import com.connice.common.util.CommonUtils;
import com.connice.rebbitmq.service.MessageLogService;
import com.connice.rebbitmq.utils.Constants;
import com.connice.rebbitmq.utils.RebbitMqUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 13:42 2022/10/18
 * Modified By:
 **/
@Slf4j
@Component
public class SmsListener {

    @Resource
    private MessageLogService messageLogService;
    /**
     * 短信队列监控(消费者)
     * @param mqMessage
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(queues = "sms.queue")
    public void listenerWorkQueue(Map mqMessage, Message message, Channel channel) throws Exception {
        Boolean flag = true;
//        Boolean flag = CommonUtils.send(mqMessage.get("iphone").toString(),mqMessage.get("code").toString());
        String messageId = mqMessage.get("messageId").toString();
        try {
            log.info("message:{}", message.getMessageProperties().getDeliveryTag());
        } catch (Exception e) {
            log.info("message:{}", message.getMessageProperties().getDeliveryTag());
        } finally {
            // 在这里执行成功或失败
            if (flag) {
                //成功消费消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                messageLogService.changeBrokerMessageLogStatus(messageId,Constants.ORDER_SEND_SUCCESS,new Date());
            } else {
                //丢弃这条消息，如果最后一个参数设置为true的话，消息将重回队列末尾，重复消费
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                messageLogService.updateReSend(messageId,new Date());
            }
        }

    }
}
