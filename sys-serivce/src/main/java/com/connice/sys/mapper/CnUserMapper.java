package com.connice.sys.mapper;

import com.connice.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.connice.sys.vo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-11-15
 */
@Mapper
public interface CnUserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户信息
     * @param user
     * @return
     */
    List<User> findAllUser(@Param("user") User user);

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    int insertUser(@Param("user") User user);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(@Param("user")User user);

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    User findUserById(@Param("id") String id);

    /**
     * 根据ID删除用户信息
     * @param id
     * @return
     */
    int delUserById(@Param("id")String id);

    /**
     * 根据ID列表批量删除用户信息
     * @param list
     */
    void delListUser(List list);

    /**
     * 根据用户名查询个人信息
     * @param userName
     * @return
     */
    User findUserByName(@Param("userName")String userName);

    /**
     * 通过电话号查询用户信息
     * @param iphone
     * @return
     */
    User findUserByIphone(@Param("iphone")String iphone);

    /**
     * 通过用户ID查询用户与角色信息
     * @param userId
     * @return
     */
    User findUserRole(@Param("userId")String userId);

    /**
     * 给用户分配角色
     * @param userRole
     */
    void addUserRole(@Param("userRole") UserRole userRole);

    /**
     * 修改用户分配的角色
     * @param userRole
     */
    void putUserRole(@Param("userRole") UserRole userRole);

    void delUserRole(@Param("id")String id);
}
