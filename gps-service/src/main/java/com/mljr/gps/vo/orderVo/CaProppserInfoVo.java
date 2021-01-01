package com.mljr.gps.vo.orderVo;

import com.mljr.gps.common.enums.BooEnum;
import com.mljr.gps.common.enums.CaProppserInfoEnum;
import com.mljr.gps.common.enums.IdTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.sf.oval.constraint.Length;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CaProppserInfoVo implements Serializable {

    @ApiModelProperty(name="appCode",value="单号",dataType="String")
    private String appCode;
    @ApiModelProperty(name="proppserId",value="主贷人id",dataType="Integer")
    private Integer proppserId;
    private String idType;
    @ApiModelProperty(name="idTypeStr",value="证件类型",dataType="String")
    private String idTypeStr;
    @ApiModelProperty(name="idno",value="身份证号",dataType="String")
    private String idno;
    @ApiModelProperty(name="proppserName",value="申请人姓名",dataType="String")
    private String proppserName;
    @ApiModelProperty(name="birth",value="生日",dataType="String")
    private String birth;
    @ApiModelProperty(name="sex",value="性别",dataType="String")
    private String sex;
    @ApiModelProperty(name="marriage",value="是否已婚",dataType="String")
    private String marriage;
    @ApiModelProperty(name="marriageStr",value="是否已婚Str",dataType="String")
    private String marriageStr;
    @ApiModelProperty(name="education",value="学历",dataType="String")
    private String education;
    @ApiModelProperty(name="educationStr",value="学历Str",dataType="String")
    private String educationStr;
    @ApiModelProperty(name="province",value="户籍省",dataType="String")
    private String province;
    private String district;
    @ApiModelProperty(name="city",value="户籍市",dataType="String")
    private String city;
    @ApiModelProperty(name="address",value="户籍地址",dataType="String")
    private String address;
    @ApiModelProperty(name="nowProvince",value="现在居住省份",dataType="String")
    private String nowProvince;
    @ApiModelProperty(name="nowCity",value="现居住城市",dataType="String")
    private String nowCity;
    @ApiModelProperty(name="nowAddress",value="现居住地址",dataType="String")
    private String nowAddress;
    @ApiModelProperty(name="nowPostalcode",value="现居住地址邮编",dataType="String")
    private String nowPostalcode;
    @ApiModelProperty(name="nowTel",value="现居住电话",dataType="String")
    private String nowTel;
    private Byte nowLivingTime;
    @ApiModelProperty(name="nowLivingTimeStr",value="现居住时间",dataType="String")
    private String nowLivingTimeStr;
    @ApiModelProperty(name="firstWorkTime",value="参加工作时间",dataType="Date")
    private Date firstWorkTime;
    @ApiModelProperty(name="nowIndustry",value="从事行业",dataType="String")
    private String nowIndustry;
    @ApiModelProperty(name="nowPosition",value="职位",dataType="String")
    private String nowPosition;
    @ApiModelProperty(name="incomeMonth",value="税后月收入(元)",dataType="BigDecimal")
    private BigDecimal incomeMonth;
    @ApiModelProperty(name="nowCompany",value="现工作单位名称",dataType="String")
    private String nowCompany;
    @ApiModelProperty(name="nowUnitKind",value="现工作单位性质",dataType="String")
    private String nowUnitKind;
    @ApiModelProperty(name="nowUnitProvince",value="现工作单位省",dataType="String")
    private String nowUnitProvince;
    @ApiModelProperty(name="nowUnitCity",value="现工作单位市",dataType="String")
    private String nowUnitCity;
    @ApiModelProperty(name="nowUnitAddress",value="现工作单位地址",dataType="String")
    private String nowUnitAddress;
    @ApiModelProperty(name="nowUnitScale",value="现工作单位规模(人数)",dataType="String")
    private String nowUnitScale;
    @ApiModelProperty(name="nowUnitScaleStr",value="现工作单位规模(人数)",dataType="String")
    private String nowUnitScaleStr;
    @ApiModelProperty(name="nowUnitTel",value="现工作单位电话",dataType="String")
    private String nowUnitTel;
    @ApiModelProperty(name="isDriverLicense",value="是否有驾驶证",dataType="String")
    private String isDriverLicense;
    @ApiModelProperty(name="isDriverLicenseStr",value="是否有驾驶证Str",dataType="String")
    private String isDriverLicenseStr;
    @ApiModelProperty(name="firstDriverNo",value="主贷人驾驶证号码",dataType="String")
    private String firstDriverNo;
    //@Length(min=11,max=11,message="电话号码必须为11位")
    @ApiModelProperty(name="mobile",value="电话号码",dataType="String")
    private String mobile;
    @ApiModelProperty(name="houseOwner",value="住房所有权",dataType="String")
    private String houseOwner;
    @Length(min=11,max=11,message="电话号码必须为11位")
    @ApiModelProperty(name="mobiles",value="电话号码2",dataType="String")
    private String mobiles;
    @ApiModelProperty(name="driverType",value="准驾车型",dataType="String")
    private String driverType;
    @ApiModelProperty(name="driverTypeStr",value="准驾车型Str",dataType="String")
    private String driverTypeStr;
    private String nowWorkYear;
    @ApiModelProperty(name="nowWorkYearStr",value="现工作年限",dataType="String")
    private String nowWorkYearStr;
    @ApiModelProperty(name="bankCardTypeStr",value="征信卡类型",dataType="String")
    private String bankCardTypeStr;
    @ApiModelProperty(name="idCardValidStartDate",value="身份证有效起始日期",dataType="Date")
    private Date idCardValidStartDate;
    @ApiModelProperty(name="idCardValidEndDate",value="身份证有效截止日期",dataType="Date")
    private Date idCardValidEndDate;
    @ApiModelProperty(name="relationDFl",value="驾驶员与主贷人关系",dataType="String")
    private String relationDFl;
    @ApiModelProperty(name="relationDFlStr",value="驾驶员与主贷人关系Str",dataType="String")
    private String relationDFlStr;
    @ApiModelProperty(name="isAssure",value="是否有担保人",dataType="String")
    private String isAssure;
    @ApiModelProperty(name="isAssureStr",value="是否有担保人Str",dataType="String")
    private String isAssureStr;
    @ApiModelProperty(name="gbossTrue",value="国政通是否一致 true一致 false不一致",dataType="Boolean")
    private Boolean gbossTrue;

    public String getSexStr() {
        String sexStr = "";
        if(StringUtils.isNotEmpty(this.sex)){
            sexStr = CaProppserInfoEnum.SexEnum.getNameByVale(this.sex);
        }
        return sexStr;
    }


    public String getIdTypeStr() {
        String idTypeStr="";
        if(StringUtils.isNotEmpty(this.idType)){
            idTypeStr = IdTypeEnum.getNameByValue(this.idType);
        }
        return idTypeStr;
    }

    public String getIsAssure() {
        return isAssure;
    }


    public void setIsAssure(String isAssure) {
        this.isAssure = isAssure == null ? null : isAssure.trim();
    }


    /**
     * 是否已婚
     * @return
     */
    public String getMarriageStr() {
        String marriageStr ="";
        if(StringUtils.isNotEmpty(this.marriage)){
            marriageStr= CaProppserInfoEnum.MarriageTypeEnum.getNameByIndex(Integer.valueOf(this.marriage));
        }
        return marriageStr;
    }


    /**
     * 学历
     * @return
     */
    public String getEducationStr() {
        String educationStr="";
        if(StringUtils.isNotEmpty(this.education)){
            educationStr = CaProppserInfoEnum.EducationEnum.getNameByValue(this.education);
        }
        return educationStr;
    }

    /*    public String getIsCreditStr() {
            String isCreditStr = "";
            if(StringUtils.isNotEmpty(this.isCredit)){
                isCreditStr = IsCreditEnum.getOneName(this.isCredit);
            }
            return isCreditStr;
        }
        */
    public String getIsDriverLicenseStr() {
        String isDriverLicenseStr="";
        if(StringUtils.isNotEmpty(this.isDriverLicense)){
            isDriverLicenseStr = BooEnum.getNameByValue(Integer.valueOf(this.isDriverLicense));
        }
        return isDriverLicenseStr;
    }

    /*   public String getBankCardTypeStr() {
           String bankCardTypeStr = "";
           if(StringUtils.isNotEmpty(this.bankCardType)){
               bankCardTypeStr = BankCardTypeEnum.getNameByValue(this.bankCardType);
           }
           return bankCardTypeStr;
       }

       public String getIsSceneStr() {
           String isSceneStr = "";
           if(StringUtils.isNotEmpty(this.isScene)){
               isSceneStr = BooEnum.getNameByValue(Integer.valueOf(this.isScene));
           }
           return isSceneStr;
       }
   */
    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Integer getProppserId() {
        return proppserId;
    }

    public void setProppserId(Integer proppserId) {
        this.proppserId = proppserId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setIdTypeStr(String idTypeStr) {
        this.idTypeStr = idTypeStr;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getProppserName() {
        return proppserName;
    }

    public void setProppserName(String proppserName) {
        this.proppserName = proppserName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public void setMarriageStr(String marriageStr) {
        this.marriageStr = marriageStr;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setEducationStr(String educationStr) {
        this.educationStr = educationStr;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getNowPostalcode() {
        return nowPostalcode;
    }

    public void setNowPostalcode(String nowPostalcode) {
        this.nowPostalcode = nowPostalcode;
    }

    public String getNowTel() {
        return nowTel;
    }

    public void setNowTel(String nowTel) {
        this.nowTel = nowTel;
    }

    public Byte getNowLivingTime() {
        return nowLivingTime;
    }

    public void setNowLivingTime(Byte nowLivingTime) {
        this.nowLivingTime = nowLivingTime;
    }

    public String getNowLivingTimeStr() {
        return nowLivingTimeStr;
    }

    public void setNowLivingTimeStr(String nowLivingTimeStr) {
        this.nowLivingTimeStr = nowLivingTimeStr;
    }

    public Date getFirstWorkTime() {
        return firstWorkTime;
    }

    public void setFirstWorkTime(Date firstWorkTime) {
        this.firstWorkTime = firstWorkTime;
    }

    public String getNowIndustry() {
        return nowIndustry;
    }

    public void setNowIndustry(String nowIndustry) {
        this.nowIndustry = nowIndustry;
    }

    public String getNowPosition() {
        return nowPosition;
    }

    public void setNowPosition(String nowPosition) {
        this.nowPosition = nowPosition;
    }

    public BigDecimal getIncomeMonth() {
        return incomeMonth;
    }

    public void setIncomeMonth(BigDecimal incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    public String getNowCompany() {
        return nowCompany;
    }

    public void setNowCompany(String nowCompany) {
        this.nowCompany = nowCompany;
    }

    public String getNowUnitKind() {
        return nowUnitKind;
    }

    public void setNowUnitKind(String nowUnitKind) {
        this.nowUnitKind = nowUnitKind;
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

    public String getNowUnitScale() {
        return nowUnitScale;
    }

    public void setNowUnitScale(String nowUnitScale) {
        this.nowUnitScale = nowUnitScale;
    }

    public String getNowUnitTel() {
        return nowUnitTel;
    }

    public void setNowUnitTel(String nowUnitTel) {
        this.nowUnitTel = nowUnitTel;
    }

    public String getIsDriverLicense() {
        return isDriverLicense;
    }

    public void setIsDriverLicense(String isDriverLicense) {
        this.isDriverLicense = isDriverLicense;
    }

    public void setIsDriverLicenseStr(String isDriverLicenseStr) {
        this.isDriverLicenseStr = isDriverLicenseStr;
    }

    public String getFirstDriverNo() {
        return firstDriverNo;
    }

    public void setFirstDriverNo(String firstDriverNo) {
        this.firstDriverNo = firstDriverNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHouseOwner() {
        return houseOwner;
    }

    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getDriverTypeStr() {
        return driverTypeStr;
    }

    public void setDriverTypeStr(String driverTypeStr) {
        this.driverTypeStr = driverTypeStr;
    }

    public String getNowWorkYear() {
        return nowWorkYear;
    }

    public void setNowWorkYear(String nowWorkYear) {
        this.nowWorkYear = nowWorkYear;
    }

    public String getNowWorkYearStr() {
        return nowWorkYearStr;
    }

    public void setNowWorkYearStr(String nowWorkYearStr) {
        this.nowWorkYearStr = nowWorkYearStr;
    }

    public void setBankCardTypeStr(String bankCardTypeStr) {
        this.bankCardTypeStr = bankCardTypeStr;
    }

    public Date getIdCardValidStartDate() {
        return idCardValidStartDate;
    }

    public void setIdCardValidStartDate(Date idCardValidStartDate) {
        this.idCardValidStartDate = idCardValidStartDate;
    }

    public Date getIdCardValidEndDate() {
        return idCardValidEndDate;
    }

    public void setIdCardValidEndDate(Date idCardValidEndDate) {
        this.idCardValidEndDate = idCardValidEndDate;
    }

    public String getRelationDFlStr() {
        return relationDFlStr;
    }

    public void setRelationDFlStr(String relationDFlStr) {
        this.relationDFlStr = relationDFlStr;
    }

    public String getBankCardTypeStr() {
        return bankCardTypeStr;
    }

    public String getIsAssureStr() {
        return isAssureStr;
    }

    public void setIsAssureStr(String isAssureStr) {
        this.isAssureStr = isAssureStr;
    }

    public String getRelationDFl() {
        return relationDFl;
    }

    public void setRelationDFl(String relationDFl) {
        this.relationDFl = relationDFl;
    }

    public String getDriverType() {
        return driverType;
    }

    public void setDriverType(String driverType) {
        this.driverType = driverType;
    }

    public String getNowUnitScaleStr() {
        return nowUnitScaleStr;
    }

    public void setNowUnitScaleStr(String nowUnitScaleStr) {
        this.nowUnitScaleStr = nowUnitScaleStr;
    }
}
