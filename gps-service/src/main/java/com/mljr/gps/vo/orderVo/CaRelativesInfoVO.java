package com.mljr.gps.vo.orderVo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CaRelativesInfoVO {

    @ApiModelProperty(name="appCode",value="单号",dataType="String")
    private String appCode;
    @ApiModelProperty(name="relativesId",value="配偶id",dataType="Integer")
    private Integer relativesId;
    @ApiModelProperty(name="idType",value="配偶证件类型",dataType="String")
    private String idType;
    @ApiModelProperty(name="idTypeStr",value="配偶证件类型",dataType="String")
    private String idTypeStr;
    @ApiModelProperty(name="idno",value="配偶证件号码",dataType="String")
    private String idno;
    @ApiModelProperty(name="relativesName",value="配偶姓名",dataType="String")
    private String relativesName;
    @ApiModelProperty(name="mobile",value="配偶手机号",dataType="String")
    private String mobile;
    @ApiModelProperty(name="gbossFlag",value="国政通是否一致 ",dataType="String")
    private String gbossFlag;
    @ApiModelProperty(name="policeAddr",value="国政通地区",dataType="String")
    private String policeAddr;

}