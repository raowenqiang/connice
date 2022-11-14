package com.connice.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 14:16 2022/11/14
 * Modified By:
 **/
@Component
public class UserServiceImpl implements UserDetailsService {


//    @Autowired
//    private AuthUserFeign authUserFeign;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String USERNAME ="username";
    private static final String USERTYPE ="userType";
    private static final String CLIENTID ="clientId";
    private static final String LOGINPLTM ="loginPltm";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
