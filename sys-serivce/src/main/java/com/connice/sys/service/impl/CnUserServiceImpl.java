package com.connice.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.connice.common.constant.Constant;
import com.connice.common.redis.cache.RedisUtils;
import com.connice.common.util.*;
import com.connice.sys.entity.User;
import com.connice.sys.mapper.CnUserMapper;
import com.connice.sys.service.CnUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.connice.sys.vo.UserRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;
import sun.security.rsa.RSASignature;
import sun.security.util.Password;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  系统用户服务实现类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-11-15
 */
@Service
public class CnUserServiceImpl extends ServiceImpl<CnUserMapper, User> implements CnUserService {

    @Resource
    private CnUserMapper cnUserMapper;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private JwtUtil jwtUtil;

    @Override
    public PageInfo<User> findAllUser(Integer page, Integer size, User user) {
        PageHelper.startPage(page, size);
        List<User> blogList = cnUserMapper.findAllUser(user);
        PageInfo<User> pageInfo = new PageInfo<User>(blogList);
        return pageInfo;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        AssertUtils.isBlank(user.getPassword(),"密码");//判断密码是否为空
        AssertUtils.isBlank(user.getIphone(),"电话号码");//判断密码是否为空
        User userIphone = cnUserMapper.findUserByIphone(user.getIphone());
        if (userIphone!=null){
            ReturnUtils.returnBlank("电话号已经存在，无法再次注册");
        }
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setCreateTime(new Date());
        user.setUserState(Constant.state0);
        user.setPassword(MD5Utils.encode2hex(user.getPassword()));
        cnUserMapper.insertUser(user);
    }

    @Override
    public void putUser(User user) {
        AssertUtils.isBlank(user.getId(),"ID");//判断ID是否为空
        user.setUpdateTime(new Date());
        cnUserMapper.updateUser(user);
    }

    @Override
    public User getUserById(String id) throws Exception {
        AssertUtils.isBlank(id,"ID");//判断ID是否为空
        User user = cnUserMapper.findUserById(id);
        if (null == user){
            throw new Exception("根据此ID，未查询到个人信息");
        }
        return user;
    }

    @Override
    public void delUserById(String id) throws Exception {
        AssertUtils.isBlank(id,"ID");//判断ID是否为空
        int flag = cnUserMapper.delUserById(id);
        if (flag!=1){
            throw new Exception("根据此ID，未查询到个人信息");
        }
    }

    @Override
    public void delUserByIds(String ids) throws Exception {
        AssertUtils.isBlank(ids,"ID列表");//判断ID是否为空
        List list = null;
        try {
            list = Arrays.asList(ids.split(","));
        }catch (Exception e){
            throw new Exception("获取的ID列表有误，无法执行删除操作");
        }
        cnUserMapper.delListUser(list);
    }

    @Override
    public User loginUser(String iphone, String code)  {
        AssertUtils.isBlank(code,"验证码");//判断电话号码是否为空
        AssertUtils.isBlank(iphone,"电话号码");//判断电话号码是否为空
        String redisCode = redisUtils.get(iphone).toString();
        if (redisCode.equals(code)){
            ReturnUtils.returnBlank("验证码不正确");
            return null;
        }
        User user = cnUserMapper.findUserByIphone(iphone);
        if (user!=null){
            return user;
        }else {
            ReturnUtils.returnBlank("此电话号为认证用户信息");
        }
        return null;
    }

    @Override
    public User loginUserByName(String userName, String password) {
        AssertUtils.isBlank(userName,"用户名");//判断用户名是否为空
        AssertUtils.isBlank(password,"密码");//判断密码是否为空
        User user = cnUserMapper.findUserByName(userName);
        if (user ==null){
            ReturnUtils.returnBlank("用户名错误");
        }
        AssertUtils.isBlank(user.getPassword(),"密码");//判断密码是否为空
        Boolean flag = MD5Utils.validate(password,user.getPassword());
        if (!flag){
            ReturnUtils.returnBlank("密码错误");
        }
        return user;
    }

    @Override
    public User getNewUser(HttpServletRequest request) {
        Map map = jwtUtil.getRedisToken(request);
        User user = JSON.parseObject(JSON.toJSONString(map), User.class);
        return user;
    }

    @Override
    public User getUserRole(String userId) {
        AssertUtils.isBlank(userId,"用户ID");
        User user = cnUserMapper.findUserRole(userId);
        return user;
    }

    @Override
    public void userDesRole(UserRole userRole) {
        AssertUtils.isBlank(userRole.getUserId(),"用户ID");
        AssertUtils.isBlank(userRole.getRoleId(),"角色ID");
        userRole.setId(UUID.randomUUID().toString().replace("-",""));
        cnUserMapper.addUserRole(userRole);
    }

    @Override
    public void putUserRole(UserRole userRole) {
        AssertUtils.isBlank(userRole.getId(),"ID");
        AssertUtils.isBlank(userRole.getRoleId(),"角色ID");
        cnUserMapper.putUserRole(userRole);
    }

    @Override
    public void delUserRole(String id) {
        AssertUtils.isBlank(id,"ID");
        cnUserMapper.delUserRole(id);
    }
}
