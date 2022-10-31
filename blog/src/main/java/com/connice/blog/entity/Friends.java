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
@ApiModel(value = "Friends对象", description = "")
public class Friends implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "朋友ID")
    private String friendId;

    @ApiModelProperty(value = "友链地址")
    private String blogAddress;

    @ApiModelProperty(value = "友链图片")
    private String blogPicture;

    @ApiModelProperty(value = "博客名")
    private String blogName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
