package com.mljr.gps.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @Date : 2018/3/21$ 17:11$
 * @Author : liht
 */
public class SyDealerVo implements Serializable{

    private static final long serialVersionUID = 2325522658155156789L;
    private String extendSafeDealerId;
    @ApiModelProperty(name="dealerCode",value="经销商门店编码")
    private String dealerCode;
    @ApiModelProperty(name="dealerName",value="经销商门店名称")
    private String dealerName;
    @ApiModelProperty(name="dealerType",value="经销商门店类型 目前分两类：DD(1)、 SP(2,3) ")
    private String dealerType;
    @ApiModelProperty(name="companyCode",value="所属经销商单位编码")
    private String companyCode;
    @ApiModelProperty(name="recAccountName",value="收款账户名")
    private String recAccountName;
    @ApiModelProperty(name="recAccountCard",value="收款账户证件号码")
    private String recAccountCard;
    @ApiModelProperty(name="recAccountBank",value="收款开户行")
    private String recAccountBank;
    @ApiModelProperty(name="bankProvince",value="收款开户行所属省份")
    private String bankProvince;
    @ApiModelProperty(name="bankCity",value="收款开户行所属市")
    private String bankCity;
    @ApiModelProperty(name="recAccountNo",value="收款借记卡号")
    private String recAccountNo;
    @ApiModelProperty(name="address",value="门店地址")
    private String address;
    @ApiModelProperty(name="rtamil",value="AM邮箱地址")
    private String rtamil;
    @ApiModelProperty(name="am",value="AM")
    private String am;
    @ApiModelProperty(name="sv",value="sv")
    private String sv;
    @ApiModelProperty(name="gmName",value="经销商门店GM姓名")
    private String gmName;
    @ApiModelProperty(name="gmMobile",value="经销商门店GM手机号码")
    private String gmMobile;
    @ApiModelProperty(name="gmPhone",value="经销商门店GM电话")
    private String gmPhone;
    @ApiModelProperty(name="gmEmail",value="经销商门店GM email")
    private String gmEmail;
    @ApiModelProperty(name="smName",value="经销商门店SM姓名")
    private String smName;
    @ApiModelProperty(name="smMobile",value="经销商门店SM手机号码")
    private String smMobile;
    @ApiModelProperty(name="smPhone",value="经销商门店SM电话")
    private String smPhone;
    @ApiModelProperty(name="smPsw",value="SM维护密码")
    private String smPsw;
    @ApiModelProperty(name="smEamil",value="经销商门店SM email")
    private String smEamil;
    @ApiModelProperty(name="fmName",value="经销商门店FM姓名")
    private String fmName;
    @ApiModelProperty(name="fmMobile",value="经销商门店FM手机号码")
    private String fmMobile;
    @ApiModelProperty(name="fmPhone",value="经销商门店FM电话")
    private String fmPhone;
    @ApiModelProperty(name="fmEamil",value="经销商门店FM email")
    private String fmEamil;
    @ApiModelProperty(name="fmPsw",value="FM维护密码")
    private String fmPsw;
    @ApiModelProperty(name="mmName",value="经销商门店MM姓名")
    private String mmName;
    @ApiModelProperty(name="mmMobile",value="经销商门店MM手机号码")
    private String mmMobile;
    @ApiModelProperty(name="mmPhone",value="经销商门店MM电话")
    private String mmPhone;
    @ApiModelProperty(name="mmEamil",value="经销商门店MM email")
    private String mmEamil;
    @ApiModelProperty(name="saleName",value="经销商门店售后姓名")
    private String saleName;
    @ApiModelProperty(name="saleMobile",value="经销商门店售后手机号码")
    private String saleMobile;
    @ApiModelProperty(name="salePhone",value="经销商门店售后电话")
    private String salePhone;
    @ApiModelProperty(name="saleEmail",value="经销商门店售后 email")
    private String saleEmail;
    @ApiModelProperty(name="trainName",value="经销商门店内训师姓名")
    private String trainName;
    @ApiModelProperty(name="trainMobile",value="经销商门店内训师手机号码")
    private String trainMobile;
    @ApiModelProperty(name="trainPhone",value="经销商门店内训师电话")
    private String trainPhone;
    @ApiModelProperty(name="trainEmail",value="经销商门店内训师 email")
    private String trainEmail;
    @ApiModelProperty(name="clcType",value="CLC Review时间")
    private String clcType;
    @ApiModelProperty(name="serverInvoiceName",value="经销商门店服务费发票名称")
    private String serverInvoiceName;
    @ApiModelProperty(name="serverInvoiceType",value="经销商门店服务费发票类型")
    private String serverInvoiceType;
    @ApiModelProperty(name="dealerEmail",value="经销商门店电子邮件地址")
    private String dealerEmail;
    @ApiModelProperty(name="contactPerson",value="经销商门店联系人")
    private String contactPerson;
    @ApiModelProperty(name="accountName",value="经销商门店账户名")
    private String accountName;
    @ApiModelProperty(name="remarks1",value="经销商门店电话")
    private String remarks1;
    @ApiModelProperty(name="interestRate",value="Interest Rate")
    private String interestRate;
    @ApiModelProperty(name="provinceRate",value="Province Rate")
    private String provinceRate;
    @ApiModelProperty(name="status",value="域状态  1-有效,  0-无效")
    private String status;
    @ApiModelProperty(name="onlineTime",value="创建时间")
    private String onlineTime;
    @ApiModelProperty(name="updateTime",value="更新时间")
    private String updateTime;
    @ApiModelProperty(name="remarks",value="备注")
    private String remarks;
    @ApiModelProperty(name="saleArea",value="门店所属销售分区")
    private String saleArea;
    @ApiModelProperty(name="province",value="所属省份")
    private String province;
    @ApiModelProperty(name="city",value="所属城市")
    private String city;
    @ApiModelProperty(name="recAccountBank2",value="收款开户银行")
    private String recAccountBank2;
    @ApiModelProperty(name="accountType",value="账户类型")
    private String accountType;
    @ApiModelProperty(name="bankMobile",value="经销商银行卡手机预留号码 ")
    private String bankMobile;
    @ApiModelProperty(name="lineNumber",value="联行号")
    private String lineNumber;
    @ApiModelProperty(name="isFraudFlight",value="秒拒接口（1:正常 0:禁用） ")
    private String isFraudFlight;
    @ApiModelProperty(name="bankProvinceRegCode",value="收款开户行所属省份reg_code")
    private String bankProvinceRegCode;
    @ApiModelProperty(name="bankCityRegCode",value="收款开户行所属市reg_code")
    private String bankCityRegCode;
    @ApiModelProperty(name="provinceRegCode",value="所属省份reg_code")
    private String provinceRegCode;
    @ApiModelProperty(name="cityRegCode",value="所属城市reg_code")
    private String cityRegCode;
    @ApiModelProperty(name="companyName",value="公司名称")
    private String companyName;

    public String getExtendSafeDealerId() {
        return extendSafeDealerId;
    }

    public void setExtendSafeDealerId(String extendSafeDealerId) {
        this.extendSafeDealerId = extendSafeDealerId;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRecAccountName() {
        return recAccountName;
    }

    public void setRecAccountName(String recAccountName) {
        this.recAccountName = recAccountName;
    }

    public String getRecAccountCard() {
        return recAccountCard;
    }

    public void setRecAccountCard(String recAccountCard) {
        this.recAccountCard = recAccountCard;
    }

    public String getRecAccountBank() {
        return recAccountBank;
    }

    public void setRecAccountBank(String recAccountBank) {
        this.recAccountBank = recAccountBank;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getRecAccountNo() {
        return recAccountNo;
    }

    public void setRecAccountNo(String recAccountNo) {
        this.recAccountNo = recAccountNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRtamil() {
        return rtamil;
    }

    public void setRtamil(String rtamil) {
        this.rtamil = rtamil;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getGmName() {
        return gmName;
    }

    public void setGmName(String gmName) {
        this.gmName = gmName;
    }

    public String getGmMobile() {
        return gmMobile;
    }

    public void setGmMobile(String gmMobile) {
        this.gmMobile = gmMobile;
    }

    public String getGmPhone() {
        return gmPhone;
    }

    public void setGmPhone(String gmPhone) {
        this.gmPhone = gmPhone;
    }

    public String getGmEmail() {
        return gmEmail;
    }

    public void setGmEmail(String gmEmail) {
        this.gmEmail = gmEmail;
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getSmMobile() {
        return smMobile;
    }

    public void setSmMobile(String smMobile) {
        this.smMobile = smMobile;
    }

    public String getSmPhone() {
        return smPhone;
    }

    public void setSmPhone(String smPhone) {
        this.smPhone = smPhone;
    }

    public String getSmPsw() {
        return smPsw;
    }

    public void setSmPsw(String smPsw) {
        this.smPsw = smPsw;
    }

    public String getSmEamil() {
        return smEamil;
    }

    public void setSmEamil(String smEamil) {
        this.smEamil = smEamil;
    }

    public String getFmName() {
        return fmName;
    }

    public void setFmName(String fmName) {
        this.fmName = fmName;
    }

    public String getFmMobile() {
        return fmMobile;
    }

    public void setFmMobile(String fmMobile) {
        this.fmMobile = fmMobile;
    }

    public String getFmPhone() {
        return fmPhone;
    }

    public void setFmPhone(String fmPhone) {
        this.fmPhone = fmPhone;
    }

    public String getFmEamil() {
        return fmEamil;
    }

    public void setFmEamil(String fmEamil) {
        this.fmEamil = fmEamil;
    }

    public String getFmPsw() {
        return fmPsw;
    }

    public void setFmPsw(String fmPsw) {
        this.fmPsw = fmPsw;
    }

    public String getMmName() {
        return mmName;
    }

    public void setMmName(String mmName) {
        this.mmName = mmName;
    }

    public String getMmMobile() {
        return mmMobile;
    }

    public void setMmMobile(String mmMobile) {
        this.mmMobile = mmMobile;
    }

    public String getMmPhone() {
        return mmPhone;
    }

    public void setMmPhone(String mmPhone) {
        this.mmPhone = mmPhone;
    }

    public String getMmEamil() {
        return mmEamil;
    }

    public void setMmEamil(String mmEamil) {
        this.mmEamil = mmEamil;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleMobile() {
        return saleMobile;
    }

    public void setSaleMobile(String saleMobile) {
        this.saleMobile = saleMobile;
    }

    public String getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(String salePhone) {
        this.salePhone = salePhone;
    }

    public String getSaleEmail() {
        return saleEmail;
    }

    public void setSaleEmail(String saleEmail) {
        this.saleEmail = saleEmail;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainMobile() {
        return trainMobile;
    }

    public void setTrainMobile(String trainMobile) {
        this.trainMobile = trainMobile;
    }

    public String getTrainPhone() {
        return trainPhone;
    }

    public void setTrainPhone(String trainPhone) {
        this.trainPhone = trainPhone;
    }

    public String getTrainEmail() {
        return trainEmail;
    }

    public void setTrainEmail(String trainEmail) {
        this.trainEmail = trainEmail;
    }

    public String getClcType() {
        return clcType;
    }

    public void setClcType(String clcType) {
        this.clcType = clcType;
    }

    public String getServerInvoiceName() {
        return serverInvoiceName;
    }

    public void setServerInvoiceName(String serverInvoiceName) {
        this.serverInvoiceName = serverInvoiceName;
    }

    public String getServerInvoiceType() {
        return serverInvoiceType;
    }

    public void setServerInvoiceType(String serverInvoiceType) {
        this.serverInvoiceType = serverInvoiceType;
    }

    public String getDealerEmail() {
        return dealerEmail;
    }

    public void setDealerEmail(String dealerEmail) {
        this.dealerEmail = dealerEmail;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getProvinceRate() {
        return provinceRate;
    }

    public void setProvinceRate(String provinceRate) {
        this.provinceRate = provinceRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea;
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

    public String getRecAccountBank2() {
        return recAccountBank2;
    }

    public void setRecAccountBank2(String recAccountBank2) {
        this.recAccountBank2 = recAccountBank2;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankMobile() {
        return bankMobile;
    }

    public void setBankMobile(String bankMobile) {
        this.bankMobile = bankMobile;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getIsFraudFlight() {
        return isFraudFlight;
    }

    public void setIsFraudFlight(String isFraudFlight) {
        this.isFraudFlight = isFraudFlight;
    }

    public String getBankProvinceRegCode() {
        return bankProvinceRegCode;
    }

    public void setBankProvinceRegCode(String bankProvinceRegCode) {
        this.bankProvinceRegCode = bankProvinceRegCode;
    }

    public String getBankCityRegCode() {
        return bankCityRegCode;
    }

    public void setBankCityRegCode(String bankCityRegCode) {
        this.bankCityRegCode = bankCityRegCode;
    }

    public String getProvinceRegCode() {
        return provinceRegCode;
    }

    public void setProvinceRegCode(String provinceRegCode) {
        this.provinceRegCode = provinceRegCode;
    }

    public String getCityRegCode() {
        return cityRegCode;
    }

    public void setCityRegCode(String cityRegCode) {
        this.cityRegCode = cityRegCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
