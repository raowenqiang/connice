package com.connice.common.util;

import com.connice.common.redis.cache.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 10:14 2022/11/14
 * Modified By:
 **/
@Component
public class JwtUtil {

    private final String  TOKEN ="TOKEN_";
    @Resource
    private RedisUtils redisUtils;

    /**
     * 解析token查看数据是否存在，是则延迟缓存中token过期时间
     * @param token
     */
    public  void parseJWT(String token) {

//        if
    }

    /**
     * 获取redis中的个人数据
     * @param request
     * @return
     */
    public Map getRedisToken(HttpServletRequest request){
        String token = request.getHeaders("token").nextElement();
        AssertUtils.isBlank(token,"数据");
        Map map = (Map) redisUtils.get(TOKEN+token);
        if (map==null){
            ReturnUtils.returnBlank("获取个人信息失败");
        }
        return map;

    }
}
