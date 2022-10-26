package com.connice.blog.controller;


import com.connice.blog.entity.Blog;
import com.connice.blog.service.BlogService;
import com.connice.common.util.Result;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-10-09
 */
@RestController
@RequestMapping("connice/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    /**
     * 新增博客
     * @param blog
     * @return
     */
    @PostMapping("/addBlog")
    public Result<String> addBlog(@RequestBody Blog blog){
        Result<String> result = new Result<>();
        blogService.addBlog(blog);
        return result;
    }

    /**
     * 修改博客
     * @param blog
     * @return
     */
    @PutMapping("/putBlog")
    public Result<String> putBlog(@RequestBody Blog blog){
        Result<String> result = new Result<>();
        blogService.putBlog(blog);
        return result;
    }

    /**
     * 分页查询所有博客
     * @param page
     * @param size
     * @return
     */
    @GetMapping("getList")
    public Result getBlogList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                              @RequestParam(value = "size",defaultValue = "10") Integer size){
        Result result = new Result<>();
        PageInfo<Blog> blogList = blogService.getBlogList(page,size);
        result.setData(blogList);
        return result;
    }

    /**
     * 根据ID查询博客详情
     * @param blogId
     * @return
     */
    @GetMapping("queryBlogById")
    public Result<Blog> queryBlogById(@RequestParam(value = "blogId",required = true) String blogId){
        Result<Blog> result = new Result<>();
        Blog blog = blogService.queryBlogById(blogId);
        result.setData(blog);
        return result;
    }

    @DeleteMapping("delBlogById")
    public Result  delBlogById(@RequestParam(value = "blogId",required = true) String blogId){
        Result<Blog> result = new Result<>();
        blogService.delBlogById(blogId);
        result.setMessage("删除成功");
        return new Result();
    }
}

