package com.connice.blog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.connice.blog.entity.Blog;
import com.connice.blog.mapper.BlogMapper;
import com.connice.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.connice.common.exception.ErrorCode;
import com.connice.common.exception.ServerException;


import com.connice.common.redis.cache.RedisCache;
import com.connice.common.util.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-10-09
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Override
    @Transactional
    public void addBlog(Blog blog) {
        blog.setAddTime(new Date());
        blog.setBlogView(0);
        blog.setCommentCount(0);
        blog.setBlogId(IdWorker.getIdStr());
        blogMapper.insertBlog(blog);
    }

    @Override
    @Transactional
    @CacheEvict(value = "blogList",key = "blog.blogId")
    public void putBlog(Blog blog) {
        if (StringUtils.isEmpty(blog.getBlogId())){
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        blogMapper.putBlog(blog);
    }

@Resource
private RedisCache redisCache;
    @Override
    public PageInfo<Blog> getBlogList(Integer page, Integer size) {
        String code = CommonUtils.code();
        //       2 保存到rabbitMQ中
//        rabbitTemplate.convertAndSend("sms.queue", "1111-----------"+code);
        redisCache.set("1111",code);
        PageHelper.startPage(page,size);
        List<Blog> blogList = blogMapper.queryBlogList();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "blogList",key = "#blogId")
    public Blog queryBlogById(String blogId) {
        if (StringUtils.isEmpty(blogId)){
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        Blog blog = blogMapper.queryBlogById(blogId);
        return blog;
    }

    @Override
    @Transactional
    @CacheEvict(value = "blogList",key = "#blogId")
    public void delBlogById(String blogId) {
        if (StringUtils.isEmpty(blogId)){
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        blogMapper.delBlogById(blogId);
    }
}
