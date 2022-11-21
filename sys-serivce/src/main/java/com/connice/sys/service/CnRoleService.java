package com.connice.sys.service;

import com.connice.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.connice.sys.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
     * 查询所有角色
     * @param page
     * @param size
     * @param role
     * @return
     */
    PageInfo<Role> getRoleList(Integer page, Integer size, Role role);

    /**
     * 根据Id查询角色
     * @param id
     * @return
     */
    Role getRoleById(String id);

    /**
     * 新增角色
     * @param role
     */
    void insertRole(Role role);

    /**
     * 修改角色
     * @param role
     */
    void putRole(Role role);

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(String userId);

}
