package com.mljr.gps.vo.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RiskInfoVo implements Serializable {
    
    private static final long serialVersionUID = -1047182625812523044L;
    @ApiModelProperty(name="isJxlReport",value="是否有聚立信",dataType="String")
    private String isJxlReport;
    @ApiModelProperty(name="sysaRemarks",value="系统备注",dataType="String")
    private String sysaRemarks;
    @ApiModelProperty(name="workflowQueue",value="系统审批建议工作流",dataType="String")
    private String workflowQueue;
    @ApiModelProperty(name="highRateYyRiskRemarks",value="高风险产品审批备注",dataType="String")
    private String highRateYyRiskRemarks;
    @ApiModelProperty(name="insRuleBCode",value="反欺诈信息",dataType="String")
    private String insRuleBCode;
    @ApiModelProperty(name="yyRiskRemarks",value="有用风控审批备注",dataType="String")
    private String yyRiskRemarks;
    @ApiModelProperty(name="haveGzt",value="是否有国政通信息",dataType="Boolean")
    private boolean haveGzt;
    @ApiModelProperty(name="gzt",value="国政通不良信息",dataType="List")
    private List gzt;
    @ApiModelProperty(name="policyTipList",value="政策提示",dataType="List")
    private List<String> policyTipList;
    @ApiModelProperty(name="sysaRemarkList",value="系统审批备注",dataType="List")
    private List<String> sysaRemarkList;
    @ApiModelProperty(name="fraudTipList",value="反欺诈信息",dataType="List")
    private List<String> fraudTipList;

}
