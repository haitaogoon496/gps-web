package com.mljr.gps.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.base.mapper.BaseMapper;
import com.mljr.gps.entity.GpsCompositeQuery;
import com.mljr.gps.form.GpsCompositeQueryForm;

import java.util.List;

/**
 * @description: GPS综合查询Mapper
 * @Date : 2018/6/3 下午5:40
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsCompositeQueryMapper extends BaseMapper<GpsCompositeQuery,String,GpsCompositeQueryForm> {

    /**
     * 分页查询
     * @param form
     * @return List<E>
     */
    List<GpsCompositeQuery> queryMineApprExportByPage(PageForm<GpsCompositeQueryForm> form);

    /**
     * 分页查询条数
     * @param form
     * @return
     */
    int queryMineApprExportCount(PageForm<GpsCompositeQueryForm> form);
}
