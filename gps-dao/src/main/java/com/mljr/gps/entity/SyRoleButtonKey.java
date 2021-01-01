package com.mljr.gps.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SyRoleButtonKey {
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    @ApiModelProperty(value = "按钮id")
    private String buttonId;
    @ApiModelProperty(value = "菜单id")
    private String itemId;
}