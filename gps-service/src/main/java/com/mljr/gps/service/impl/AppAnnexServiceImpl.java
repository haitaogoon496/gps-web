package com.mljr.gps.service.impl;

import com.mljr.gps.entity.AppAnnex;
import com.mljr.gps.mapper.AppAnnexMapper;
import com.mljr.gps.service.AppAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 申请单附件Service
 * @Date : 2018/6/15 下午7:36
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Service
public class AppAnnexServiceImpl implements AppAnnexService {
    @Autowired
    private AppAnnexMapper appAnnexMapper;
    @Override
    public int batchInsert(List<AppAnnex> list) {
        return appAnnexMapper.batchInsert(list);
    }

    @Override
    public int insert(AppAnnex record) {
        return appAnnexMapper.insert(record);
    }

    @Override
    public AppAnnex findAnnexById(Integer id) {
        return appAnnexMapper.selectByPrimaryKey(id);
    }

}
