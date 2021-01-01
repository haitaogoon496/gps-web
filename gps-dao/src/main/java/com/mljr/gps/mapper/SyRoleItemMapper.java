package com.mljr.gps.mapper;

import com.mljr.gps.entity.SyRoleItemKey;

public interface SyRoleItemMapper {
    int deleteByPrimaryKey(SyRoleItemKey key);

    int insert(SyRoleItemKey record);

    int insertSelective(SyRoleItemKey record);
}