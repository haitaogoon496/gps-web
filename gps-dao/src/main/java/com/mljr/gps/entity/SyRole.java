package com.mljr.gps.entity;

import com.mljr.gps.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @description: 系统角色entity
 * @Date : 2018/3/12 下午4:19
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class SyRole extends BaseEntity{
    private static final long serialVersionUID = -2688408287876669906L;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色类型")
    private Integer roleType;
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    private Date updateTime;

    private Date createTime;

    private Long validFalg;

}