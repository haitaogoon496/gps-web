package com.mljr.gps.service;

import com.mljr.gps.entity.SyButton;

import java.util.List;

/**
 * @description:
 * @Date : 2018/11/2$ 17:21$
 * @Author : liht
 */
public interface SyButtonService {

    /**
     * 获取按钮根据角色
     * @param roleIds
     * @return
     */
    List<SyButton> selectSyButtonsByRoles(List<Integer> roleIds);
}
