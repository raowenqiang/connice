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
@ApiModel(value="Picture对象", description="")
public class Picture implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "图片ID")
    private String pictureId;

    @ApiModelProperty(value = "图片地址")
    private String pictureAddress;

    @ApiModelProperty(value = "图片名字")
    private String pictureName;

    @ApiModelProperty(value = "图片描述")
    private String pictureDescription;

    @ApiModelProperty(value = "图片创建时间")
    private Date pictureTime;

    private String userId;


}
