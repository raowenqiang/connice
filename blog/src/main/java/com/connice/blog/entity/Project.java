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
@ApiModel(value = "Project对象", description = "")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目图片")
    private String projectPicture;

    @ApiModelProperty(value = "项目创建时间")
    private Date createTime;

    @ApiModelProperty(value = "项目地址")
    private String projectAddress;

    @ApiModelProperty(value = "项目描述")
    private String projectDescripation;

    private String userId;


}
