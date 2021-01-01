package com.mljr.gps.service;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.base.service.BasePagingService;
import com.mljr.gps.entity.GpsCompositeQuery;
import com.mljr.gps.form.GpsCompositeQueryForm;

import java.util.List;

/**
 * @description:  GPS综合查询Service
 * @Date : 2018/6/3 下午6:14
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsCompositeQueryService extends BasePagingService<GpsCompositeQuery,GpsCompositeQueryForm> {

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
