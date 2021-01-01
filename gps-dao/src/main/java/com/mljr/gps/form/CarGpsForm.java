package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 车辆GPS信息Form对象
 * @Date : 2018/6/4 下午5:08
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarGpsForm extends BaseForm {
    /**
     * 订单号
     */
    private String appCode;
    /**
     * 历史记录id
     */
    private Integer historyId;
    /**
     * 订单号集合
     */
    private List<String> appCodeScope;

    /**
     * 状态：0-审核失败，1-审核成功，2-审核中
     */
    private Integer status;

    /**
     * 状态：0-审核失败，1-审核成功，2-审核中
     */
    private Integer newStatus;
}
