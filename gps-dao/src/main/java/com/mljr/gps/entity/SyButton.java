package com.mljr.gps.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SyButton implements Serializable {

    @ApiModelProperty(value = "主键id")
    private Integer buttonId;
    @ApiModelProperty(value = "按钮名称")
    private String buttonTitle;
    @ApiModelProperty(value = "按钮存放位置")
    private String buttonLocation;
    @ApiModelProperty(value = "顺序")
    private Long buttonIndex;
    @ApiModelProperty(value = "菜单id")
    private String itemId;
}