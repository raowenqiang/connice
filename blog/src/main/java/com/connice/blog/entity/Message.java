package com.connice.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author RaoWwenQiang
 * @since 2022-10-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Message对象", description = "")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消息ID")
    private String messageId;

    @ApiModelProperty(value = "联系人姓名")
    private String name;

    @ApiModelProperty(value = "电话")
    private String iphone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "消息内容")
    private String messageText;

    @ApiModelProperty(value = "消息图片")
    private String messagePictrue;

    @ApiModelProperty(value = "邮箱")
    private String email;

    private String userId;


}
