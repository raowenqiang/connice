package com.connice.sys.controller;

import com.connice.common.util.Result;
import com.connice.sys.entity.User;
import com.connice.sys.service.CnUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: WenQiangRao
 * @Description:    用户信息
 * @Date: Created in 9:11 2022/11/15
 * Modified By:
 **/
@RestController
@RequestMapping("sys/user")
public class UserController {

    @Resource
    private CnUserService cnUserService;



    /**
     * 获取到所有用户信息（分页）
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getAllUser")
    public Result findAllUser(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                               User user){
        Result result = new Result();
        PageInfo<User> userList = cnUserService.findAllUser(page, size,user);
        result.setData(userList);
        return result;
    }

    /**
     * 注册/新增用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        Result result = new Result();
         cnUserService.saveUser(user);
        return result;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping("putUser")
    public Result putUser(@RequestBody User user){
        Result result = new Result();
        cnUserService.putUser(user);
        return result;
    }

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("getUserById")
    public Result getUserById(@RequestParam("id")String id) throws Exception {
        Result result = new Result();
        User user = cnUserService.getUserById(id);
        result.setData(user);
        return result;
    }

    /**
     * 根据ID删除用户信息
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("delUserById")
    public Result delUserById(@RequestParam("id")String id) throws Exception {
        Result result = new Result();
        cnUserService.delUserById(id);
        return result;
    }

    /**
     * 批量删除用户数据
     * @param ids   多个id字符串(以逗号分割)
     * @return
     * @throws Exception
     */
    @DeleteMapping("delUserById")
    public Result delUserByIds(@RequestParam("id")String ids) throws Exception {
        Result result = new Result();
        cnUserService.delUserByIds(ids);
        return result;
    }


    /**
     * 登录操作
     * @param iphone
     * @param code
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("login")
    public Result loginUser(@RequestParam("iphone") String iphone,
                            @RequestParam("code") String code,
                            @RequestParam("userName") String userName,
                            @RequestParam("password")String password) throws Exception {
        Result result = new Result();
        User user = cnUserService.loginUser(iphone,code,userName,password);
        result.setData(user);
        return result;
    }
}
