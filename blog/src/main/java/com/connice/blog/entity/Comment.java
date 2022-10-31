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
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    private String commentId;

    @ApiModelProperty(value = "评论人姓名")
    private String nickName;

    @ApiModelProperty(value = "评论内容")
    private String commentText;

    @ApiModelProperty(value = "评论的图片地址")
    private String commentPrcture;

    @ApiModelProperty(value = "关联的博客ID")
    private String blogId;

    @ApiModelProperty(value = "评论的父ID")
    private String parentId;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String bz;

    private String userId;


}
