package com.connice.common.util;

import com.connice.common.redis.cache.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 10:14 2022/11/14
 * Modified By:
 **/
@Component
public class JwtUtil {

    private final String  TOKEN ="token_";
    @Resource
    private RedisUtils redisUtils;

    /**
     * 解析token查看数据是否存在，是则延迟缓存中token过期时间
     * @param token
     */
    public static void parseJWT(String token) {

//        if
    }
}
