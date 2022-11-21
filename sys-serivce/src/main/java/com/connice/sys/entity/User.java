package com.connice.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
 * @since 2022-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CnUser对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "联系电话")
    private String iphone;

    @ApiModelProperty(value = "家庭地址")
    private String address;

    @ApiModelProperty(value = "现居住地址")
    private String newAddress;

    @ApiModelProperty(value = "头像图片")
    private String headPortrait;

    @ApiModelProperty(value = "身份证")
    private String cartId;

    @ApiModelProperty(value = "身份证照片地址")
    private String cartImg;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否启用：’0‘未审核  ‘1’启用 2‘暂时限制使用’ ‘3删除’")
    private String userState;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "职位ID")
    private String positionCode;

    private List<Role> roleList;

}
