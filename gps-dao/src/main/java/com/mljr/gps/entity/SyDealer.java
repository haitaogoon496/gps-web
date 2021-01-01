package com.mljr.gps.entity;

import com.mljr.gps.base.entity.BaseEntity;

import java.util.Date;

public class SyDealer extends BaseEntity {
    private static final long serialVersionUID = -8960348157912614283L;

    private Integer extendSafeDealerId;

    private Integer dealerCode;

    private String dealerName;

    private String dealerType;

    private Integer companyCode;

    private String recAccountName;

    private String recAccountCard;

    private String recAccountBank;

    private String bankProvince;

    private String bankCity;

    private String recAccountNo;

    private String address;

    private String rtamil;

    private String am;

    private String sv;

    private String gmName;

    private String gmMobile;

    private String gmPhone;

    private String gmEmail;

    private String smName;

    private String smMobile;

    private String smPhone;

    private String smPsw;

    private String smEamil;

    private String fmName;

    private String fmMobile;

    private String fmPhone;

    private String fmEamil;

    private String fmPsw;

    private String mmName;

    private String mmMobile;

    private String mmPhone;

    private String mmEamil;

    private String saleName;

    private String saleMobile;

    private String salePhone;

    private String saleEmail;

    private String trainName;

    private String trainMobile;

    private String trainPhone;

    private String trainEmail;

    private Date clcType;

    private String serverInvoiceName;

    private String serverInvoiceType;

    private String dealerEmail;

    private String contactPerson;

    private String accountName;

    private String remarks1;

    private String interestRate;

    private String provinceRate;

    private String status;

    private Date onlineTime;

    private Date updateTime;

    private String remarks;

    private String saleArea;

    private String province;

    private String city;

    private String recAccountBank2;

    private String accountType;

    private String bankMobile;

    private String lineNumber;

    private Byte isFraudFlight;

    private String bankProvinceRegCode;

    private String bankCityRegCode;

    private String provinceRegCode;

    private String cityRegCode;

    private String companyName;

    public Integer getExtendSafeDealerId() {
        return extendSafeDealerId;
    }

    public void setExtendSafeDealerId(Integer extendSafeDealerId) {
        this.extendSafeDealerId = extendSafeDealerId;
    }

    public Integer getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(Integer dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName == null ? null : dealerName.trim();
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType == null ? null : dealerType.trim();
    }

    public Integer getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(Integer companyCode) {
        this.companyCode = companyCode;
    }

    public String getRecAccountName() {
        return recAccountName;
    }

    public void setRecAccountName(String recAccountName) {
        this.recAccountName = recAccountName == null ? null : recAccountName.trim();
    }

    public String getRecAccountCard() {
        return recAccountCard;
    }

    public void setRecAccountCard(String recAccountCard) {
        this.recAccountCard = recAccountCard == null ? null : recAccountCard.trim();
    }

    public String getRecAccountBank() {
        return recAccountBank;
    }

    public void setRecAccountBank(String recAccountBank) {
        this.recAccountBank = recAccountBank == null ? null : recAccountBank.trim();
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince == null ? null : bankProvince.trim();
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity == null ? null : bankCity.trim();
    }

    public String getRecAccountNo() {
        return recAccountNo;
    }

    public void setRecAccountNo(String recAccountNo) {
        this.recAccountNo = recAccountNo == null ? null : recAccountNo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRtamil() {
        return rtamil;
    }

    public void setRtamil(String rtamil) {
        this.rtamil = rtamil == null ? null : rtamil.trim();
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am == null ? null : am.trim();
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv == null ? null : sv.trim();
    }

    public String getGmName() {
        return gmName;
    }

    public void setGmName(String gmName) {
        this.gmName = gmName == null ? null : gmName.trim();
    }

    public String getGmMobile() {
        return gmMobile;
    }

    public void setGmMobile(String gmMobile) {
        this.gmMobile = gmMobile == null ? null : gmMobile.trim();
    }

    public String getGmPhone() {
        return gmPhone;
    }

    public void setGmPhone(String gmPhone) {
        this.gmPhone = gmPhone == null ? null : gmPhone.trim();
    }

    public String getGmEmail() {
        return gmEmail;
    }

    public void setGmEmail(String gmEmail) {
        this.gmEmail = gmEmail == null ? null : gmEmail.trim();
    }

    public String getSmName() {
        return smName;
    }

    public void setSmName(String smName) {
        this.smName = smName == null ? null : smName.trim();
    }

    public String getSmMobile() {
        return smMobile;
    }

    public void setSmMobile(String smMobile) {
        this.smMobile = smMobile == null ? null : smMobile.trim();
    }

    public String getSmPhone() {
        return smPhone;
    }

    public void setSmPhone(String smPhone) {
        this.smPhone = smPhone == null ? null : smPhone.trim();
    }

    public String getSmPsw() {
        return smPsw;
    }

    public void setSmPsw(String smPsw) {
        this.smPsw = smPsw == null ? null : smPsw.trim();
    }

    public String getSmEamil() {
        return smEamil;
    }

    public void setSmEamil(String smEamil) {
        this.smEamil = smEamil == null ? null : smEamil.trim();
    }

    public String getFmName() {
        return fmName;
    }

    public void setFmName(String fmName) {
        this.fmName = fmName == null ? null : fmName.trim();
    }

    public String getFmMobile() {
        return fmMobile;
    }

    public void setFmMobile(String fmMobile) {
        this.fmMobile = fmMobile == null ? null : fmMobile.trim();
    }

    public String getFmPhone() {
        return fmPhone;
    }

    public void setFmPhone(String fmPhone) {
        this.fmPhone = fmPhone == null ? null : fmPhone.trim();
    }

    public String getFmEamil() {
        return fmEamil;
    }

    public void setFmEamil(String fmEamil) {
        this.fmEamil = fmEamil == null ? null : fmEamil.trim();
    }

    public String getFmPsw() {
        return fmPsw;
    }

    public void setFmPsw(String fmPsw) {
        this.fmPsw = fmPsw == null ? null : fmPsw.trim();
    }

    public String getMmName() {
        return mmName;
    }

    public void setMmName(String mmName) {
        this.mmName = mmName == null ? null : mmName.trim();
    }

    public String getMmMobile() {
        return mmMobile;
    }

    public void setMmMobile(String mmMobile) {
        this.mmMobile = mmMobile == null ? null : mmMobile.trim();
    }

    public String getMmPhone() {
        return mmPhone;
    }

    public void setMmPhone(String mmPhone) {
        this.mmPhone = mmPhone == null ? null : mmPhone.trim();
    }

    public String getMmEamil() {
        return mmEamil;
    }

    public void setMmEamil(String mmEamil) {
        this.mmEamil = mmEamil == null ? null : mmEamil.trim();
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName == null ? null : saleName.trim();
    }

    public String getSaleMobile() {
        return saleMobile;
    }

    public void setSaleMobile(String saleMobile) {
        this.saleMobile = saleMobile == null ? null : saleMobile.trim();
    }

    public String getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(String salePhone) {
        this.salePhone = salePhone == null ? null : salePhone.trim();
    }

    public String getSaleEmail() {
        return saleEmail;
    }

    public void setSaleEmail(String saleEmail) {
        this.saleEmail = saleEmail == null ? null : saleEmail.trim();
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName == null ? null : trainName.trim();
    }

    public String getTrainMobile() {
        return trainMobile;
    }

    public void setTrainMobile(String trainMobile) {
        this.trainMobile = trainMobile == null ? null : trainMobile.trim();
    }

    public String getTrainPhone() {
        return trainPhone;
    }

    public void setTrainPhone(String trainPhone) {
        this.trainPhone = trainPhone == null ? null : trainPhone.trim();
    }

    public String getTrainEmail() {
        return trainEmail;
    }

    public void setTrainEmail(String trainEmail) {
        this.trainEmail = trainEmail == null ? null : trainEmail.trim();
    }

    public Date getClcType() {
        return clcType;
    }

    public void setClcType(Date clcType) {
        this.clcType = clcType;
    }

    public String getServerInvoiceName() {
        return serverInvoiceName;
    }

    public void setServerInvoiceName(String serverInvoiceName) {
        this.serverInvoiceName = serverInvoiceName == null ? null : serverInvoiceName.trim();
    }

    public String getServerInvoiceType() {
        return serverInvoiceType;
    }

    public void setServerInvoiceType(String serverInvoiceType) {
        this.serverInvoiceType = serverInvoiceType == null ? null : serverInvoiceType.trim();
    }

    public String getDealerEmail() {
        return dealerEmail;
    }

    public void setDealerEmail(String dealerEmail) {
        this.dealerEmail = dealerEmail == null ? null : dealerEmail.trim();
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1 == null ? null : remarks1.trim();
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate == null ? null : interestRate.trim();
    }

    public String getProvinceRate() {
        return provinceRate;
    }

    public void setProvinceRate(String provinceRate) {
        this.provinceRate = provinceRate == null ? null : provinceRate.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea == null ? null : saleArea.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRecAccountBank2() {
        return recAccountBank2;
    }

    public void setRecAccountBank2(String recAccountBank2) {
        this.recAccountBank2 = recAccountBank2 == null ? null : recAccountBank2.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getBankMobile() {
        return bankMobile;
    }

    public void setBankMobile(String bankMobile) {
        this.bankMobile = bankMobile == null ? null : bankMobile.trim();
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber == null ? null : lineNumber.trim();
    }

    public Byte getIsFraudFlight() {
        return isFraudFlight;
    }

    public void setIsFraudFlight(Byte isFraudFlight) {
        this.isFraudFlight = isFraudFlight;
    }

    public String getBankProvinceRegCode() {
        return bankProvinceRegCode;
    }

    public void setBankProvinceRegCode(String bankProvinceRegCode) {
        this.bankProvinceRegCode = bankProvinceRegCode == null ? null : bankProvinceRegCode.trim();
    }

    public String getBankCityRegCode() {
        return bankCityRegCode;
    }

    public void setBankCityRegCode(String bankCityRegCode) {
        this.bankCityRegCode = bankCityRegCode == null ? null : bankCityRegCode.trim();
    }

    public String getProvinceRegCode() {
        return provinceRegCode;
    }

    public void setProvinceRegCode(String provinceRegCode) {
        this.provinceRegCode = provinceRegCode == null ? null : provinceRegCode.trim();
    }

    public String getCityRegCode() {
        return cityRegCode;
    }

    public void setCityRegCode(String cityRegCode) {
        this.cityRegCode = cityRegCode == null ? null : cityRegCode.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}