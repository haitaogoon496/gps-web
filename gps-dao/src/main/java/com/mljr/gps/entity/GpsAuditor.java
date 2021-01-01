package com.mljr.gps.entity;

/**
 * @description:  审核人员拥有的gps领单后的单子个数
 * @Date : 2018/9/6 21:21
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
public class GpsAuditor {

    /**
     * 审核人员持有gps领单后的单子数量
     */
    private Integer num;
    /**
     * 审核人员id
     */
    private Integer approvalUserId;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(Integer approvalUserId) {
        this.approvalUserId = approvalUserId;
    }
}
