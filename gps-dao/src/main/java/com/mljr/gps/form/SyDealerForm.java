package com.mljr.gps.form;

import com.lyqc.base.common.BaseForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @Date : 上午11:18 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Builder
@Data
@AllArgsConstructor
public class SyDealerForm extends BaseForm {

    /**
     * 门店编码
     */
    private Integer dealerCode;
    private String dealerName;
    private String companyName;
    /**
     * 表名
     */
    private String tableName;

    @ApiModelProperty(name="dealerCodes", value="门店列表")
    private List<Integer> dealerCodes;

    public SyDealerForm() {
    }

    public SyDealerForm(String dealerCode, String dealerName){
        super.setCode(dealerCode);
        super.setName(dealerName);
    }

}
