package com.connice.geteway.filter;

import com.connice.common.redis.cache.RedisUtils;
import com.connice.common.util.DateUtils;
import com.connice.common.util.IpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 11:35 2022/11/9
 * Modified By:
 **/

@Component
public class UserGlobalFilter implements GlobalFilter, Ordered {
    private final Logger logger = LoggerFactory.getLogger(UserGlobalFilter.class);
    private String[] sqlinjectionHttpUrls = new String[0];

    @Resource
    private RedisUtils redisUtils;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //写业务逻辑
        ServerHttpRequest request = exchange.getRequest();
        String userIp = IpUtils.getIpAddr(request);
        URI uri = exchange.getRequest().getURI();
        HttpMethod method = request.getMethod();
        String contentType = request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        logger.info("ip为:{},调用了接口:{},请求方式为：{},时间节点是:{}",userIp,uri,method, DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
//        调用redis中缓存的黑名单，看是否是，

        // 如果成功继续往下执行
        return chain.filter(exchange);
    }

    //过滤器的优先级 数字越小 优先级越高
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
