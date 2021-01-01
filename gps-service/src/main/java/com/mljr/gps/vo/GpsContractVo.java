package com.mljr.gps.vo;

import com.mljr.gps.base.vo.BaseVo;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @Date : 2018/8/21 14:48
 * @Author : lihaitao
 */
@Data
public class GpsContractVo extends BaseVo {
    private String id;

    private String appCode;

    private String contract;

    private String phone;

    private String address;

    private String installer;

    private String createTime;

    private String createUserId;

    private String createUserName;

    private String remark;
}
