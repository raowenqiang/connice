package com.connice.sys.controller;


import com.connice.common.util.Result;
import com.connice.sys.entity.Role;
import com.connice.sys.entity.User;
import com.connice.sys.service.CnRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-11-15
 */
@RestController
@RequestMapping("/sys/role")
public class CnRoleController {

    @Resource
    private CnRoleService cnRoleService;

    /**
     * 查询所有角色
     * @param page
     * @param size
     * @param role
     * @return
     */
    @GetMapping("/getAllRole")
    public Result getRoleList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                              Role role){
        Result result = new Result();
        PageInfo<Role> userList = cnRoleService.getRoleList(page, size,role);
        result.setData(userList);
        return result;
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @GetMapping("/getRoleById")
    public Result getRoleById(@RequestParam(value = "id") String id){
        Result result = new Result();
        Role role  = cnRoleService.getRoleById(id);
        result.setData(role);
        return result;
    }

    /**
     * 新增用户角色
     * @param role
     * @return
     */
    @PostMapping("insertRole")
    public Result insertRole(@RequestBody Role role){
        Result result = new Result();
        cnRoleService.insertRole(role);
        return result;
    }

    /**
     * 修改用户角色
     * @param role
     * @return
     */
    @PutMapping("putRole")
    public Result putRole(@RequestBody Role role){
        Result result = new Result();
        cnRoleService.putRole(role);
        return result;
    }

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    @GetMapping("getRoleByUserId")
    public Result getRoleByUserId(@RequestParam(value = "userId") String userId){
        Result result = new Result();
        List<Role> role  = cnRoleService.getRoleByUserId(userId);
        result.setData(role);
        return result;
    }

    

}

