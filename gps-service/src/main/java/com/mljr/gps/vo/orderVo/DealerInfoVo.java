package com.mljr.gps.vo.orderVo;

import lombok.Data;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午6:11 2018/7/17
 */
@Data
public class DealerInfoVo {

    /**
     * 经销商单位
     */
    private String companyName;
    /**
     * 经销商单位code
     */
    private String companyCode;
    /**
     * 经销商名称
     */
    private String dealerName;
    /**
     * 经销商code
     */
    private String dealerCode;
    /**
     * 所属市场
     */
    private String belongMarket;
    /**
     * 所属市场str
     */
    private String belongMarketStr;


}
