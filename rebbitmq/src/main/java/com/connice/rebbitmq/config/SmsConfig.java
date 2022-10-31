package com.connice.rebbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: WenQiangRao
 * @Description: 短信队列
 * @Date: Created in 14:45 2022/10/28
 * Modified By:
 **/
@Configuration
public class SmsConfig {

    //    短信队列
    @Bean
    public Queue createSmsQueue() {
        return new Queue("sms.queue");
    }

    //短信交换机
    @Bean
    public DirectExchange createSmsExchange() {
        return new DirectExchange("sms.exchange");
    }

    //绑定交换机与队列
    @Bean
    public Binding createSmsQueueBing(Queue createSmsQueue, DirectExchange createSmsExchange) {
        return BindingBuilder.bind(createSmsQueue).to(createSmsExchange).with("sms.routing.key");
    }

}
