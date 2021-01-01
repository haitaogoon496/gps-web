package com.mljr.gps.vo.orderVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderInfoVo {
    
    /**
     * 经销商编号
     */
    private String appCode;

    /**
     * 经销商单位
     */
    private String companyName;
    
    /**
     * 经销商单位编号
     */
    private Integer companyCode;

    /**
     * 公司类型
     */
    private String companyType;
    
    /**
     * 经销商单位名称
     */
    private String dealerName;
    
    /**
     * 经销商单位编号
     */
    private Integer dealerCode;
    
    /**
     * 经销商所属城市
     */
    private String city;
    
    /**
     * 经销商所属城市编码
     */
    private String cityCode;
    
    /**
     * 所属市场编号
     */
    private String belongMarket;
    
    /**
     * 所属市场中文描述
     */
    private String belongMarketStr;
    
    /**
     * 客户所属门店
     */
    private String belongDealer;
    /**
     * 客户所属门店中文描述
     */
    private String belongDealerStr;
    
    /**
     * 所属销售人编号
     */
    private String belongSale;
    
    /**
     * 所属销售人中文描述
     */
    private String belongSaleStr;
    
    /**
     * 销售人手机号
     */
    private String belongSaleTel;
    
    /**
     * 所属销售主管编号
     */
    private String belongSaleManager;
    
    /**
     * 所属销售主管中文描述
     */
    private String belongSaleManagerStr;
    
    /**
     * 所属运营编号
     */
    private String belongOperation;
    
    /**
     * 所属运营中文描述
     */
    private String belongOperationStr;
    
    /**
     * 金融专员名称
     */
    private String userName;
    
    /**
     * 金融专员联系电话
     */
    private String phone;
    
    /**
     * 审批流程编号
     */
    private Integer flowSeq;
    
    /**
     * 审批流程名称
     */
    private String flowName;
    
    /**
     * 贷款产品
     */
    private String productName;
    
    /**
     * 贷款产品编号
     */
    private Integer productCode;
    
    /**
     * 车牌类型编号
     */
    private Integer isCompanyLicense;
    
    /**
     * 车牌类型中文描述
     */
    private String isCompanyLicenseStr;
    
    /**
     * 申请单来源
     */
    private String comeFrom;
    
    /**
     * 申请单来源中文描述
     */
    private String comeFromStr;

    /**
     * 工作流
     */
    private String workflowQueue;

    /**
     * 反欺诈标签
     */
    private String creditRecords;
    /**
     * 是否直营
     */
    private Boolean isZy;
}
