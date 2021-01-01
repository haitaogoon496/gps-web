package com.mljr.gps.voconvertor;

import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.entity.SyItem;
import com.mljr.gps.vo.SyItemVo;

/**
 * @description:
 * @Date : 2018/4/12$ 18:13$
 * @Author : liht
 */
public class SyItemVoConvertor extends VoConvertor<SyItemVo,SyItem> {
    @Override
    public SyItemVo convert(SyItem bo) {
        SyItemVo vo = new SyItemVo();
        vo.setItemId(StringUtils.killNull(bo.getItemId()));
        vo.setItemDesc(StringUtils.killNull(bo.getItemDesc()));
        vo.setItemIcon(StringUtils.killNull(bo.getItemIcon()));
        vo.setItemLocation(StringUtils.killNull(bo.getItemLocation()));
        vo.setItemTitle(StringUtils.killNull(bo.getItemTitle()));
        vo.setMenuTitle(StringUtils.killNull(bo.getMenuTitle()));

        return vo;
    }
}
