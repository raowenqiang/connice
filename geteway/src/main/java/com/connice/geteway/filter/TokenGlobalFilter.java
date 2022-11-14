package com.connice.geteway.filter;

import com.connice.common.constant.Constant;
import com.connice.common.util.JwtUtil;
import com.connice.geteway.config.IgnoreUrlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @Author: WenQiangRao
 * @Description:  token过滤器
 * @Date: Created in 14:03 2022/11/10
 * Modified By:
 **/
@Slf4j
@Component
public class TokenGlobalFilter implements GlobalFilter, Ordered {

    private IgnoreUrlsConfig ignoreUrlsConfig;

    public  TokenGlobalFilter( IgnoreUrlsConfig ignoreUrlsConfig){
        this.ignoreUrlsConfig = ignoreUrlsConfig;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 获取请求
        final ServerHttpRequest[] request = {exchange.getRequest()};
        //2. 则获取响应
        ServerHttpResponse response = exchange.getResponse();
        //3. 如果是网关访问白名单请求则放行
        if (check(request[0].getURI().getPath())) {
            return chain.filter(exchange);
        }
        //4. 获取请求头
        HttpHeaders headers = request[0].getHeaders();
        //5. 请求头中获取令牌
        String token = headers.getFirst(Constant.AUTHORIZE_TOKEN);

        //6. 判断请求头中是否有令牌
        if (StringUtils.isEmpty(token)) {
            log.info("请求的url:{} 中无令牌，无法通行", request[0].getURI().getPath());
            //7. 响应中放入返回的状态吗, 没有权限访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //8. 返回
            return response.setComplete();
        }

        //9. 如果请求头中有令牌则解析令牌
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .cast(JwtAuthenticationToken.class)
                .flatMap(authentication -> {
                    ServerHttpRequest request1 = exchange.getRequest();
                    Jwt jwt = (Jwt) authentication.getPrincipal();
                    Map<String, Object> jwtMap = jwt.getClaims();
                    request1 = request1.mutate()
                            .header(token, jwtMap.get(token).toString())
                            .build();
                    ServerWebExchange newExchange = exchange.mutate().request(request1).build();

                    return chain.filter(newExchange);
                });
    }


    //过滤器的优先级 数字越小 优先级越高
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
    /**
     * 检查请求地址是否网关白名单
     * @param path
     * @return
     */
    private boolean check(String path) {
        boolean b = false;
        List<String> urls = ignoreUrlsConfig.getUrls();
        if (urls.size() > 0) {
            for (int i = 0; i < urls.size(); i++) {
                String url = urls.get(i);
                url = url.replace("/**", "");
                b = path.contains(url);
                if (b) {
                    break;
                }

            }
        }
        return b;
    }
}
