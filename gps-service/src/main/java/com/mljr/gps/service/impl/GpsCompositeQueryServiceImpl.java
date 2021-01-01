package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.GpsCompositeQuery;
import com.mljr.gps.form.GpsCompositeQueryForm;
import com.mljr.gps.mapper.GpsCompositeQueryMapper;
import com.mljr.gps.service.GpsCompositeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: GPS综合查询Service
 * @Date : 2018/6/3 下午5:52
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class GpsCompositeQueryServiceImpl implements GpsCompositeQueryService {
    @Autowired
    private GpsCompositeQueryMapper gpsCompositeQueryMapper;
    @Override
    public List<GpsCompositeQuery> queryByPage(PageForm<GpsCompositeQueryForm> form) {
        return gpsCompositeQueryMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<GpsCompositeQueryForm> form) {
        return gpsCompositeQueryMapper.queryCount(form);
    }

    @Override
    public List<GpsCompositeQuery> queryMineApprExportByPage(PageForm<GpsCompositeQueryForm> form) {
        return gpsCompositeQueryMapper.queryMineApprExportByPage(form);
    }

    @Override
    public int queryMineApprExportCount(PageForm<GpsCompositeQueryForm> form) {
        return gpsCompositeQueryMapper.queryMineApprExportCount(form);
    }
}
