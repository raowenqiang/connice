package com.connice.rebbitmq.mapper;

import com.connice.rebbitmq.entity.MessageLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
//修改mq
    void putMessage(MessageLog messageLog);
//根据id查看日志
    MessageLog getMessageById(String messageId);
//查看所有目前日志
    List<MessageLog> getAllMessage(MessageLog messageLog);

//    查询数据库中状态为发送中且已超时的数据
    List<MessageLog> queryStatusAndTimeoutMessage();
    /**
     * 重新发送统计count发送次数 +1
     * @param messageId
     * @param updateTime
     */
    void updateReSend(@Param("messageId")String messageId, @Param("updateTime")Date updateTime);
    /**
     * 更新最终消息发送结果 成功 or 失败
     * @param messageId
     * @param status
     * @param updateTime
     */
    void changeBrokerMessageLogStatus(@Param("messageId")String messageId,@Param("status")String status,@Param("updateTime")Date updateTime);
}
