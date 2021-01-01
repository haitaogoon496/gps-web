package com.mljr.gps.vo.orderVo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mljr.gps.common.enums.BooEnum;
import com.mljr.gps.common.enums.IdentifyCompareEnum;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

public class CaCarInfoVo {

    private String appCode;
    private Integer carId;
    @ApiModelProperty(name="carBrand",value="品牌",dataType="String")
    private String carBrand;
    @ApiModelProperty(name="carSeries",value="车系",dataType="String")
    private String carSeries;
    @ApiModelProperty(name="carYear",value="车款年代",dataType="String")
    private String carYear;
    @ApiModelProperty(name="carGearbox",value="档位",dataType="String")
    private String carGearbox;
    @ApiModelProperty(name="carDiston",value="排量/吨位",dataType="String")
    private String carDiston;
    @ApiModelProperty(name="carStyles",value="车款",dataType="String")
    private String carStyles;
    @ApiModelProperty(name="carNewPrice",value="新车参考价",dataType="BigDecimal")
    private BigDecimal carNewPrice;
    @ApiModelProperty(name="carIdentify",value="车辆识别代号",dataType="String")
    private String carIdentify;
    @ApiModelProperty(name="carColor",value="车辆颜色",dataType="String")
    private String carColor;
    @ApiModelProperty(name="fuelType",value="燃料类型",dataType="String")
    private String fuelType;
    @ApiModelProperty(name="isOld",value="是否二手车",dataType="String")
    private String isOld;
    @ApiModelProperty(name="isOldStr",value="是否二手车Str",dataType="String")
    private String isOldStr;
    @ApiModelProperty(name="carMiles",value="二手车里程数(公里)",dataType="String")
    private Integer carMiles;
    @ApiModelProperty(name="carFirstBook",value="二手车首次登记日期",dataType="Date")
    private Date carFirstBook;
    @ApiModelProperty(name="carSaleName",value="二手车卖方姓名",dataType="String")
    private String carSaleName;
    @ApiModelProperty(name="carSaleIdno",value="二手车卖方身份证号码",dataType="String")
    private String carSaleIdno;
    @ApiModelProperty(name="aCarCondtion",value="二手车车况",dataType="String")
    @JsonProperty(value = "aCarCondtion")
    private String aCarCondtion;
    @ApiModelProperty(name="aCarPrice",value="二手车评估价",dataType="String")
    @JsonProperty(value = "aCarPrice")
    private Double aCarPrice;
    @ApiModelProperty(name="floatingPro",value="二手车车价上浮比例",dataType="String")
    private String floatingPro;
    @ApiModelProperty(name="aCarPriceChange",value="二手车调整价",dataType="String")
    @JsonProperty(value = "aCarPriceChange")
    private String aCarPriceChange;
    private Double openFare;
    @ApiModelProperty(name="vinIdentifyChe300",value="VIN码解析车型",dataType="String")
    private String vinIdentifyChe300;
    @ApiModelProperty(name="transferCnt",value="过户次数",dataType="Integer")
    private Integer transferCnt;
    @ApiModelProperty(name="transCity",value="交易城市",dataType="String")
    private String transCity;
    @ApiModelProperty(name="isMatchChe300",value="与车300车型是否一致",dataType="String")
    private String isMatchChe300;
    @ApiModelProperty(name="isMatchChe300Str",value="与车300车型是否一致Str",dataType="String")
    private String isMatchChe300Str;
    @ApiModelProperty(name="isCompanyLicenseStr",value="车牌类型Str",dataType="String")
    private String isCompanyLicenseStr;
    @ApiModelProperty(name="isCompanyLicense",value="车牌类型",dataType="String")
    private String isCompanyLicense;
    private String reportUrl;
    @ApiModelProperty(name="transCityId",value="交易城市Id",dataType="Integer")
    private Integer transCityId;
    @ApiModelProperty(name="vinIdentifyChe300Id",value="VIN码解析车型Id",dataType="Integer")
    private Integer vinIdentifyChe300Id;
    private Double businessInsurance;
    @ApiModelProperty(name="fuelTypeStr",value="燃料类型Str",dataType="String")
    private String fuelTypeStr;
    @ApiModelProperty(name="oldCarAge",value="二手车车龄(月)页面计算出来的",dataType="Integer")
    private Integer oldCarAge;
    @ApiModelProperty(name="icbcAssessmentPrices",value="工行车辆评估价",dataType="BigDecimal")
    private BigDecimal icbcAssessmentPrices;
    @ApiModelProperty(name="manuallyEnterCarStyles",value="手动输入款式",dataType="BigDecimal")
    private String manuallyEnterCarStyles;
    @ApiModelProperty(name="evaluationPrice",value="人工评估价",dataType="BigDecimal")
    private BigDecimal evaluationPrice;
    @ApiModelProperty(name="evaluationTime",value="评估时间",dataType="Date")
    private Date evaluationTime;

    public void setFuelTypeStr(String fuelTypeStr) {
        this.fuelTypeStr = fuelTypeStr;
    }
    public String getIsOldStr() {
        String isOldStr="";
        if(StringUtils.isNotEmpty(this.isOld)){
            isOldStr = BooEnum.getNameByValue(Integer.valueOf(this.isOld));
        }
        return isOldStr;
    }

    public String getIsMatchChe300Str() {
        String isMatchChe300Str="";
        if(StringUtils.isNotEmpty(this.isMatchChe300)){
            isMatchChe300Str = IdentifyCompareEnum.getNameByValue(this.isMatchChe300);
        }
        return isMatchChe300Str;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public String getCarYear() {
        return carYear;
    }

    public void setCarYear(String carYear) {
        this.carYear = carYear;
    }

    public String getCarGearbox() {
        return carGearbox;
    }

    public void setCarGearbox(String carGearbox) {
        this.carGearbox = carGearbox;
    }

    public String getCarDiston() {
        return carDiston;
    }

    public void setCarDiston(String carDiston) {
        this.carDiston = carDiston;
    }

    public String getCarStyles() {
        return carStyles;
    }

    public void setCarStyles(String carStyles) {
        this.carStyles = carStyles;
    }

    public BigDecimal getCarNewPrice() {
        return carNewPrice;
    }

    public void setCarNewPrice(BigDecimal carNewPrice) {
        this.carNewPrice = carNewPrice;
    }

    public String getCarIdentify() {
        return carIdentify;
    }

    public void setCarIdentify(String carIdentify) {
        this.carIdentify = carIdentify;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getIsOld() {
        return isOld;
    }

    public void setIsOld(String isOld) {
        this.isOld = isOld;
    }

    public void setIsOldStr(String isOldStr) {
        this.isOldStr = isOldStr;
    }

    public Integer getCarMiles() {
        return carMiles;
    }

    public void setCarMiles(Integer carMiles) {
        this.carMiles = carMiles;
    }

    public Date getCarFirstBook() {
        return carFirstBook;
    }

    public void setCarFirstBook(Date carFirstBook) {
        this.carFirstBook = carFirstBook;
    }

    public String getCarSaleName() {
        return carSaleName;
    }

    public void setCarSaleName(String carSaleName) {
        this.carSaleName = carSaleName;
    }

    public String getCarSaleIdno() {
        return carSaleIdno;
    }

    public void setCarSaleIdno(String carSaleIdno) {
        this.carSaleIdno = carSaleIdno;
    }

    public String getaCarCondtion() {
        return aCarCondtion;
    }

    public void setaCarCondtion(String aCarCondtion) {
        this.aCarCondtion = aCarCondtion;
    }

    public Double getaCarPrice() {
        return aCarPrice;
    }

    public void setaCarPrice(Double aCarPrice) {
        this.aCarPrice = aCarPrice;
    }

    public String getFloatingPro() {
        return floatingPro;
    }

    public void setFloatingPro(String floatingPro) {
        this.floatingPro = floatingPro;
    }

    public String getaCarPriceChange() {
        return aCarPriceChange;
    }

    public void setaCarPriceChange(String aCarPriceChange) {
        this.aCarPriceChange = aCarPriceChange;
    }

    public Double getOpenFare() {
        return openFare;
    }

    public void setOpenFare(Double openFare) {
        this.openFare = openFare;
    }

    public String getVinIdentifyChe300() {
        return vinIdentifyChe300;
    }

    public void setVinIdentifyChe300(String vinIdentifyChe300) {
        this.vinIdentifyChe300 = vinIdentifyChe300;
    }

    public Integer getTransferCnt() {
        return transferCnt;
    }

    public void setTransferCnt(Integer transferCnt) {
        this.transferCnt = transferCnt;
    }

    public String getTransCity() {
        return transCity;
    }

    public void setTransCity(String transCity) {
        this.transCity = transCity;
    }

    public String getIsMatchChe300() {
        return isMatchChe300;
    }

    public void setIsMatchChe300(String isMatchChe300) {
        this.isMatchChe300 = isMatchChe300;
    }

    public void setIsMatchChe300Str(String isMatchChe300Str) {
        this.isMatchChe300Str = isMatchChe300Str;
    }

    public String getIsCompanyLicenseStr() {
        return isCompanyLicenseStr;
    }

    public void setIsCompanyLicenseStr(String isCompanyLicenseStr) {
        this.isCompanyLicenseStr = isCompanyLicenseStr;
    }

    public String getIsCompanyLicense() {
        return isCompanyLicense;
    }

    public void setIsCompanyLicense(String isCompanyLicense) {
        this.isCompanyLicense = isCompanyLicense;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public Integer getTransCityId() {
        return transCityId;
    }

    public void setTransCityId(Integer transCityId) {
        this.transCityId = transCityId;
    }

    public Integer getVinIdentifyChe300Id() {
        return vinIdentifyChe300Id;
    }

    public void setVinIdentifyChe300Id(Integer vinIdentifyChe300Id) {
        this.vinIdentifyChe300Id = vinIdentifyChe300Id;
    }

    public Double getBusinessInsurance() {
        return businessInsurance;
    }

    public void setBusinessInsurance(Double businessInsurance) {
        this.businessInsurance = businessInsurance;
    }

    public String getFuelTypeStr() {
        return fuelTypeStr;
    }

    public Integer getOldCarAge() {
        return oldCarAge;
    }

    public void setOldCarAge(Integer oldCarAge) {
        this.oldCarAge = oldCarAge;
    }

    public BigDecimal getIcbcAssessmentPrices() {
        return icbcAssessmentPrices;
    }

    public void setIcbcAssessmentPrices(BigDecimal icbcAssessmentPrices) {
        this.icbcAssessmentPrices = icbcAssessmentPrices;
    }

    public String getManuallyEnterCarStyles() {
        return manuallyEnterCarStyles;
    }

    public void setManuallyEnterCarStyles(String manuallyEnterCarStyles) {
        this.manuallyEnterCarStyles = manuallyEnterCarStyles;
    }

    public BigDecimal getEvaluationPrice() {
        return evaluationPrice;
    }

    public void setEvaluationPrice(BigDecimal evaluationPrice) {
        this.evaluationPrice = evaluationPrice;
    }

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }
}
