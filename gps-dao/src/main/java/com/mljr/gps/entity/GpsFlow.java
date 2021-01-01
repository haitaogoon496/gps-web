package com.mljr.gps.entity;

import com.lyqc.settable.SubmitOrApprovalPropsSettable;
import com.mljr.gps.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: GPS审批流程实体对象
 * @Date : 2018/6/4 下午3:20
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
@NoArgsConstructor
public class GpsFlow extends BaseEntity  implements SubmitOrApprovalPropsSettable {
    /**
     *
     */
    private Integer id;
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
    private Integer submitUserId;
    /**
     * 提交人姓名
     */
    private String submitUserName;
    /**
     * 流程步骤
     */
    private Integer flowStep;
    /**
     * 流程状态
     */
    private Integer flowStatus;
    /**
     * 审批时间
     */
    private String approvalTime;
    /**
     * 审批人id
     */
    private Integer approvalUserId;
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
     */
    private Integer violationSituation;
    /**
     * 违规类别，多个以;分割
     */
    private String violationType;


    @Override
    public void setSubmitProps(Integer submitUserId, String submitUserName, String submitTime) {
        this.submitUserId = submitUserId;
        this.submitUserName = submitUserName;
        this.submitTime = submitTime;
    }

    @Override
    public void setApprovalProps(Integer approvalUserId, String approvalUserName, String approvalTime) {
        this.approvalUserId = approvalUserId;
        this.approvalUserName = approvalUserName;
        this.approvalTime = approvalTime;
    }
}