package com.mljr.gps.mapper;

import com.mljr.gps.entity.SyMenu;

public interface SyMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(SyMenu record);

    int insertSelective(SyMenu record);

    SyMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SyMenu record);

    int updateByPrimaryKey(SyMenu record);
}