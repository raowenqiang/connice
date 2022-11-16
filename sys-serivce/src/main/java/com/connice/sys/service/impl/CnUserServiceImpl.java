package com.connice.sys.service.impl;

import com.connice.common.constant.Constant;
import com.connice.common.redis.cache.RedisUtils;
import com.connice.common.util.AssertUtils;
import com.connice.common.util.DateUtils;
import com.connice.common.util.MD5Utils;
import com.connice.sys.entity.User;
import com.connice.sys.mapper.CnUserMapper;
import com.connice.sys.service.CnUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public User loginUser(String iphone, String code, String userName, String password) throws Exception {
        if (StringUtils.isEmpty(code)){   //如果验证码未传输，则使用用户名跟密码
            AssertUtils.isBlank(userName,"用户名");//判断电话号码是否为空
            AssertUtils.isBlank(password,"密码");//判断电话号码是否为空
            User user = cnUserMapper.findUserByName(userName);
            if (user != null && MD5Utils.validate(password,user.getPassword())){
                user.setPassword("");
                return user;
            }else {
                throw new Exception("用户名或者密码不正确");
            }
        }
        AssertUtils.isBlank(iphone,"电话号码");//判断电话号码是否为空
        String redisCode = redisUtils.get(iphone).toString();
        if (redisCode.equals(code)){
            User user = cnUserMapper.findUserByIphone(iphone);
            if (user!=null){
                return user;
            }else {
                throw new Exception("电话号或者验证码不正确");
            }
        }else {
            throw new Exception("电话号或者验证码不正确");
        }
    }
}
