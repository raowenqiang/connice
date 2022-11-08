package com.connice.rebbitmq.service;

import com.connice.rebbitmq.entity.MessageLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    /**
     * 修改日志状态，
     * @param messageId
     * @param status
     * @param updateTime
     */
    void changeBrokerMessageLogStatus(@Param("messageId")String messageId, @Param("status")String status, @Param("updateTime") Date updateTime);
    //    查询数据库中状态为发送中且已超时的数据
    List<MessageLog> queryStatusAndTimeoutMessage();
    /**
     * 重新发送统计count发送次数 +1
     * @param messageId
     * @param updateTime
     */
    void updateReSend(@Param("messageId")String messageId, @Param("updateTime")Date updateTime);
}
