package com.mljr.gps.service.impl;

import com.mljr.gps.entity.SyButton;
import com.mljr.gps.mapper.SyButtonMapper;
import com.mljr.gps.service.SyButtonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Date : 2018/11/2$ 17:23$
 * @Author : liht
 */
@Service
public class SyButtonServiceImpl implements SyButtonService{

    @Resource
    private SyButtonMapper syButtonMapper;

    @Override
    public List<SyButton> selectSyButtonsByRoles(List<Integer> list) {
        return syButtonMapper.selectButtonsByRoles(list);
    }
}
