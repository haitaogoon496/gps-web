package com.mljr.gps.common.enums;

/**
 * @description:
 * @Date : 2018/9/29$ 14:53$
 * @Author : liht
 */
public enum PoseidonProviderEnum {

    GET_ORDER_INFO("/web/api/approval/order/getOrderInfo", "订单详情接口"),
    GET_REPLY_LOAN_FEE("/web/api/approval/order/getReplyLoanFee", "批复贷款金额"),
    GET_PROPPSER_INFO("/web/api/approval/order/getProppserInfo","申请人信息接口"),
    GET_SPOUSE_INFO("/web/api/approval/order/getSpouseInfo","配偶信息"),
    GET_EM_CONTACTS_INFO("/web/api/approval/order/getEmContactsInfo", "联系人信息"),
    GET_DEALER_INFO("/web/api/approval/order/getDealerInfo","商户信息"),
    GET_CAR_INFO("/web/api/approval/order/getCarInfo","车辆信息"),
    GET_CREDIT_INFO("/web/api/approval/order/getCreditInfo","贷款信息"),
    GET_ANNEX_LIST_INFO("/web/api/approval/approvalInfo/getAnnexList","附件信息"),
    GET_MESSAGE_LIST_INFO("/web/api/approval/approvalInfo/getMessageList","留言信息"),
    GET_CA_APP_APPROVAL_LIST_INFO("/web/api/approval/approvalInfo/getCaAppApprovalList","审批记录信息"),
    GET_ORDER_TIPS_INFO("/web/api/approval/order/getOrderTips","经销商显示"),
    GET_RISK_INFO("/web/api/approval/order/getRiskInfo","风控信息"),
    GET_APPROVAL_INFO("/web/api/approval/order/getApprovalInfo","审批信息"),
    GET_GUARANTEE_INFO("/web/api/approval/order/getGuaranteeInfo","担保人信息"),
    GET_CS_VIN("/vincode/carModels","根据vin码获取车型");



    PoseidonProviderEnum(String url, String desc) {
        this.url =url;
        this.desc =desc;
    }
    private String url;
    private String desc;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    }
