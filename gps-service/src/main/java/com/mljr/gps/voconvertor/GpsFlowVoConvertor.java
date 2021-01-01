package com.mljr.gps.voconvertor;

import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.lyqc.util.EnumConvert;
import com.mljr.gps.common.util.StringUtils;
import com.mljr.gps.entity.GpsFlow;
import com.mljr.gps.vo.GpsFlowVo;
import com.mljr.util.StringTools;

import java.util.Arrays;
import java.util.Optional;

/**
 * @description: GpsFlowVoConvertor
 * @Date : 2018/4/12$ 18:13$
 * @Author : liht
 */
public class GpsFlowVoConvertor extends VoConvertor<GpsFlowVo,GpsFlow> {
    @Override
    public GpsFlowVo convert(GpsFlow bo) {
        GpsFlowVo vo = new GpsFlowVo();
        vo.setAppCode(StringUtils.killNull(bo.getAppCode()));
        vo.setApprovalIdea(StringUtils.killNull(bo.getApprovalIdea()));
        vo.setApprovalTime(StringUtils.killNull(bo.getApprovalTime()));
        vo.setApprovalUserId(StringUtils.killNull(bo.getApprovalUserId()));
        vo.setApprovalUserName(StringUtils.killNull(bo.getApprovalUserName()));
        vo.setFlowStatus(StringUtils.killNull(bo.getFlowStatus()));
        vo.setId(StringUtils.killNull(bo.getId()));
        vo.setFlowStep(StringUtils.killNull(bo.getFlowStep()));
        vo.setRemark(StringUtils.killNull(bo.getRemark()));
        vo.setSubmitTime(StringUtils.killNull(bo.getSubmitTime()));
        vo.setSubmitUserId(StringUtils.killNull(bo.getSubmitUserId()));
        vo.setSubmitUserName(StringUtils.killNull(bo.getSubmitUserName()));
        vo.setViolationSituation(Optional.ofNullable(bo.getViolationSituation()).orElse(-1));
        if(null != bo.getViolationSituation()){
            vo.setViolationSituationDesc(Optional.ofNullable(CarGpsConstant.ViolationSituationEnum.getNameByIndex(bo.getViolationSituation())).orElse(""));
        }else{
            vo.setViolationSituationDesc("");
        }
        String violationType = bo.getViolationType();
        vo.setViolationType(Optional.ofNullable(violationType).orElse(""));
        vo.setViolationTypeDesc(Optional.ofNullable(violationType).orElse(""));
        return vo;
    }
}
