package com.connice.rebbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.connice")
public class RebbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RebbitmqApplication.class, args);
    }

}
