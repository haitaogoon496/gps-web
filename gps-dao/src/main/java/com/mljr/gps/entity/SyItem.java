package com.mljr.gps.entity;


import com.mljr.gps.base.entity.BaseEntity;
import lombok.Data;

@Data
public class SyItem extends BaseEntity{
    private Integer itemId;

    private String itemTitle;

    private String itemLocation;

    private String menuId;

    private Long wndWidth;

    private Long wndHeight;

    private String itemIcon;

    private String itemDesc;

    private Integer systemId;

    private Byte status;

    private String menuTitle;
}