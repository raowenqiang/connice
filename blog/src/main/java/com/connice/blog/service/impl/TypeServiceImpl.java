package com.connice.blog.service.impl;

import com.connice.blog.entity.Type;
import com.connice.blog.mapper.TypeMapper;
import com.connice.blog.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-10-09
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}
