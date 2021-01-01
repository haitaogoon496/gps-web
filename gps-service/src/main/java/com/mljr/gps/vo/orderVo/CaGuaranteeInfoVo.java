package com.mljr.gps.vo.orderVo;

import com.mljr.gps.common.enums.IdTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

public class CaGuaranteeInfoVo {

    private String appCode;

    private Integer guaranteeId;
    @ApiModelProperty(name="idType",value="证件类型",dataType="String")
    private String idType;
    @ApiModelProperty(name="idno",value="担保人证件号",dataType="String")
    private String idno;
    @ApiModelProperty(name="guaranteeName",value="担保人姓名",dataType="String")
    private String guaranteeName;
    @ApiModelProperty(name="province",value="担保人户籍省",dataType="String")
    private String province;
    @ApiModelProperty(name="city",value="担保人户籍市",dataType="String")
    private String city;
    @ApiModelProperty(name="address",value="担保人户籍地址",dataType="String")
    private String address;
    @ApiModelProperty(name="nowProvince",value="担保人现居住省",dataType="String")
    private String nowProvince;
    @ApiModelProperty(name="nowCity",value="担保人现居住市",dataType="String")
    private String nowCity;
    @ApiModelProperty(name="nowAddress",value="担保人现居住地址",dataType="String")
    private String nowAddress;
    @ApiModelProperty(name="nowCompany",value="担保人现工作单位名称",dataType="String")
    private String nowCompany;
    @ApiModelProperty(name="nowUnitProvince",value="担保人现工作单位省",dataType="String")
    private String nowUnitProvince;
    @ApiModelProperty(name="nowUnitCity",value="担保人现工作单位市",dataType="String")
    private String nowUnitCity;
    @ApiModelProperty(name="nowUnitAddress",value="担保人现工作地址",dataType="String")
    private String nowUnitAddress;
    @ApiModelProperty(name="mobile",value="担保人手机号码",dataType="String")
    private String mobile;
    @ApiModelProperty(name="relationGFl",value="与申请人关系",dataType="String")
    private String relationGFl;
    @ApiModelProperty(name="relationGFlStr",value="与申请人关系Str",dataType="String")
    private String relationGFlStr;

    public String getIdTypeStr() {
        String idTypeStr="";
        if(StringUtils.isNotEmpty(this.idType)){
            idTypeStr = IdTypeEnum.getNameByValue(this.idType);
        }
        return idTypeStr;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Integer getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(Integer guaranteeId) {
        this.guaranteeId = guaranteeId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getGuaranteeName() {
        return guaranteeName;
    }

    public void setGuaranteeName(String guaranteeName) {
        this.guaranteeName = guaranteeName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNowProvince() {
        return nowProvince;
    }

    public void setNowProvince(String nowProvince) {
        this.nowProvince = nowProvince;
    }

    public String getNowCity() {
        return nowCity;
    }

    public void setNowCity(String nowCity) {
        this.nowCity = nowCity;
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public String getNowCompany() {
        return nowCompany;
    }

    public void setNowCompany(String nowCompany) {
        this.nowCompany = nowCompany;
    }

    public String getNowUnitProvince() {
        return nowUnitProvince;
    }

    public void setNowUnitProvince(String nowUnitProvince) {
        this.nowUnitProvince = nowUnitProvince;
    }

    public String getNowUnitCity() {
        return nowUnitCity;
    }

    public void setNowUnitCity(String nowUnitCity) {
        this.nowUnitCity = nowUnitCity;
    }

    public String getNowUnitAddress() {
        return nowUnitAddress;
    }

    public void setNowUnitAddress(String nowUnitAddress) {
        this.nowUnitAddress = nowUnitAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRelationGFl() {
        return relationGFl;
    }

    public void setRelationGFl(String relationGFl) {
        this.relationGFl = relationGFl;
    }

    public String getRelationGFlStr() {
        return relationGFlStr;
    }

    public void setRelationGFlStr(String relationGFlStr) {
        this.relationGFlStr = relationGFlStr;
    }
}