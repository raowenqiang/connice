package com.connice.sys.service;

import com.connice.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-11-15
 */
public interface CnUserService extends IService<User> {

    /**
     * 查收系统用户
     * @param page
     * @param size
     * @return
     */
    PageInfo<User> findAllUser(Integer page, Integer size, User user);

    /**
     * 新增系统用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 修改系统用户
     * @param user
     */
    void putUser(User user);

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    User getUserById(String id) throws Exception;

    /**
     * 根据id删除信息
     * @param id
     * @throws Exception
     */
    void delUserById(String id) throws Exception;

    /**
     * 批量删除用户信息
     * @param ids
     */
    void delUserByIds(String ids) throws Exception;

    /**
     * 登录逻辑校验
     * @param iphone
     * @param code
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    User loginUser(String iphone, String code, String userName, String password) throws Exception;

}
