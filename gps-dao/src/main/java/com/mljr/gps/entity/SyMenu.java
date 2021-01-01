package com.mljr.gps.entity;

public class SyMenu {
    private Integer menuId;

    private String menuTitle;

    private Long menuIndex;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle == null ? null : menuTitle.trim();
    }

    public Long getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Long menuIndex) {
        this.menuIndex = menuIndex;
    }
}