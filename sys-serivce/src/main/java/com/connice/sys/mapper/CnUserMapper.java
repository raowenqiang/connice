package com.connice.sys.mapper;

import com.connice.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}
