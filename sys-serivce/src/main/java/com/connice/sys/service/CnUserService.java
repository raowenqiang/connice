package com.connice.sys.service;

import com.connice.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.connice.sys.vo.UserRole;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

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
     * 登录逻辑校验（手机号跟验证码）
     * @param iphone
     * @param code
     * @return
     * @throws Exception
     */
    User loginUser(String iphone, String code) ;

    /**
     * 登录逻辑校验（用户名跟密码）
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    User loginUserByName(String userName, String password);

    /**
     * 获取当前登录人信息
     * @param request
     * @return
     */
    User getNewUser(HttpServletRequest request);

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    User getUserRole(String userId);

    /**
     * 给用户分配角色
     * @param userRole
     */
    void userDesRole(UserRole userRole);

    /**
     * 修改分配的角色
     * @param userRole
     */
    void putUserRole(UserRole userRole);

    /**
     * 删除用户与角色的关联
     * @param id
     */
    void delUserRole(String id);
}
