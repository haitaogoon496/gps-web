package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lingyu.shang
 */
@Data
public class GpsMonitorForm extends BaseForm {

    @ApiModelProperty(name="removeGpsUserIds", value="要移除的生产的GPS列表")
    private List<Integer> removeGpsUserIds;

    @ApiModelProperty(name="removeGpsUserIds", value="要移除的测试的GPS列表")
        private List<Integer> removeTestGpsUserIds;

    @ApiModelProperty(name="upperLimit", value="审核人员自动派单持有单量上限")
    private Integer upperLimit;

    @ApiModelProperty(name="gpsWebAcceptBillTestDealerCode", value="测试门店配置")
    private List<Integer> gpsWebAcceptBillTestDealerCode;
}
