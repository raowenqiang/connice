package com.connice.blog.mapper;

import com.connice.blog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-10-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
