package com.mljr.gps.service.impl;

import com.mljr.gps.entity.ProppserInfo;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.mapper.ProppserInfoMapper;
import com.mljr.gps.service.ProppserInfoService;
import com.mljr.gps.vo.GpsQueryListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请人信息实现类
 * @author caixiang.sun
 * @date 2017/11/28
 */
@Service
public class ProppserInfoServiceImpl implements ProppserInfoService{

    @Autowired
    private ProppserInfoMapper proppserInfoMapper;

    /**
     * 根据申请人标识号查询申请人信息
     * @param proppserId
     * @return
     */
    @Override
    public ProppserInfo selectByPrimaryKey(Integer proppserId) {
        return proppserInfoMapper.selectByPrimaryKey(proppserId);
    }

    @Override
    public List<ProppserInfo> listByProppserIds(List<Integer> proppserIds) {
        return proppserInfoMapper.listByProppserIds(proppserIds);
    }

    @Override
    public List<GpsQueryListVo> listByForm(GpsQueryListForm form) {
        return proppserInfoMapper.listByForm(form);
    }
}
