package com.mljr.gps.voconvertor;

import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.entity.GpsContract;
import com.mljr.gps.vo.GpsContractVo;
import com.mljr.util.TimeTools;

/**
 * @description:
 * @Date : 2018/4/12$ 18:13$
 * @Author : liht
 */
public class GpsContractVoConvertor extends VoConvertor<GpsContractVo,GpsContract> {
    @Override
    public GpsContractVo convert(GpsContract bo) {
        GpsContractVo vo = new GpsContractVo();
        vo.setRemark(StringUtils.killNull(bo.getRemark()));
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setAddress(StringUtils.killNull(bo.getAddress()));
        vo.setAppCode(StringUtils.killNull(bo.getAppCode()));
        vo.setContract(StringUtils.killNull(bo.getContract()));
        vo.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(bo.getCreateTime()));
        vo.setCreateUserName(StringUtils.killNull(bo.getCreateUserName()));
        vo.setInstaller(StringUtils.killNull(bo.getInstaller()));
        vo.setPhone(StringUtils.killNull(bo.getPhone()));

        return vo;
    }
}
