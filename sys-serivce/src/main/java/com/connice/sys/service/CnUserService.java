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
    void addUser(User user);
}
