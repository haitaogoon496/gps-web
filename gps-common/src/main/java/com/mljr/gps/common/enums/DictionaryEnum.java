package com.mljr.gps.common.enums;

import com.lyqc.base.enums.*;
import com.lyqc.base.enums.AnnexTypeEnum;
import com.lyqc.gpsprovider.enums.CarGpsConstant;
import com.lyqc.gpsweb.enums.GpsManualAuditCodeEnum;

/**
 * @description: 前端页面所需数据字典接口
 * @Date : 下午9:07 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public enum DictionaryEnum {
    IS_OLD(DictionaryConstant.YesOrNoEnum.values(),"isOld"),
    IS_HOUSE(DictionaryConstant.YesOrNoEnum.values(),"isHouse"),
    IS_DEALER(DictionaryConstant.YesOrNoEnum.values(),"isDealer"),
    IS_VALID(DictionaryConstant.ValidOrNoValidEnum.values(),"isVaild"),
    IS_COMPANY_LICENSE(CarLoanConstant.IsCompanyLicenseEnum.values(), "isCompanyLicense"),
    dataStatus(DictionaryConstant.YesOrNoEnum.values(),"dataStatus"),
    dealerType(DealerTypeEnum.values(),"dealerType"),
    APP_STATUS(AppInfoStatusEnum.values(),"appStatus"),
    IS_LCV(RuleConditionConstant.IsLcvEnum.values(),"isLcv"),
    IS_GPS(CarGpsConstant.AppInfoIsGpsEnum.values(),"isGps"),
    MANUAL_AUDIT_STATUS(GpsManualAuditCodeEnum.values(),"manualAuditStatus"),
    INSTALL_POSITION(CarGpsConstant.GpsInstallPositionEnum.values(),"installPosition"),
    VIOLATION_TYPE(CarGpsConstant.ViolationTypeEnum.values(),"violationType"),
    VIOLATION_SITUATION(CarGpsConstant.ViolationSituationEnum.values(),"violationSituation"),
    INSTALL_TYPE(CarGpsConstant.GpsInstallTypeEnum.values(),"installType"),
    ANNEX_TYPE(AnnexTypeEnum.values(),"annexType");

    /**
     * 枚举数组集合
     */
    private EnumValue[] enums;
    /**
     * 枚举代码值，用于前端查询的编码
     */
    private String code;

    /**
     * 构造函数
     * @param enums
     * @param code
     */
    DictionaryEnum(EnumValue[] enums, String code) {
        this.enums = enums;
        this.code = code;
    }

    public EnumValue[] getEnums() {
        return enums;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据名称获取枚举数组
     * @param code
     * @return
     */
    public static EnumValue[] getEnumsByCode(String code){
        for(DictionaryEnum e : DictionaryEnum.values()){
            if(e.getCode().equals(code)){
                return e.getEnums();
            }
        }
        return null;
    }
}
