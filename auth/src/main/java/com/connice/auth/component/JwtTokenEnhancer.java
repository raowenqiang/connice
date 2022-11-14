package com.connice.auth.component;

import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.connice.auth.vo.SecurityUser;
import com.connice.common.constant.Constant;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 11:25 2022/11/14
 * Modified By:
 **/
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Object ob = oAuth2Authentication.getPrincipal();
        SecurityUser securityUser = new SecurityUser();
        if (ob instanceof java.lang.String){
            securityUser.setUsername(ob.toString());
        }else{
            securityUser = (SecurityUser) oAuth2Authentication.getPrincipal();
        }

        Map<String, Object> info = Maps.newHashMap();
        info.put("id", securityUser.getId());
        info.put("userName", securityUser.getUsername());
        info.put(Constant.RETURN_SUCCESS,true);
        info.put(Constant.AUTHORIZE_TOKEN,securityUser.getLoginUserToken());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
