package com.connice.sys.service;

import com.connice.sys.entity.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import com.connice.sys.entity.Privilege;
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
public interface CnPositionService{

    /**
     * 新增权限
     * @param privilege
     */
    void insertPri(Privilege privilege);

    /**
     * 查询所有权限
     * @param size
     * @param page
     * @return
     */
    PageInfo<Privilege> getPriList(Integer size, Integer page);

    /**
     * 删除权限
     * @param id
     */
    void delPri(String id);
}
