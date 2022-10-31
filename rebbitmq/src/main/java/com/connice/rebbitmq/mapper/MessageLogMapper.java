package com.connice.rebbitmq.mapper;

import com.connice.rebbitmq.entity.MessageLog;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 14:56 2022/10/31
 * Modified By:
 **/
@Mapper
public interface MessageLogMapper {

//    新增mq日志
     void insert(MessageLog messageLog);

    void putMessage(MessageLog messageLog);

    MessageLog getMessageById(String messageId);

    List<MessageLog> getAllMessage(MessageLog messageLog);
}
