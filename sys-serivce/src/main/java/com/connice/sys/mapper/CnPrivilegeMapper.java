package com.connice.sys.mapper;

import com.connice.sys.entity.Privilege;
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
public interface CnPrivilegeMapper extends BaseMapper<Privilege> {

    /**
     * 查询所有的权限
     * @return
     */
    List<Privilege> findPriList();


    void delRolePri(@Param("id") String id);
}
