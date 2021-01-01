package com.mljr.gps.mapper;

import com.mljr.gps.entity.SyItem;

import java.util.List;

public interface SyItemMapper {
    /**
     * 获取角色所属的菜单
     * @param list
     * @return
     */
    List<SyItem> selectMenuItemByRoles(List<Integer> list);
}