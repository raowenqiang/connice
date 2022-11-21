package com.connice.sys.controller;


import com.connice.common.util.Result;
import com.connice.sys.entity.Privilege;
import com.connice.sys.entity.User;
import com.connice.sys.service.CnPositionService;
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
@RequestMapping("/sys/privilege")
public class CnPrivilegeController {

    @Resource
    private CnPositionService cnPositionService;

    /**
     * 新增权限
     * @param privilege
     * @return
     */
    @PostMapping("insert")
    public Result insertPri(@RequestBody Privilege privilege){
        Result result = new Result();
        cnPositionService.insertPri(privilege);
        return result;
    }

    /**
     *分页查询所有权限
     * @param size
     * @param page
     * @return
     */
    @GetMapping("getPriList")
    public Result getPriList(@RequestParam("size")Integer size,
                             @RequestParam("page")Integer page){
        Result result = new Result();
        PageInfo<Privilege> priList =cnPositionService.getPriList(size,page);
        result.setData(priList);
        return result;
    }
    @DeleteMapping("delPri")
    public Result dekPri(@RequestParam("id")String id){
        Result result = new Result();
       cnPositionService.delPri(id);
        return result;
    }


}

