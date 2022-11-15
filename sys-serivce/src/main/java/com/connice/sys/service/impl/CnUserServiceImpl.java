package com.connice.sys.service.impl;

import com.connice.common.constant.Constant;
import com.connice.common.util.DateUtils;
import com.connice.sys.entity.User;
import com.connice.sys.mapper.CnUserMapper;
import com.connice.sys.service.CnUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public PageInfo<User> findAllUser(Integer page, Integer size, User user) {
        PageHelper.startPage(page, size);
        List<User> blogList = cnUserMapper.findAllUser(user);
        PageInfo<User> pageInfo = new PageInfo<User>(blogList);
        return pageInfo;
    }

    @Override
    public void addUser(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setCreateTime(new Date());
        user.setUserState(Constant.state0);
        cnUserMapper.insertUser(user);
    }
}
