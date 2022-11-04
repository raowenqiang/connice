package com.connice.rebbitmq.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 14:37 2022/10/31
 * Modified By:
 **/
@Data
public class MessageLog {
    private String messageId;

    private String message;

    private Integer tryCount;
// 0 投递中 1 投递成功   2 投递失败
    private String status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;
}
