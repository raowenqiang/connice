package com.connice.sys.service.impl;

import com.connice.common.constant.Constant;
import com.connice.common.util.AssertUtils;
import com.connice.sys.entity.Role;
import com.connice.sys.entity.User;
import com.connice.sys.mapper.CnRoleMapper;
import com.connice.sys.service.CnRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-11-15
 */
@Service
public class CnRoleServiceImpl  implements CnRoleService {

    @Resource
    private CnRoleMapper cnRoleMapper;

    @Override
    public PageInfo<Role> getRoleList(Integer page, Integer size, Role role) {
        PageHelper.startPage(page,size);
        List<Role> list = cnRoleMapper.selectList(role);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Role getRoleById(String id) {
        AssertUtils.isBlank(id,"ID");//判断ID是否为空
        Role role = cnRoleMapper.selectById(id);
        return role;
    }

    @Override
    public void insertRole(Role role) {
        AssertUtils.isBlank(role.getRoleName(),"权限名");
        role.setId(UUID.randomUUID().toString().replace("-",""));
        role.setCreateTime(new Date());
        role.setIsDel(Constant.state0);
        cnRoleMapper.insert(role);
    }

    @Override
    public void putRole(Role role) {
        AssertUtils.isBlank(role.getId(),"ID");
        role.setUpdateTime(new Date());
        cnRoleMapper.updateById(role);
    }

    @Override
    public List<Role> getRoleByUserId(String userId) {
        List<Role> list = cnRoleMapper.selectListByUserId(userId);
        return list;
    }
}
