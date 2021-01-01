package com.mljr.gps.vo;

import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.mljr.gps.base.vo.BaseVo;
import lombok.Data;

/**
 * @description:gps流程表
 * @Date : 2018/8/21 14:48
 * @Author : lihaitao
 */
@Data
public class GpsFlowVo extends BaseVo {
    /**
     *
     */
    private String id;
    /**
     * 订单号
     */
    private String appCode;
    /**
     * 提交时间
     */
    private String submitTime;
    /**
     * 提交人id
     */
    private String submitUserId;
    /**
     * 提交人姓名
     */
    private String submitUserName;
    /**
     * 流程步骤
     */
    private String flowStep;
    /**
     * 流程状态
     */
    private String flowStatus;
    /**
     * 审批时间
     */
    private String approvalTime;
    /**
     * 审批人id
     */
    private String approvalUserId;
    /**
     * 审批人姓名
     */
    private String approvalUserName;
    /**
     * 审批意见
     */
    private String approvalIdea;
    /**
     * 备注
     */
    private String remark;
    /**
     * '违规情况(1:有违规 0:无违规)
     * {@link CarGpsConstant.ViolationSituationEnum#getIndex()}
     */
    private Integer violationSituation;

    private String violationSituationDesc;
    /**
     * 违规类别，多个以;分割
     * {@link CarGpsConstant.ViolationTypeEnum#getIndex()}
     */
    private String violationType;

    private String violationTypeDesc;
}
