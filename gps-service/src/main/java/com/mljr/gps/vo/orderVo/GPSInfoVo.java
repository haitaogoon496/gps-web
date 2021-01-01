package com.mljr.gps.vo.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 订单详情中GPS信息
 * @author: zhaoxin
 * @date: 2018/10/15 下午1:46
 **/
@Data
public class GPSInfoVo {

    @ApiModelProperty(name="gpsCount",value="GPS安装数量",dataType="Integer")
    private Integer gpsCount;

    @ApiModelProperty(name="gpsPro",value="GPS属性",dataType="Integer")
    private String gpsPro;

    @ApiModelProperty(name="gpsProStr",value="GPS属性",dataType="String")
    private String gpsProStr;

    @ApiModelProperty(name="gpsDealer",value="gps提供商",dataType="String")
    private String gpsDealer;

    @ApiModelProperty(name="gpsDealerStr",value="gps提供商",dataType="String")
    private String gpsDealerStr;

    @ApiModelProperty(name="gpsNo",value="GPS设备ID",dataType="String")
    private String gpsNo;
}
