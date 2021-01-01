package com.mljr.gps.mapper;

import com.mljr.gps.entity.AppAnnex;
import com.mljr.gps.entity.AppInfo;

import java.util.List;

/**
 * @description: 申请单附件Mapper
 * @Date : 2018/6/15 下午7:35
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface AppAnnexMapper {
    /**
     * 批量新增
     * @param list
     * @return
     */
    int batchInsert(List<AppAnnex> list);
    /**
     * 新增
     * @param record
     * @return
     */
    int insert(AppAnnex record);

    AppAnnex selectByPrimaryKey(Integer id);
}