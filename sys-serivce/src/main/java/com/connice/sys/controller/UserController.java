package com.connice.sys.controller;

import com.connice.common.util.Result;
import com.connice.sys.entity.User;
import com.connice.sys.service.CnUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: WenQiangRao
 * @Description:
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
                              @RequestParam(value = "user") User user){
        Result result = new Result();
        PageInfo<User> userList = cnUserService.findAllUser(page, size,user);
        result.setData(userList);
        return result;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        Result result = new Result();
         cnUserService.addUser(user);
        return result;
    }

}
