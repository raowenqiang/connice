package com.connice.blog.mapper;

import com.connice.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-10-09
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    List<Blog> queryBlogList();

    void insertBlog(Blog blog);

    void putBlog(Blog blog);

    Blog queryBlogById(String blogId);

    void delBlogById(String blogId);
}
