package com.mljr.gps.mapper;

import com.mljr.gps.entity.SyRoleButtonKey;

public interface SyRoleButtonMapper {
    int deleteByPrimaryKey(SyRoleButtonKey key);

    int insert(SyRoleButtonKey record);

    int insertSelective(SyRoleButtonKey record);
}