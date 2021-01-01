package com.mljr.gps.service;

import com.lyqc.base.page.PageForm;
import com.lyqc.gpsweb.vo.GpsInstalledListVo;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.vo.GpsQueryListVo;

import java.util.List;

/**
 * @description: 车辆GPS查询列表Service
 * @Date : 2018/6/4 下午5:10
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface GpsQueryListService{
    /**
     * @description: 车贷申请管理-待上传GPS安装单
     * @Date : 2018/6/20 上午11:05
     * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
     */
    List<GpsQueryListVo> queryListForTobeUploadOnApply(PageForm<GpsQueryListForm> form);
    /**
     * 车贷申请管理-待上传GPS安装单-查询条数
     * @param form
     * @return
     */
    int queryCountForTobeUploadOnApply(PageForm<GpsQueryListForm> form);
    /**
     * @description: 车贷申请管理-GPS安装单列表
     * @Date : 2018/6/20 上午11:05
     * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
     */
    List<GpsQueryListVo> queryListForInstalledOnApply(PageForm<GpsQueryListForm> form);
    /**
     * 车贷申请管理-GPS安装单列表-查询条数
     * @param form
     * @return
     */
    int queryCountForInstalledOnApply(PageForm<GpsQueryListForm> form);

    /**
     * gps审核领单-列表查询
     * @param form
     * @return
     */
    List<GpsQueryListVo> queryListForGpsApprovePull(PageForm<GpsQueryListForm> form);

    /**
     * gps审核领单-数量查询
     * @param form
     * @return
     */
    int queryCountForGpsApprovePull(PageForm<GpsQueryListForm> form);

    /**
     * 我的GPS领单-数量查询
     * @param form
     * @return
     */
    int queryCountForGpsApproveMine(PageForm<GpsQueryListForm> form);
    /**
     * GPS综合查询
     * @param form
     * @return
     */
    List<GpsQueryListVo> queryListForGpsGeneral(PageForm<GpsQueryListForm> form);

    /**
     * GPS综合查询-数量
     * @param form
     * @return
     */
    int queryCountForGpsGeneral(PageForm<GpsQueryListForm> form);

    /**
     * 车贷审批管理-GPS安装单未上传
     * @param form
     * @return
     */
    List<GpsQueryListVo> queryListForUnInstalledGps(PageForm<GpsQueryListForm> form);

    /**
     * 车贷审批管理-GPS安装单未上传 查询数量
     * @param form
     * @return
     */
    int queryCountForUnInstalledGps(PageForm<GpsQueryListForm> form);

    /**
     * 车贷审批管理-GPS安装单已上传
     * @param form
     * @return
     */
    List<GpsQueryListVo> queryListForInstalledGps(PageForm<GpsQueryListForm> form);

    /**
     * 车贷审批管理-GPS安装单已上传 查询数量
     * @param form
     * @return
     */
    int queryCountForInstalledGps(PageForm<GpsQueryListForm> form);

    /**
     * 风云系统调用-GPS安装单列表
     * @param form
     * @return
     */
    List<GpsInstalledListVo> queryListForInstalled(PageForm<GpsQueryListForm> form);

    /**
     * 风云系统调用-GPS安装单列表数量
     * @param form
     * @return
     */
    int queryCountForInstalled(PageForm<GpsQueryListForm> form);
}
