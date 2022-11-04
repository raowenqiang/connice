package com.connice.rebbitmq.service.impl;

import com.connice.common.exception.ServerException;
import com.connice.rebbitmq.entity.MessageLog;
import com.connice.rebbitmq.mapper.MessageLogMapper;
import com.connice.rebbitmq.service.MessageLogService;
import com.connice.rebbitmq.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 14:48 2022/10/31
 * Modified By:
 **/
@Slf4j
@Service
public class MessageLogServiceImpl implements MessageLogService {

    @Resource
    private MessageLogMapper messageLogMapper;


    @Override
    public MessageLog insert(MessageLog messageLog) {
//        String id = UUID.randomUUID().toString().replaceAll("-", "");
//        messageLog.setMessageId(id);
        messageLog.setStatus(Constants.ORDER_SENDING);
        messageLog.setTryCount(0);
        Date time  = new Date();
        messageLog.setNextRetry(DateUtils.addMinutes(time, Constants.ORDER_TIMEOUT));
        messageLog.setUpdateTime(time);
        messageLog.setCreateTime(time);
        messageLogMapper.insert(messageLog);
        return messageLog;
    }

    @Override
    public MessageLog putMessage(MessageLog messageLog) {
        if (StringUtils.isEmpty(messageLog.getMessageId())){
            log.info("修改日志失败，ID为空,时间：{}",new Date());
            throw new ServerException("ID为空，无法修改mq日志");
        }
        messageLog.setUpdateTime(new Date());
        messageLogMapper.putMessage(messageLog);
        return messageLog;
    }

    @Override
    public MessageLog getMessageById(String messageId) {
        if (StringUtils.isEmpty(messageId)){
            log.info("查询日志失败，ID为空,时间：{}",new Date());
            throw new ServerException("ID为空，无法查看mq日志");
        }
        MessageLog messageLog = messageLogMapper.getMessageById(messageId);
        return messageLog;
    }

    @Override
    public List<MessageLog> getAllMessage(MessageLog messageLog) {
        List<MessageLog> logs = messageLogMapper.getAllMessage(messageLog);
        return logs;
    }
}
