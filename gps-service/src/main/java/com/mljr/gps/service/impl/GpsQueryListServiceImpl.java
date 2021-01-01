package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.lyqc.gpsweb.vo.GpsInstalledListVo;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.mapper.GpsQueryListMapper;
import com.mljr.gps.service.GpsQueryListService;
import com.mljr.gps.vo.GpsQueryListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 车辆GPS查询列表Service
 * @Date : 2018/6/20 上午11:05
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class GpsQueryListServiceImpl implements GpsQueryListService {

    @Autowired
    private GpsQueryListMapper gpsQueryListMapper;

    @Override
    public List<GpsQueryListVo> queryListForTobeUploadOnApply(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryListForTobeUploadOnApply(form);
    }

    @Override
    public int queryCountForTobeUploadOnApply(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForTobeUploadOnApply(form);
    }

    @Override
    public List<GpsQueryListVo> queryListForInstalledOnApply(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryListForInstalledOnApply(form);
    }

    @Override
    public int queryCountForInstalledOnApply(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForInstalledOnApply(form);
    }

    @Override
    public List<GpsQueryListVo> queryListForGpsApprovePull(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryListForGpsApprovePull(form);
    }

    @Override
    public int queryCountForGpsApprovePull(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForGpsApprovePull(form);
    }

    @Override
    public int queryCountForGpsApproveMine(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForGpsApproveMine(form);
    }

    @Override
    public List<GpsQueryListVo> queryListForGpsGeneral(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryListForGpsGeneral(form);
    }

    @Override
    public int queryCountForGpsGeneral(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForGpsGeneral(form);
    }

    @Override
    public List<GpsQueryListVo> queryListForUnInstalledGps(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryListForUnInstalledGps(form);
    }

    @Override
    public int queryCountForUnInstalledGps(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForUnInstalledGps(form);
    }

    @Override
    public List<GpsQueryListVo> queryListForInstalledGps(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryListForInstalledGps(form);
    }

    @Override
    public int queryCountForInstalledGps(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForInstalledGps(form);
    }

    @Override
    public List<GpsInstalledListVo> queryListForInstalled(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryListForInstalled(form);
    }

    @Override
    public int queryCountForInstalled(PageForm<GpsQueryListForm> form) {
        return gpsQueryListMapper.queryCountForInstalled(form);
    }

}
