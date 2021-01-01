package com.mljr.gps.mapper;

import com.mljr.gps.entity.SyButton;

import java.util.List;

public interface SyButtonMapper {
    int deleteByPrimaryKey(Integer buttonId);

    int insert(SyButton record);

    int insertSelective(SyButton record);

    SyButton selectByPrimaryKey(Integer buttonId);

    int updateByPrimaryKeySelective(SyButton record);

    int updateByPrimaryKey(SyButton record);

    List<SyButton> selectButtonsByRoles(List<Integer> list);
}