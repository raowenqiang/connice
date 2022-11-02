package com.connice.rebbitmq.controller;

import com.connice.common.util.Result;
import com.connice.rebbitmq.entity.MessageLog;
import com.connice.rebbitmq.service.MessageLogService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: WenQiangRao
 * @Description: mq日志控制器
 * @Date: Created in 14:39 2022/10/31
 * Modified By:
 **/
@RestController
@RequestMapping("/conncie/message")
public class MessageLogController {

    @Resource
    private MessageLogService messageLogService;

    /**
     * mq新增日志信息
     *
     * @return
     */
    @PostMapping("insertMessage")
    public Result<MessageLog> insertMessage(@RequestBody MessageLog messageLog) {
        Result<MessageLog> result = new Result<>();
        MessageLog message = messageLogService.insert(messageLog);
        result.setData(message);
        return result;
    }

    /**
     * 修改日志信息
     */
    @PutMapping("putMessage")
    public Result<MessageLog> putMessage(@RequestBody MessageLog messageLog) {
        Result<MessageLog> result = new Result<>();
        MessageLog message = messageLogService.putMessage(messageLog);
        result.setData(message);
        return result;
    }

    /**
     * 根据ID查询日志信息
     *
     * @param messageId
     * @return
     */
    @GetMapping("getMessageById")
    public Result<MessageLog> getMessageById(@RequestParam String messageId) {
        Result<MessageLog> result = new Result<>();
        MessageLog message = messageLogService.getMessageById(messageId);
        result.setData(message);
        return result;
    }

    /**
     * 条件查询所有Mq日志
     *
     * @param messageLog
     * @return
     */
    @GetMapping("getAllMessage")
    public Result<List<MessageLog>> getAllMessage(MessageLog messageLog) {
        Result<List<MessageLog>> result = new Result<>();
        List<MessageLog> message = messageLogService.getAllMessage(messageLog);
        result.setData(message);
        return result;
    }
}
