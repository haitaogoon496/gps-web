package com.mljr.gps.vo.orderVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mljr.gps.common.enums.AnnexTypeEnum;
import lombok.Data;

import java.util.Date;

@Data
public class CaAppAnnexVo {

    private String annexId;

    private String appCode;

    private String annexName;

    private String annexAddress;

    private Date createTime;

    private Integer userId;

    private String userName;

    private Integer status;

    private String remarks;

    private Integer annexType;

    private String imgKey;
    /**
     * 订单来源
     * 1=老单子
     * 2=影像件中心
     */
    private Integer annexSource;

    public String getAnnexTypeStr(){
        if(null != this.annexType){
            return AnnexTypeEnum.getNameByValue(this.annexType);
        }
        return "";
    }
}
