package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description: GPS审批操作记录Form
 * @Date : 2018/6/4 下午5:18
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
@Builder
public class GpsOperateRecordForm extends BaseForm {

    private List<String> appCodeScope;

    /**
     * 流程状态(0:未审批 1:审批通过 2:已驳回 3:审批中),
     */
    private Integer flowStatus;

    /**
     * 申请单编号
     */
    private String appCode;
}
