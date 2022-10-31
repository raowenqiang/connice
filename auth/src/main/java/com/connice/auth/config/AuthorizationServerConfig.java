package com.connice.auth.config;


/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 9:41 2022/10/12
 * Modified By:
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 配置客户端详情,规定客户端权限
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()  //在内存中保存客户端权限设置
                //设置客户端id
                .withClient("client-id")
                //当前客户端加密规则:设置一个秘钥
                .secret(passwordEncoder.encode("123456"))
                //赋予客户端权限(只是一个名称)
                .scopes("all")
                //  允许客户端登录的类型
                //  password表示支持用户名密码登录
                //  refresh_token表示支持令牌刷新
                .authorizedGrantTypes("password", "refresh_token");
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    // 配置授权服务器的登录参数(配置用什么方式获取授权)
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer
                                  endpoints) throws Exception {
        //endpoints就是端点的意思,这里指客户端登录访问的Rest接口
        //  配置SpringSecurity中的认证管理器
        endpoints.authenticationManager(authenticationManager)
                //配置登录获得用户详情的方法
                .userDetailsService(userDetailsService)
                // 设置登录的方式只能是post保证安全
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                // 配置令牌生成器(如何生成令牌)
                .tokenServices(tokenService());
    }

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;

    // 配置生成令牌的方法
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        //下面开始设置生成令牌的参数
        DefaultTokenServices services = new DefaultTokenServices();
        //支持令牌刷新
        services.setSupportRefreshToken(true);
        //设置令牌生成对象
        services.setTokenStore(tokenStore);
        // 设置令牌有效期(3600是秒 指1小时有效)
        services.setAccessTokenValiditySeconds(3600);
        // 设置令牌刷新最大时间
        services.setRefreshTokenValiditySeconds(3600);
        // 配置客户端详情
        services.setClientDetailsService(clientDetailsService);
        // 千万别忘了返回services
        return services;
    }

    /**
     * 使用非对称加密算法对token签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    /**
     * 从classpath下的密钥库中获取密钥对(公钥+私钥)
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("conncie.jks"), "rao970624".toCharArray());
        KeyPair keyPair = factory.getKeyPair(
                "conncie", "rao970624".toCharArray());
        return keyPair;
    }

    // 认证成功后,当前客户端能够访问Oauth2的端点设置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 当前Oauth2允许任何人访问/oauth/token端点
                .tokenKeyAccess("permitAll()")
                // 当前Oauth2允许任何人访问/oauth/check_token端点
                .checkTokenAccess("permitAll()")
                // 允许通过验证的客户端获得令牌
                .allowFormAuthenticationForClients();
    }


}
