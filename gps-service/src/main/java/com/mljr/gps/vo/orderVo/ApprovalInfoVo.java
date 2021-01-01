package com.mljr.gps.vo.orderVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ApprovalInfoVo implements Serializable {

    private static final long serialVersionUID = 2256345233365827632L;
    @ApiModelProperty(name="remarks",value="补充说明",dataType="String")
    private String remarks;
    @ApiModelProperty(name="loanRemarks",value="复审审批备注",dataType="String")
    private String loanRemarks;
    @ApiModelProperty(name="fyRemarks",value="复议审批备注",dataType="String")
    private String fyRemarks;
    @ApiModelProperty(name="bloanRemarks",value="贷前审批备注",dataType="String")
    private String bloanRemarks;
    @ApiModelProperty(name="weizongFirstRemarks",value="微众一审审批备注",dataType="String")
    private String weizongFirstRemarks;
    @ApiModelProperty(name="weizongSecondRemarks",value="微众二审审批备注",dataType="String")
    private String weizongSecondRemarks;
    @ApiModelProperty(name="webankFirstApr",value="是否可以重新发起微众一审",dataType="boolean")
    private boolean webankFirstApr;
    @ApiModelProperty(name="evaluationRemarks",value="评估备注",dataType="String")
    private String evaluationRemarks;
    @ApiModelProperty(name="evaluationTime",value="评估时间",dataType="Date")
    private Date evaluationTime;

}
