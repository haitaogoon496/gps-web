package com.mljr.gps.service;

import com.mljr.gps.entity.SyItem;

import java.util.List;

/**
 * @description:用户菜单操作service
 * @Date : 2018/8/17 11:15
 * @Author : lihaitao
 */
public interface SyItemService{
    /**
     * 获取角色所属的菜单
     * @param list
     * @return
     */
    List<SyItem> selectMenuItemByRoles(List<Integer> list);
}
