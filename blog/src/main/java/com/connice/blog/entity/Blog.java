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
@ApiModel(value="Blog对象", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private String blogId;

    @ApiModelProperty(value = "博客标题")
    private String blogTitle;

    private String blogPic;

    @ApiModelProperty(value = "博客描述")
    private String blogInterption;

    @ApiModelProperty(value = "博客内容")
    private String blogText;

    @ApiModelProperty(value = "新增时间")
    private Date addTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "查看人数")
    private Integer blogView;

    @ApiModelProperty(value = "外键，用户ID")
    private String userId;

    @ApiModelProperty(value = "分类ID")
    private String typeId;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "备注")
    private String bz;

    @ApiModelProperty(value = "赞赏")
    private String appreciation;

    @ApiModelProperty(value = "来源  0:转载 1:原创")
    private String blogOrigin;

    @ApiModelProperty(value = "推荐")
    private String blogFisrt;

    @ApiModelProperty(value = "是否是草稿")
    private String blogCg;

    @ApiModelProperty(value = "原创")
    private String blogYc;

    @ApiModelProperty(value = "分类数据")
    private Type type;

}
