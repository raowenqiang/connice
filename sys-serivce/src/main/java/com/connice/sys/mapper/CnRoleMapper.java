package com.connice.sys.mapper;

import com.connice.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface CnRoleMapper extends BaseMapper<Role> {

    /**
     * 查询所有权限
     * @param role
     * @return
     */
    List<Role> selectList(Role role);
}
