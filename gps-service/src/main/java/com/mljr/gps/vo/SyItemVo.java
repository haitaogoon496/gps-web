package com.mljr.gps.vo;

import com.mljr.gps.entity.SyButton;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SyItemVo {
    private String itemId;
    @ApiModelProperty(value = "二级菜单名称")
    private String itemTitle;

    private String itemLocation;

    private String menuId;

    private String wndWidth;

    private String wndHeight;

    private String itemIcon;

    private String itemDesc;

    private String systemId;

    private String status;

    @ApiModelProperty(value = "一级菜单名称")
    private String menuTitle;

    @ApiModelProperty(value = "每个菜单对应的按钮")
    private List<SyButton> syButtons;

}