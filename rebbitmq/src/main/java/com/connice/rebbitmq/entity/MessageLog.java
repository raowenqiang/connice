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

    private String status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;
}
