package com.mljr.gps.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AppInfo {
    private Integer appNo;

    private String appCode;

    private Integer proppserId;

    private Integer carId;

    private Integer dealerCode;

    private String dealerName;

    private String companyName;

    private String isOld;

    private String isLcv;

    private BigDecimal salePrice;

    private String isGps;

    private BigDecimal gpsFee;

    private String isGpsLoan;

    private BigDecimal aCarloanAmount;

    private BigDecimal aLoanAmount;

    private BigDecimal aInitPayment;

    private BigDecimal aInitScale;

    private Byte aLoanPeriods;

    private BigDecimal aLoanRate;

    private String loanRecord;

    private BigDecimal appScore;

    private BigDecimal rLoanAmount;

    private BigDecimal rInitPayment;

    private BigDecimal rInitScale;

    private Byte rLoanPeriods;

    private BigDecimal rLoanRate;

    private String repAccountBank;

    private String repAccountNo;

    private String repAccountName;

    private Date createTime;

    private Date appTime;

    private Date updateTime;

    private Date loanerApprovalTime;

    private Date manageApprovalTime;

    private Date bloanDataTime;

    private Date bloanApprovalTime;

    private Date aloanDataTime;

    private Date aloanApprovalTime;

    private Date loanTime;

    private String aloanArrive;

    private String cancelType;

    private String cancelReason;

    private String status;

    private String remarks;

    private String isAssure;

    private Integer companyCode;

    private Integer userId;

    private String userName;

    private Integer flowSeq;

    private String isDeferMortgage;

    private Integer productCode;

    private String productName;

    private BigDecimal rGpsFee;

    private BigDecimal aSecureFee;

    private BigDecimal rSecureFee;

    private String isPrintW;

    private String isHouse;

    private String loanRemarks;

    private String bloanRemarks;

    private String sysaRemarks;

    private String yyRiskRemarks;

    private String yyPreRiskRemarks;

    private Date sysaDate;

    private String sysaStatus;

    private String sysaResult;

    private String refuseStatus;

    private String isPrepay;

    private Date prepayDate;

    private BigDecimal extraLoanAmount;

    private String paymentCh;

    private Date dateCh;

    private String paymentChZn;

    private BigDecimal discountE;

    private BigDecimal disaRate;

    private BigDecimal discountTrueE;

    private BigDecimal rDiscountTrueE;

    private String fyRemarks;

    private String loanStatus;

    private String isCreditReport;

    private String modelIds;

    private BigDecimal aComRate;

    private BigDecimal aComFee;

    private BigDecimal aYanbaoFee;

    private BigDecimal aYanbaoFee3;

    private Integer aYanbaoTc;

    private Integer aYanbaoTc3;

    private String aYanbaoTcName;

    private String aYanbaoTcName3;

    private BigDecimal aLifeInsurance;

    private BigDecimal rLifeInsurance;

    private String lifeInsuranceRule;

    private BigDecimal lifeInsuranceRate;

    private BigDecimal rComFee;

    private BigDecimal rYanbaoFee;

    private BigDecimal rYanbaoFee3;

    private String chApprovalStatus;

    private String pledModelId;

    private String authCh;

    private String paymentChEn;

    private String fileNumber;

    private String postLoanStatus;

    private String loanAfterRemarks;

    private String loanAfterCondition;

    private String isCredit;

    private String rateLevel;

    private String rRateLevel;

    private BigDecimal accountFee;

    private BigDecimal rAccountFee;

    private BigDecimal rCarloanAmount;

    private String loanFileStatus;

    private String belongDealer;

    private String belongSale;

    private String belongSaleManager;

    private String belongOperation;

    private String belongMarket;

    private String repAccountIdno;

    private String isCheckAccount;

    private String gpsLevel;

    private Date gpsConfirmDate;

    private BigDecimal gpsRebate;

    private BigDecimal serFinRebate;

    private String comeFrom;

    private String belongSaleTel;

    private String isPreInterest;

    private BigDecimal rGrossInterest;

    private BigDecimal grossInterest;

    private BigDecimal extendCost;

    private BigDecimal rExtendCost;

    private String extendId;

    private String extendName;

    private BigDecimal preLoanRate;

    private String autoAppr;

    private Date autoApprTime;

    private Date firstSubmit;

    private String workflowQueue;

    private String batchPayFlag;

    private String sendAppr;

    private String yyApprovalResult;

    private String isCertAll;

    private String isAuthYh;

    private String loanAfterYuanRemarks;

    private String isVipContract;

    private Byte isQuickAudit;

    private String assign;

    private String repAccountMobile;

    private Boolean isBack;

    private Boolean isEndHt;

    private Boolean htSource;

    private Boolean isEndContract;

    private Boolean isCompanyLicense;

    private BigDecimal icbcAssessmentPrices;

    private BigDecimal notarizationFee;

    private Boolean quickAuditSign;

    private String repAccountLineNum;

    private String repAccountCheckWays;

    private BigDecimal rBusinessInsurance;

    private BigDecimal rTrafficInsurance;

    private BigDecimal rTaxPrice;

    private String webankno;

    private String belongSubSale;

    private String shiftFileNumber;

    private Integer pufaBackType;

    private List<String> appCodeList;

    public Integer getAppNo() {
        return appNo;
    }

    public void setAppNo(Integer appNo) {
        this.appNo = appNo;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Integer getProppserId() {
        return proppserId;
    }

    public void setProppserId(Integer proppserId) {
        this.proppserId = proppserId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getIsOld() {
        return isOld;
    }

    public void setIsOld(String isOld) {
        this.isOld = isOld == null ? null : isOld.trim();
    }

    public String getIsLcv() {
        return isLcv;
    }

    public void setIsLcv(String isLcv) {
        this.isLcv = isLcv == null ? null : isLcv.trim();
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getIsGps() {
        return isGps;
    }

    public void setIsGps(String isGps) {
        this.isGps = isGps == null ? null : isGps.trim();
    }

    public BigDecimal getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(BigDecimal gpsFee) {
        this.gpsFee = gpsFee;
    }

    public String getIsGpsLoan() {
        return isGpsLoan;
    }

    public void setIsGpsLoan(String isGpsLoan) {
        this.isGpsLoan = isGpsLoan == null ? null : isGpsLoan.trim();
    }

    public BigDecimal getaCarloanAmount() {
        return aCarloanAmount;
    }

    public void setaCarloanAmount(BigDecimal aCarloanAmount) {
        this.aCarloanAmount = aCarloanAmount;
    }

    public BigDecimal getaLoanAmount() {
        return aLoanAmount;
    }

    public void setaLoanAmount(BigDecimal aLoanAmount) {
        this.aLoanAmount = aLoanAmount;
    }

    public BigDecimal getaInitPayment() {
        return aInitPayment;
    }

    public void setaInitPayment(BigDecimal aInitPayment) {
        this.aInitPayment = aInitPayment;
    }

    public BigDecimal getaInitScale() {
        return aInitScale;
    }

    public void setaInitScale(BigDecimal aInitScale) {
        this.aInitScale = aInitScale;
    }

    public Byte getaLoanPeriods() {
        return aLoanPeriods;
    }

    public void setaLoanPeriods(Byte aLoanPeriods) {
        this.aLoanPeriods = aLoanPeriods;
    }

    public BigDecimal getaLoanRate() {
        return aLoanRate;
    }

    public void setaLoanRate(BigDecimal aLoanRate) {
        this.aLoanRate = aLoanRate;
    }

    public String getLoanRecord() {
        return loanRecord;
    }

    public void setLoanRecord(String loanRecord) {
        this.loanRecord = loanRecord == null ? null : loanRecord.trim();
    }

    public BigDecimal getAppScore() {
        return appScore;
    }

    public void setAppScore(BigDecimal appScore) {
        this.appScore = appScore;
    }

    public BigDecimal getrLoanAmount() {
        return rLoanAmount;
    }

    public void setrLoanAmount(BigDecimal rLoanAmount) {
        this.rLoanAmount = rLoanAmount;
    }

    public BigDecimal getrInitPayment() {
        return rInitPayment;
    }

    public void setrInitPayment(BigDecimal rInitPayment) {
        this.rInitPayment = rInitPayment;
    }

    public BigDecimal getrInitScale() {
        return rInitScale;
    }

    public void setrInitScale(BigDecimal rInitScale) {
        this.rInitScale = rInitScale;
    }

    public Byte getrLoanPeriods() {
        return rLoanPeriods;
    }

    public void setrLoanPeriods(Byte rLoanPeriods) {
        this.rLoanPeriods = rLoanPeriods;
    }

    public BigDecimal getrLoanRate() {
        return rLoanRate;
    }

    public void setrLoanRate(BigDecimal rLoanRate) {
        this.rLoanRate = rLoanRate;
    }

    public String getRepAccountBank() {
        return repAccountBank;
    }

    public void setRepAccountBank(String repAccountBank) {
        this.repAccountBank = repAccountBank == null ? null : repAccountBank.trim();
    }

    public String getRepAccountNo() {
        return repAccountNo;
    }

    public void setRepAccountNo(String repAccountNo) {
        this.repAccountNo = repAccountNo == null ? null : repAccountNo.trim();
    }

    public String getRepAccountName() {
        return repAccountName;
    }

    public void setRepAccountName(String repAccountName) {
        this.repAccountName = repAccountName == null ? null : repAccountName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLoanerApprovalTime() {
        return loanerApprovalTime;
    }

    public void setLoanerApprovalTime(Date loanerApprovalTime) {
        this.loanerApprovalTime = loanerApprovalTime;
    }

    public Date getManageApprovalTime() {
        return manageApprovalTime;
    }

    public void setManageApprovalTime(Date manageApprovalTime) {
        this.manageApprovalTime = manageApprovalTime;
    }

    public Date getBloanDataTime() {
        return bloanDataTime;
    }

    public void setBloanDataTime(Date bloanDataTime) {
        this.bloanDataTime = bloanDataTime;
    }

    public Date getBloanApprovalTime() {
        return bloanApprovalTime;
    }

    public void setBloanApprovalTime(Date bloanApprovalTime) {
        this.bloanApprovalTime = bloanApprovalTime;
    }

    public Date getAloanDataTime() {
        return aloanDataTime;
    }

    public void setAloanDataTime(Date aloanDataTime) {
        this.aloanDataTime = aloanDataTime;
    }

    public Date getAloanApprovalTime() {
        return aloanApprovalTime;
    }

    public void setAloanApprovalTime(Date aloanApprovalTime) {
        this.aloanApprovalTime = aloanApprovalTime;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public String getAloanArrive() {
        return aloanArrive;
    }

    public void setAloanArrive(String aloanArrive) {
        this.aloanArrive = aloanArrive == null ? null : aloanArrive.trim();
    }

    public String getCancelType() {
        return cancelType;
    }

    public void setCancelType(String cancelType) {
        this.cancelType = cancelType == null ? null : cancelType.trim();
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getIsAssure() {
        return isAssure;
    }

    public void setIsAssure(String isAssure) {
        this.isAssure = isAssure == null ? null : isAssure.trim();
    }

    public Integer getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(Integer companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getFlowSeq() {
        return flowSeq;
    }

    public void setFlowSeq(Integer flowSeq) {
        this.flowSeq = flowSeq;
    }

    public String getIsDeferMortgage() {
        return isDeferMortgage;
    }

    public void setIsDeferMortgage(String isDeferMortgage) {
        this.isDeferMortgage = isDeferMortgage == null ? null : isDeferMortgage.trim();
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getrGpsFee() {
        return rGpsFee;
    }

    public void setrGpsFee(BigDecimal rGpsFee) {
        this.rGpsFee = rGpsFee;
    }

    public BigDecimal getaSecureFee() {
        return aSecureFee;
    }

    public void setaSecureFee(BigDecimal aSecureFee) {
        this.aSecureFee = aSecureFee;
    }

    public BigDecimal getrSecureFee() {
        return rSecureFee;
    }

    public void setrSecureFee(BigDecimal rSecureFee) {
        this.rSecureFee = rSecureFee;
    }

    public String getIsPrintW() {
        return isPrintW;
    }

    public void setIsPrintW(String isPrintW) {
        this.isPrintW = isPrintW == null ? null : isPrintW.trim();
    }

    public String getIsHouse() {
        return isHouse;
    }

    public void setIsHouse(String isHouse) {
        this.isHouse = isHouse == null ? null : isHouse.trim();
    }

    public String getLoanRemarks() {
        return loanRemarks;
    }

    public void setLoanRemarks(String loanRemarks) {
        this.loanRemarks = loanRemarks == null ? null : loanRemarks.trim();
    }

    public String getBloanRemarks() {
        return bloanRemarks;
    }

    public void setBloanRemarks(String bloanRemarks) {
        this.bloanRemarks = bloanRemarks == null ? null : bloanRemarks.trim();
    }

    public String getSysaRemarks() {
        return sysaRemarks;
    }

    public void setSysaRemarks(String sysaRemarks) {
        this.sysaRemarks = sysaRemarks == null ? null : sysaRemarks.trim();
    }

    public String getYyRiskRemarks() {
        return yyRiskRemarks;
    }

    public void setYyRiskRemarks(String yyRiskRemarks) {
        this.yyRiskRemarks = yyRiskRemarks == null ? null : yyRiskRemarks.trim();
    }

    public String getYyPreRiskRemarks() {
        return yyPreRiskRemarks;
    }

    public void setYyPreRiskRemarks(String yyPreRiskRemarks) {
        this.yyPreRiskRemarks = yyPreRiskRemarks == null ? null : yyPreRiskRemarks.trim();
    }

    public Date getSysaDate() {
        return sysaDate;
    }

    public void setSysaDate(Date sysaDate) {
        this.sysaDate = sysaDate;
    }

    public String getSysaStatus() {
        return sysaStatus;
    }

    public void setSysaStatus(String sysaStatus) {
        this.sysaStatus = sysaStatus == null ? null : sysaStatus.trim();
    }

    public String getSysaResult() {
        return sysaResult;
    }

    public void setSysaResult(String sysaResult) {
        this.sysaResult = sysaResult == null ? null : sysaResult.trim();
    }

    public String getRefuseStatus() {
        return refuseStatus;
    }

    public void setRefuseStatus(String refuseStatus) {
        this.refuseStatus = refuseStatus == null ? null : refuseStatus.trim();
    }

    public String getIsPrepay() {
        return isPrepay;
    }

    public void setIsPrepay(String isPrepay) {
        this.isPrepay = isPrepay == null ? null : isPrepay.trim();
    }

    public Date getPrepayDate() {
        return prepayDate;
    }

    public void setPrepayDate(Date prepayDate) {
        this.prepayDate = prepayDate;
    }

    public BigDecimal getExtraLoanAmount() {
        return extraLoanAmount;
    }

    public void setExtraLoanAmount(BigDecimal extraLoanAmount) {
        this.extraLoanAmount = extraLoanAmount;
    }

    public String getPaymentCh() {
        return paymentCh;
    }

    public void setPaymentCh(String paymentCh) {
        this.paymentCh = paymentCh == null ? null : paymentCh.trim();
    }

    public Date getDateCh() {
        return dateCh;
    }

    public void setDateCh(Date dateCh) {
        this.dateCh = dateCh;
    }

    public String getPaymentChZn() {
        return paymentChZn;
    }

    public void setPaymentChZn(String paymentChZn) {
        this.paymentChZn = paymentChZn == null ? null : paymentChZn.trim();
    }

    public BigDecimal getDiscountE() {
        return discountE;
    }

    public void setDiscountE(BigDecimal discountE) {
        this.discountE = discountE;
    }

    public BigDecimal getDisaRate() {
        return disaRate;
    }

    public void setDisaRate(BigDecimal disaRate) {
        this.disaRate = disaRate;
    }

    public BigDecimal getDiscountTrueE() {
        return discountTrueE;
    }

    public void setDiscountTrueE(BigDecimal discountTrueE) {
        this.discountTrueE = discountTrueE;
    }

    public BigDecimal getrDiscountTrueE() {
        return rDiscountTrueE;
    }

    public void setrDiscountTrueE(BigDecimal rDiscountTrueE) {
        this.rDiscountTrueE = rDiscountTrueE;
    }

    public String getFyRemarks() {
        return fyRemarks;
    }

    public void setFyRemarks(String fyRemarks) {
        this.fyRemarks = fyRemarks == null ? null : fyRemarks.trim();
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus == null ? null : loanStatus.trim();
    }

    public String getIsCreditReport() {
        return isCreditReport;
    }

    public void setIsCreditReport(String isCreditReport) {
        this.isCreditReport = isCreditReport == null ? null : isCreditReport.trim();
    }

    public String getModelIds() {
        return modelIds;
    }

    public void setModelIds(String modelIds) {
        this.modelIds = modelIds == null ? null : modelIds.trim();
    }

    public BigDecimal getaComRate() {
        return aComRate;
    }

    public void setaComRate(BigDecimal aComRate) {
        this.aComRate = aComRate;
    }

    public BigDecimal getaComFee() {
        return aComFee;
    }

    public void setaComFee(BigDecimal aComFee) {
        this.aComFee = aComFee;
    }

    public BigDecimal getaYanbaoFee() {
        return aYanbaoFee;
    }

    public void setaYanbaoFee(BigDecimal aYanbaoFee) {
        this.aYanbaoFee = aYanbaoFee;
    }

    public BigDecimal getaYanbaoFee3() {
        return aYanbaoFee3;
    }

    public void setaYanbaoFee3(BigDecimal aYanbaoFee3) {
        this.aYanbaoFee3 = aYanbaoFee3;
    }

    public Integer getaYanbaoTc() {
        return aYanbaoTc;
    }

    public void setaYanbaoTc(Integer aYanbaoTc) {
        this.aYanbaoTc = aYanbaoTc;
    }

    public Integer getaYanbaoTc3() {
        return aYanbaoTc3;
    }

    public void setaYanbaoTc3(Integer aYanbaoTc3) {
        this.aYanbaoTc3 = aYanbaoTc3;
    }

    public String getaYanbaoTcName() {
        return aYanbaoTcName;
    }

    public void setaYanbaoTcName(String aYanbaoTcName) {
        this.aYanbaoTcName = aYanbaoTcName == null ? null : aYanbaoTcName.trim();
    }

    public String getaYanbaoTcName3() {
        return aYanbaoTcName3;
    }

    public void setaYanbaoTcName3(String aYanbaoTcName3) {
        this.aYanbaoTcName3 = aYanbaoTcName3 == null ? null : aYanbaoTcName3.trim();
    }

    public BigDecimal getaLifeInsurance() {
        return aLifeInsurance;
    }

    public void setaLifeInsurance(BigDecimal aLifeInsurance) {
        this.aLifeInsurance = aLifeInsurance;
    }

    public BigDecimal getrLifeInsurance() {
        return rLifeInsurance;
    }

    public void setrLifeInsurance(BigDecimal rLifeInsurance) {
        this.rLifeInsurance = rLifeInsurance;
    }

    public String getLifeInsuranceRule() {
        return lifeInsuranceRule;
    }

    public void setLifeInsuranceRule(String lifeInsuranceRule) {
        this.lifeInsuranceRule = lifeInsuranceRule == null ? null : lifeInsuranceRule.trim();
    }

    public BigDecimal getLifeInsuranceRate() {
        return lifeInsuranceRate;
    }

    public void setLifeInsuranceRate(BigDecimal lifeInsuranceRate) {
        this.lifeInsuranceRate = lifeInsuranceRate;
    }

    public BigDecimal getrComFee() {
        return rComFee;
    }

    public void setrComFee(BigDecimal rComFee) {
        this.rComFee = rComFee;
    }

    public BigDecimal getrYanbaoFee() {
        return rYanbaoFee;
    }

    public void setrYanbaoFee(BigDecimal rYanbaoFee) {
        this.rYanbaoFee = rYanbaoFee;
    }

    public BigDecimal getrYanbaoFee3() {
        return rYanbaoFee3;
    }

    public void setrYanbaoFee3(BigDecimal rYanbaoFee3) {
        this.rYanbaoFee3 = rYanbaoFee3;
    }

    public String getChApprovalStatus() {
        return chApprovalStatus;
    }

    public void setChApprovalStatus(String chApprovalStatus) {
        this.chApprovalStatus = chApprovalStatus == null ? null : chApprovalStatus.trim();
    }

    public String getPledModelId() {
        return pledModelId;
    }

    public void setPledModelId(String pledModelId) {
        this.pledModelId = pledModelId == null ? null : pledModelId.trim();
    }

    public String getAuthCh() {
        return authCh;
    }

    public void setAuthCh(String authCh) {
        this.authCh = authCh == null ? null : authCh.trim();
    }

    public String getPaymentChEn() {
        return paymentChEn;
    }

    public void setPaymentChEn(String paymentChEn) {
        this.paymentChEn = paymentChEn == null ? null : paymentChEn.trim();
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber == null ? null : fileNumber.trim();
    }

    public String getPostLoanStatus() {
        return postLoanStatus;
    }

    public void setPostLoanStatus(String postLoanStatus) {
        this.postLoanStatus = postLoanStatus == null ? null : postLoanStatus.trim();
    }

    public String getLoanAfterRemarks() {
        return loanAfterRemarks;
    }

    public void setLoanAfterRemarks(String loanAfterRemarks) {
        this.loanAfterRemarks = loanAfterRemarks == null ? null : loanAfterRemarks.trim();
    }

    public String getLoanAfterCondition() {
        return loanAfterCondition;
    }

    public void setLoanAfterCondition(String loanAfterCondition) {
        this.loanAfterCondition = loanAfterCondition == null ? null : loanAfterCondition.trim();
    }

    public String getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit == null ? null : isCredit.trim();
    }

    public String getRateLevel() {
        return rateLevel;
    }

    public void setRateLevel(String rateLevel) {
        this.rateLevel = rateLevel == null ? null : rateLevel.trim();
    }

    public String getrRateLevel() {
        return rRateLevel;
    }

    public void setrRateLevel(String rRateLevel) {
        this.rRateLevel = rRateLevel == null ? null : rRateLevel.trim();
    }

    public BigDecimal getAccountFee() {
        return accountFee;
    }

    public void setAccountFee(BigDecimal accountFee) {
        this.accountFee = accountFee;
    }

    public BigDecimal getrAccountFee() {
        return rAccountFee;
    }

    public void setrAccountFee(BigDecimal rAccountFee) {
        this.rAccountFee = rAccountFee;
    }

    public BigDecimal getrCarloanAmount() {
        return rCarloanAmount;
    }

    public void setrCarloanAmount(BigDecimal rCarloanAmount) {
        this.rCarloanAmount = rCarloanAmount;
    }

    public String getLoanFileStatus() {
        return loanFileStatus;
    }

    public void setLoanFileStatus(String loanFileStatus) {
        this.loanFileStatus = loanFileStatus == null ? null : loanFileStatus.trim();
    }

    public String getBelongDealer() {
        return belongDealer;
    }

    public void setBelongDealer(String belongDealer) {
        this.belongDealer = belongDealer == null ? null : belongDealer.trim();
    }

    public String getBelongSale() {
        return belongSale;
    }

    public void setBelongSale(String belongSale) {
        this.belongSale = belongSale == null ? null : belongSale.trim();
    }

    public String getBelongSaleManager() {
        return belongSaleManager;
    }

    public void setBelongSaleManager(String belongSaleManager) {
        this.belongSaleManager = belongSaleManager == null ? null : belongSaleManager.trim();
    }

    public String getBelongOperation() {
        return belongOperation;
    }

    public void setBelongOperation(String belongOperation) {
        this.belongOperation = belongOperation == null ? null : belongOperation.trim();
    }

    public String getBelongMarket() {
        return belongMarket;
    }

    public void setBelongMarket(String belongMarket) {
        this.belongMarket = belongMarket == null ? null : belongMarket.trim();
    }

    public String getRepAccountIdno() {
        return repAccountIdno;
    }

    public void setRepAccountIdno(String repAccountIdno) {
        this.repAccountIdno = repAccountIdno == null ? null : repAccountIdno.trim();
    }

    public String getIsCheckAccount() {
        return isCheckAccount;
    }

    public void setIsCheckAccount(String isCheckAccount) {
        this.isCheckAccount = isCheckAccount == null ? null : isCheckAccount.trim();
    }

    public String getGpsLevel() {
        return gpsLevel;
    }

    public void setGpsLevel(String gpsLevel) {
        this.gpsLevel = gpsLevel == null ? null : gpsLevel.trim();
    }

    public Date getGpsConfirmDate() {
        return gpsConfirmDate;
    }

    public void setGpsConfirmDate(Date gpsConfirmDate) {
        this.gpsConfirmDate = gpsConfirmDate;
    }

    public BigDecimal getGpsRebate() {
        return gpsRebate;
    }

    public void setGpsRebate(BigDecimal gpsRebate) {
        this.gpsRebate = gpsRebate;
    }

    public BigDecimal getSerFinRebate() {
        return serFinRebate;
    }

    public void setSerFinRebate(BigDecimal serFinRebate) {
        this.serFinRebate = serFinRebate;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom == null ? null : comeFrom.trim();
    }

    public String getBelongSaleTel() {
        return belongSaleTel;
    }

    public void setBelongSaleTel(String belongSaleTel) {
        this.belongSaleTel = belongSaleTel == null ? null : belongSaleTel.trim();
    }

    public String getIsPreInterest() {
        return isPreInterest;
    }

    public void setIsPreInterest(String isPreInterest) {
        this.isPreInterest = isPreInterest == null ? null : isPreInterest.trim();
    }

    public BigDecimal getrGrossInterest() {
        return rGrossInterest;
    }

    public void setrGrossInterest(BigDecimal rGrossInterest) {
        this.rGrossInterest = rGrossInterest;
    }

    public BigDecimal getGrossInterest() {
        return grossInterest;
    }

    public void setGrossInterest(BigDecimal grossInterest) {
        this.grossInterest = grossInterest;
    }

    public BigDecimal getExtendCost() {
        return extendCost;
    }

    public void setExtendCost(BigDecimal extendCost) {
        this.extendCost = extendCost;
    }

    public BigDecimal getrExtendCost() {
        return rExtendCost;
    }

    public void setrExtendCost(BigDecimal rExtendCost) {
        this.rExtendCost = rExtendCost;
    }

    public String getExtendId() {
        return extendId;
    }

    public void setExtendId(String extendId) {
        this.extendId = extendId == null ? null : extendId.trim();
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName == null ? null : extendName.trim();
    }

    public BigDecimal getPreLoanRate() {
        return preLoanRate;
    }

    public void setPreLoanRate(BigDecimal preLoanRate) {
        this.preLoanRate = preLoanRate;
    }

    public String getAutoAppr() {
        return autoAppr;
    }

    public void setAutoAppr(String autoAppr) {
        this.autoAppr = autoAppr == null ? null : autoAppr.trim();
    }

    public Date getAutoApprTime() {
        return autoApprTime;
    }

    public void setAutoApprTime(Date autoApprTime) {
        this.autoApprTime = autoApprTime;
    }

    public Date getFirstSubmit() {
        return firstSubmit;
    }

    public void setFirstSubmit(Date firstSubmit) {
        this.firstSubmit = firstSubmit;
    }

    public String getWorkflowQueue() {
        return workflowQueue;
    }

    public void setWorkflowQueue(String workflowQueue) {
        this.workflowQueue = workflowQueue == null ? null : workflowQueue.trim();
    }

    public String getBatchPayFlag() {
        return batchPayFlag;
    }

    public void setBatchPayFlag(String batchPayFlag) {
        this.batchPayFlag = batchPayFlag == null ? null : batchPayFlag.trim();
    }

    public String getSendAppr() {
        return sendAppr;
    }

    public void setSendAppr(String sendAppr) {
        this.sendAppr = sendAppr == null ? null : sendAppr.trim();
    }

    public String getYyApprovalResult() {
        return yyApprovalResult;
    }

    public void setYyApprovalResult(String yyApprovalResult) {
        this.yyApprovalResult = yyApprovalResult == null ? null : yyApprovalResult.trim();
    }

    public String getIsCertAll() {
        return isCertAll;
    }

    public void setIsCertAll(String isCertAll) {
        this.isCertAll = isCertAll == null ? null : isCertAll.trim();
    }

    public String getIsAuthYh() {
        return isAuthYh;
    }

    public void setIsAuthYh(String isAuthYh) {
        this.isAuthYh = isAuthYh == null ? null : isAuthYh.trim();
    }

    public String getLoanAfterYuanRemarks() {
        return loanAfterYuanRemarks;
    }

    public void setLoanAfterYuanRemarks(String loanAfterYuanRemarks) {
        this.loanAfterYuanRemarks = loanAfterYuanRemarks == null ? null : loanAfterYuanRemarks.trim();
    }

    public String getIsVipContract() {
        return isVipContract;
    }

    public void setIsVipContract(String isVipContract) {
        this.isVipContract = isVipContract == null ? null : isVipContract.trim();
    }

    public Byte getIsQuickAudit() {
        return isQuickAudit;
    }

    public void setIsQuickAudit(Byte isQuickAudit) {
        this.isQuickAudit = isQuickAudit;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign == null ? null : assign.trim();
    }

    public String getRepAccountMobile() {
        return repAccountMobile;
    }

    public void setRepAccountMobile(String repAccountMobile) {
        this.repAccountMobile = repAccountMobile == null ? null : repAccountMobile.trim();
    }

    public Boolean getIsBack() {
        return isBack;
    }

    public void setIsBack(Boolean isBack) {
        this.isBack = isBack;
    }

    public Boolean getIsEndHt() {
        return isEndHt;
    }

    public void setIsEndHt(Boolean isEndHt) {
        this.isEndHt = isEndHt;
    }

    public Boolean getHtSource() {
        return htSource;
    }

    public void setHtSource(Boolean htSource) {
        this.htSource = htSource;
    }

    public Boolean getIsEndContract() {
        return isEndContract;
    }

    public void setIsEndContract(Boolean isEndContract) {
        this.isEndContract = isEndContract;
    }

    public Boolean getIsCompanyLicense() {
        return isCompanyLicense;
    }

    public void setIsCompanyLicense(Boolean isCompanyLicense) {
        this.isCompanyLicense = isCompanyLicense;
    }

    public BigDecimal getIcbcAssessmentPrices() {
        return icbcAssessmentPrices;
    }

    public void setIcbcAssessmentPrices(BigDecimal icbcAssessmentPrices) {
        this.icbcAssessmentPrices = icbcAssessmentPrices;
    }

    public BigDecimal getNotarizationFee() {
        return notarizationFee;
    }

    public void setNotarizationFee(BigDecimal notarizationFee) {
        this.notarizationFee = notarizationFee;
    }

    public Boolean getQuickAuditSign() {
        return quickAuditSign;
    }

    public void setQuickAuditSign(Boolean quickAuditSign) {
        this.quickAuditSign = quickAuditSign;
    }

    public String getRepAccountLineNum() {
        return repAccountLineNum;
    }

    public void setRepAccountLineNum(String repAccountLineNum) {
        this.repAccountLineNum = repAccountLineNum == null ? null : repAccountLineNum.trim();
    }

    public String getRepAccountCheckWays() {
        return repAccountCheckWays;
    }

    public void setRepAccountCheckWays(String repAccountCheckWays) {
        this.repAccountCheckWays = repAccountCheckWays == null ? null : repAccountCheckWays.trim();
    }

    public BigDecimal getrBusinessInsurance() {
        return rBusinessInsurance;
    }

    public void setrBusinessInsurance(BigDecimal rBusinessInsurance) {
        this.rBusinessInsurance = rBusinessInsurance;
    }

    public BigDecimal getrTrafficInsurance() {
        return rTrafficInsurance;
    }

    public void setrTrafficInsurance(BigDecimal rTrafficInsurance) {
        this.rTrafficInsurance = rTrafficInsurance;
    }

    public BigDecimal getrTaxPrice() {
        return rTaxPrice;
    }

    public void setrTaxPrice(BigDecimal rTaxPrice) {
        this.rTaxPrice = rTaxPrice;
    }

    public String getWebankno() {
        return webankno;
    }

    public void setWebankno(String webankno) {
        this.webankno = webankno == null ? null : webankno.trim();
    }

    public String getBelongSubSale() {
        return belongSubSale;
    }

    public void setBelongSubSale(String belongSubSale) {
        this.belongSubSale = belongSubSale == null ? null : belongSubSale.trim();
    }

    public String getShiftFileNumber() {
        return shiftFileNumber;
    }

    public void setShiftFileNumber(String shiftFileNumber) {
        this.shiftFileNumber = shiftFileNumber == null ? null : shiftFileNumber.trim();
    }

    public Integer getPufaBackType() {
        return pufaBackType;
    }

    public void setPufaBackType(Integer pufaBackType) {
        this.pufaBackType = pufaBackType;
    }

    public List<String> getAppCodeList() {
        return appCodeList;
    }

    public void setAppCodeList(List<String> appCodeList) {
        this.appCodeList = appCodeList;
    }
}