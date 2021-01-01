package com.mljr.gps.voconvertor;

import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.vo.SyArgControlVo;
import com.mljr.util.TimeTools;

/**
 * @description:
 * @Date : 2018/3/22$ 11:01$
 * @Author : liht
 */
public class SyArgControlVoConvertor extends VoConvertor<SyArgControlVo, SyArgControl> {
    @Override
    public SyArgControlVo convert(SyArgControl bo) {
        SyArgControlVo vo = new SyArgControlVo();
        vo.setId(bo.getrId());
        vo.setName(StringUtils.killNull(bo.getConArgName()));
        vo.setCode(StringUtils.killNull(bo.getConArgCode()));
        vo.setValue(StringUtils.killNull(bo.getConArgValue()));
        vo.setConArgName(StringUtils.killNull(bo.getConArgName()));
        vo.setConArgValue(StringUtils.killNull(bo.getConArgValue()));
        vo.setConArgCode(StringUtils.killNull(bo.getConArgCode()));
        vo.setConArgType(StringUtils.killNull(bo.getConArgType()));
        vo.setConArgStatus(StringUtils.killNull(bo.getConArgStatus()));
        vo.setConArgStartDate(TimeTools.format4YYYYMMDDHHMISS(bo.getConArgStartdate()));
        vo.setConArgStopDate(TimeTools.format4YYYYMMDDHHMISS(bo.getConArgStopdate()));
        return vo;
    }
}
