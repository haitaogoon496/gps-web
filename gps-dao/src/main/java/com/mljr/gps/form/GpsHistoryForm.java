package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import lombok.Builder;
import lombok.Data;

/**
 * @description:  GPS审批历史记录Form
 * @Date : 2018/6/4 下午5:15
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
@Builder
public class GpsHistoryForm extends BaseForm {

    /**
     * 申请单编号
     */
    private String appCode;
    /**
     * 审批状态(0:未审批 1:已审批 2:已驳回)
     */
    private Integer approvalStatus;
}
