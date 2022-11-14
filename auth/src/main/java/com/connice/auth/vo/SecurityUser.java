package com.connice.auth.vo;

import ch.qos.logback.core.status.Status;
import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 11:29 2022/11/14
 * Modified By:
 **/
@Data
public class SecurityUser implements UserDetails {

    /**
     * ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户状态
     */
    private Boolean enabled;
    /**
     * OPENID
     */
    private String openId;

    /** 用户类型 */
    private String userType ;

    private String orgCode;
    private String orgName;
    private String positionName;
    private Object details;
    private String loginUserToken;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities;

    public SecurityUser() {
    }

//    public SecurityUser(LoginUserVo user) {
//        this.setId(user.getId());
//        this.setUsername(user.getUserName());
//        this.setPassword(user.getPw());
//        boolean a  = null!=user.getStatus() && user.getStatus().equals(Status.statusEnum.ENABLED.getValue())
//                ?true:false;
//        this.setEnabled(a);
//        if (CollectionUtil.listNotEmptyNotSizeZero(user.getRoleCodes())){
//            authorities = new ArrayList<>();
//            user.getRoleCodes().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
//        }
//        this.setDetails(user);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
