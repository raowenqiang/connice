package com.connice.sys.service;

import com.connice.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.connice.sys.entity.User;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-11-15
 */
public interface CnRoleService  {

    /**
     * 查询所有权限
     * @param page
     * @param size
     * @param role
     * @return
     */
    PageInfo<Role> getRoleList(Integer page, Integer size, Role role);

    /**
     * 根据Id查询权限
     * @param id
     * @return
     */
    Role getRoleById(String id);

    /**
     * 新增权限
     * @param role
     */
    void insertRole(Role role);

    void putRole(Role role);
}
