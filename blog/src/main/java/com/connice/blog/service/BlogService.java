package com.connice.blog.service;

import com.connice.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-10-09
 */
public interface BlogService extends IService<Blog> {

    /**
     * 新增博客
     * @param blog
     */
    void addBlog(Blog blog);

    /**
     * 修改博客
     * @param blog
     */
    void putBlog(Blog blog);

    /**
     * 查询所有博客
     * @return
     */
    PageInfo<Blog> getBlogList(Integer page, Integer size);

    /**
     * 根据ID查询博客
     * @param blogId
     * @return
     */
    Blog queryBlogById(String blogId);

    /**
     * 删除博客
     * @param blogId
     */
    void delBlogById(String blogId);
}
