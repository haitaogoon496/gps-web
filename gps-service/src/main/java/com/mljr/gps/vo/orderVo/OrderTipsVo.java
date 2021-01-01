package com.mljr.gps.vo.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 上午11:43 2018/8/21
 */
@Data
public class OrderTipsVo {

    @ApiModelProperty(name="name",value="显示tips名称",dataType="String")
    private String name;
    @ApiModelProperty(name="value",value="真实值",dataType="String")
    private String value;
    @ApiModelProperty(name="colorType",value="颜色类型 提示：tips 警告：warn 优先：first",dataType="String")
    private String colorType;

}
