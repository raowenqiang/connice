package com.connice.rebbitmq.config;

import com.connice.rebbitmq.service.MessageLogService;
import com.connice.rebbitmq.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 14:03 2022/10/27
 * Modified By:
 **/
@Configuration
@Slf4j
public class RabbitConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Resource
    private MessageLogService messageLogService;

//    保证消息一定发送到交换机和保证消息一定到队列
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
//        保证消息一定发送到交换机
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            String messageId = correlationData.getId();
            if (!ack) {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
                messageLogService.updateReSend(messageId,new Date());
// 发送失败进行重试机制
            }
        });
//        保证消息一定到队列
        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            Map msg = JSON.parseObject(new String(message.getBody()), Map.class);
            messageLogService.updateReSend(msg.get("messageId").toString(),new Date());
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            // 路由失败，进行重试机制

        });
        return rabbitTemplate;
    }

    /**
     * json序列化(生产者发送消息)
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * json序列化(消费者发送消息)
     * @return
     */
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}
