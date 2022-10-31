package com.connice.rebbitmq.service;

import com.connice.rebbitmq.entity.MessageLog;

import java.util.List;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 14:47 2022/10/31
 * Modified By:
 **/
public interface MessageLogService {
//    新增mq日志信息
    MessageLog insert(MessageLog messageLog);
//修改MQ日志
    MessageLog putMessage(MessageLog messageLog);
//根据ID查询日志信息
    MessageLog getMessageById(String messageId);

//    条件查询所有MQ日志
    List<MessageLog> getAllMessage(MessageLog messageLog);
}
