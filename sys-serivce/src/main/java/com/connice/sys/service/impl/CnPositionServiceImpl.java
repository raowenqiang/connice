package com.connice.sys.service.impl;

import com.connice.common.constant.Constant;
import com.connice.common.util.AssertUtils;
import com.connice.sys.entity.Position;
import com.connice.sys.entity.Privilege;
import com.connice.sys.mapper.CnPositionMapper;
import com.connice.sys.mapper.CnPrivilegeMapper;
import com.connice.sys.service.CnPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-11-15
 */
@Service
public class CnPositionServiceImpl implements CnPositionService {

    @Resource
    private CnPrivilegeMapper cnPrivilegeMapper;

    @Override
    public void insertPri(Privilege privilege) {
        AssertUtils.isBlank(privilege.getPrivilegeName(),"ID");
        privilege.setCreateTime(new Date());
        privilege.setId(UUID.randomUUID().toString().replace("-",""));
        privilege.setIsDel(Constant.state0);
        cnPrivilegeMapper.insert(privilege);
    }

    @Override
    public PageInfo<Privilege>  getPriList(Integer size, Integer page) {
        PageHelper.startPage(page,size);
        List<Privilege> list = cnPrivilegeMapper.findPriList();
        PageInfo<Privilege> pageInfo = new PageInfo<Privilege>(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public void delPri(String id) {
        AssertUtils.isBlank(id,"ID");
        cnPrivilegeMapper.deleteById(id);
        cnPrivilegeMapper.delRolePri(id);
    }
}
