package com.mljr.gps.service;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.ProppserInfo;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.vo.GpsQueryListVo;

import java.util.List;

/**
 *
 * @author caixiang.sun
 * @date 2017/11/28
 */
public interface ProppserInfoService {

    /**
     * 根据申请人标识号查询申请人信息
     * @param proppserId
     * @return
     */
    ProppserInfo selectByPrimaryKey(Integer proppserId);

    /**
     * 通过id列表查询申请人信息  (where id in ids)
     * @param proppserIds
     * @return
     */
    List<ProppserInfo> listByProppserIds(List<Integer> proppserIds);

    /**
     * 根据单号或者客户姓名查找信息
     * @param form
     * @return
     */
    List<GpsQueryListVo> listByForm(GpsQueryListForm form);
}
