package com.connice.blog.service.impl;

import com.connice.blog.entity.Message;
import com.connice.blog.mapper.MessageMapper;
import com.connice.blog.service.MessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
