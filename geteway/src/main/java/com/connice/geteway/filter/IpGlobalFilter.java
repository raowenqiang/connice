package com.connice.geteway.filter;

import com.connice.common.constant.Constant;
import com.connice.common.redis.cache.RedisUtils;
import com.connice.common.util.AssertUtils;
import com.connice.common.util.DateUtils;
import com.connice.common.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * @Author: WenQiangRao
 * @Description:    IP黑名单过滤器
 * @Date: Created in 11:35 2022/11/9
 * Modified By:
 **/

@Component
public class IpGlobalFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(IpGlobalFilter.class);

    @Resource
    private RedisUtils redisUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //写业务逻辑
        ServerHttpRequest request = exchange.getRequest();
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();
        String userIp = IpUtils.getIpAddr(request);
        URI uri = exchange.getRequest().getURI();
        HttpMethod method = request.getMethod();
        logger.info("ip为:{},调用了接口:{},请求方式为：{},时间节点是:{}",userIp,uri,method, DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
//        调用redis中缓存的网关黑名单，
        List<String> list = (List<String>) redisUtils.get(Constant.conncie_blacklist);
        if (AssertUtils.isExistList(list,userIp)){
            //7. 响应中放入返回的状态吗, 没有权限访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 如果成功继续往下执行
        return chain.filter(exchange);
    }

    //过滤器的优先级 数字越小 优先级越高
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
