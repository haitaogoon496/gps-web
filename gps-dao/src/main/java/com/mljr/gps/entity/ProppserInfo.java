package com.mljr.gps.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * CA申请人基本信息
 * @author caixiang.sun
 */
public class ProppserInfo implements Serializable{
    private static final long serialVersionUID = -1766083274475746188L;
    /**
     * 申请人标识号
     */
    private Integer proppserId;

    /**
     * 证件种类  15-居民身份证；16-临时身份证；17-军人身份证件；18-武警身份证件；19-通行证；20-护照；21-其他；22-临时户口；23-户口簿；24-边境证；25-外国人居留证；26-身份证明；后面的暂时没有用：目前系统只有身份证。X1-证件重复1；X2-证件重复2；ZZ-联名户。
     */
    private String idType;

    /**
     * 证件号码
     */
    private String idno;

    /**
     * 申请人姓名
     */
    private String proppserName;

    /**
     * 申请人查询密码
     */
    private String psw;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 性别 0-男性；1-女性；
     */
    private String sex;

    /**
     * 婚姻状况  0:未婚  1:已婚  2:离异  3:丧偶
     */
    private String marriage;

    /**
     * 学历
     */
    private String education;

    /**
     * 户籍省份
     */
    private String province;

    /**
     * 区或者县
     */
    private String district;

    /**
     * 户籍城市
     */
    private String city;

    /**
     * 户籍地址
     */
    private String address;

    /**
     * 现居住省份
     */
    private String nowProvince;

    /**
     * 现居住城市
     */
    private String nowCity;

    /**
     * 现居住县或区
     */
    private String nowDistrict;

    /**
     * 现居住地址
     */
    private String nowAddress;

    /**
     * 现居住地址邮编
     */
    private String nowPostalcode;

    /**
     * 现居住地电话
     */
    private String nowTel;

    /**
     * 现居住地时间(年)
     */
    private Integer nowLivingTime;

    /**
     * 参加工作时间
     */
    private Date firstWorkTime;

    /**
     * 现从事行业
     */
    private String nowIndustry;

    /**
     * 现职位
     */
    private String nowPosition;

    /**
     * 收入来源
     */
    private String incomeSource;

    /**
     * 税后月收入
     */
    private BigDecimal incomeMonth;

    /**
     * 其他月收入
     */
    private BigDecimal incomeOth;

    /**
     * 现工作单位名称
     */
    private String nowCompany;

    /**
     * 现工作单位性质
     */
    private String nowUnitKind;

    /**
     * 现工作单位省份
     */
    private String nowUnitProvince;

    /**
     * 现工作单位城市
     */
    private String nowUnitCity;

    /**
     * 现工作单位地址
     */
    private String nowUnitAddress;

    /**
     * 现工作单位规模(人数)
     */
    private Short nowUnitScale;

    /**
     * 现工作单位电话
     */
    private String nowUnitTel;

    /**
     * 现工作单位邮编
     */
    private String nowUnitPostalcode;

    /**
     * 是否有驾驶证
     */
    private String isDriverLicense;

    /**
     * 主贷人驾驶证号码
     */
    private String firstDriverNo;

    /**
     * 驾驶员姓名
     */
    private String driverName;

    /**
     * 驾驶证号码
     */
    private String driverNo;

    /**
     * 申请人移动电话
     */
    private String mobile;

    /**
     * 申请人邮箱
     */
    private String email;

    /**
     * 与紧急联系人关系
     */
    private String relationEContact;

    /**
     * 紧急联系人手机号码
     */
    private String eContactMobile;

    /**
     * 紧急联系人
     */
    private String eContact;

    /**
     * 申请人状态
     */
    private String status;

    /**
     * 备注信息
     */
    private String remakes;

    /**
     * 是否有担保人
     */
    private String isAssure;

    /**
     * 住房所有权
     */
    private String houseOwner;

    /**
     * 驾驶员与主贷人关系
     */
    private String relationDFl;

    /**
     * 信贷记录
     */
    private String creditRecords;

    /**
     * 申请人移动电话2
     */
    private String mobiles;

    /**
     * 与紧急联系人2关系
     */
    private String relationEContacts;

    /**
     * 紧急联系人2手机号码
     */
    private String eContactsMobile;

    /**
     * 紧急联系人2
     */
    private String eContacts;

    /**
     * 有无聚信立报告
     */
    private String isJxlReport;

    /**
     * 驾驶证类型
     */
    private String driverType;

    /**
     * 工作年限
     */
    private String nowWorkYear;

    /**
     * 征信卡类型
     */
    private String bankCardType;

    /**
     * 征信卡号
     */
    private String bankCardNo;

    /**
     * 是否打印担保函
     */
    private String isPrintBook;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 营销人员代码
     */
    private String marketerCode;

    /**
     * 申请卡片产品代码
     */
    private String cardProductCode;

    /**
     * 申请卡片产品名称
     */
    private String cardProductName;

    /**
     * 申请人开卡城市代码
     */
    private String cityCodeStr;

    /**
     * 
     */
    private String court;

    /**
     * 证件有效期
     */
    private String periodOfValidity;

    /**
     * 
     */
    private String isCertAll;

    /**
     * 客户是否在现场
     */
    private String isScene;

    /**
     * 推荐人代码
     */
    private String referenceCode;

    /**
     * 推荐人机构代码
     */
    private String referenceJgcode;

    /**
     * 领用合约代码
     */
    private String applicationagreementbarcode;

    /**
     * 户口性质
     */
    private String residenceNature;

    /**
     * 现住址行政区编码 国标
     */
    private String nowDistrictCode;

    /**
     * 公司地址行政区编码 国标
     */
    private String nowUnitDistrictCode;

    /**
     * 公司地址行政区  国标文字
     */
    private String nowUnitDistrict;

    /**
     * 身份证有效起始日期
     */
    private Date idCardValidStartDate;

    /**
     * 身份证有效截止日期
     */
    private Date idCardValidEndDate;

    /**
     * 户籍省份REGCODE
     */
    private String provinceCode;

    /**
     * 户籍城市REGCODE
     */
    private String cityCode;

    /**
     * 户籍区县REGCODE
     */
    private String districtCode;

    /**
     * 现住址省份编码
     */
    private String nowProvinceCode;

    /**
     * 现住址城市编码
     */
    private String nowCityCode;

    /**
     * 现住址工作单位省份编码
     */
    private String nowUnitProvinceCode;

    /**
     * 现住址工作单位城市编码
     */
    private String nowUnitCityCode;

    /**
     * 申请人标识号
     * @return PROPPSER_ID 申请人标识号
     */
    public Integer getProppserId() {
        return proppserId;
    }

    /**
     * 申请人标识号
     * @param proppserId 申请人标识号
     */
    public void setProppserId(Integer proppserId) {
        this.proppserId = proppserId;
    }

    /**
     * 证件种类  15-居民身份证；16-临时身份证；17-军人身份证件；18-武警身份证件；19-通行证；20-护照；21-其他；22-临时户口；23-户口簿；24-边境证；25-外国人居留证；26-身份证明；后面的暂时没有用：目前系统只有身份证。X1-证件重复1；X2-证件重复2；ZZ-联名户。
     * @return ID_TYPE 证件种类  15-居民身份证；16-临时身份证；17-军人身份证件；18-武警身份证件；19-通行证；20-护照；21-其他；22-临时户口；23-户口簿；24-边境证；25-外国人居留证；26-身份证明；后面的暂时没有用：目前系统只有身份证。X1-证件重复1；X2-证件重复2；ZZ-联名户。
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 证件种类  15-居民身份证；16-临时身份证；17-军人身份证件；18-武警身份证件；19-通行证；20-护照；21-其他；22-临时户口；23-户口簿；24-边境证；25-外国人居留证；26-身份证明；后面的暂时没有用：目前系统只有身份证。X1-证件重复1；X2-证件重复2；ZZ-联名户。
     * @param idType 证件种类  15-居民身份证；16-临时身份证；17-军人身份证件；18-武警身份证件；19-通行证；20-护照；21-其他；22-临时户口；23-户口簿；24-边境证；25-外国人居留证；26-身份证明；后面的暂时没有用：目前系统只有身份证。X1-证件重复1；X2-证件重复2；ZZ-联名户。
     */
    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    /**
     * 证件号码
     * @return IDNO 证件号码
     */
    public String getIdno() {
        return idno;
    }

    /**
     * 证件号码
     * @param idno 证件号码
     */
    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    /**
     * 申请人姓名
     * @return PROPPSER_NAME 申请人姓名
     */
    public String getProppserName() {
        return proppserName;
    }

    /**
     * 申请人姓名
     * @param proppserName 申请人姓名
     */
    public void setProppserName(String proppserName) {
        this.proppserName = proppserName == null ? null : proppserName.trim();
    }

    /**
     * 申请人查询密码
     * @return PSW 申请人查询密码
     */
    public String getPsw() {
        return psw;
    }

    /**
     * 申请人查询密码
     * @param psw 申请人查询密码
     */
    public void setPsw(String psw) {
        this.psw = psw == null ? null : psw.trim();
    }

    /**
     * 出生日期
     * @return BIRTH 出生日期
     */
    public String getBirth() {
        return birth;
    }

    /**
     * 出生日期
     * @param birth 出生日期
     */
    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    /**
     * 性别 0-男性；1-女性；
     * @return SEX 性别 0-男性；1-女性；
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别 0-男性；1-女性；
     * @param sex 性别 0-男性；1-女性；
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 婚姻状况  0:未婚  1:已婚  2:离异  3:丧偶
     * @return MARRIAGE 婚姻状况  0:未婚  1:已婚  2:离异  3:丧偶
     */
    public String getMarriage() {
        return marriage;
    }

    /**
     * 婚姻状况  0:未婚  1:已婚  2:离异  3:丧偶
     * @param marriage 婚姻状况  0:未婚  1:已婚  2:离异  3:丧偶
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage == null ? null : marriage.trim();
    }

    /**
     * 学历
     * @return EDUCATION 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 学历
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 户籍省份
     * @return PROVINCE 户籍省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 户籍省份
     * @param province 户籍省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 区或者县
     * @return DISTRICT 区或者县
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 区或者县
     * @param district 区或者县
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 户籍城市
     * @return CITY 户籍城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 户籍城市
     * @param city 户籍城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 户籍地址
     * @return ADDRESS 户籍地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 户籍地址
     * @param address 户籍地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 现居住省份
     * @return NOW_PROVINCE 现居住省份
     */
    public String getNowProvince() {
        return nowProvince;
    }

    /**
     * 现居住省份
     * @param nowProvince 现居住省份
     */
    public void setNowProvince(String nowProvince) {
        this.nowProvince = nowProvince == null ? null : nowProvince.trim();
    }

    /**
     * 现居住城市
     * @return NOW_CITY 现居住城市
     */
    public String getNowCity() {
        return nowCity;
    }

    /**
     * 现居住城市
     * @param nowCity 现居住城市
     */
    public void setNowCity(String nowCity) {
        this.nowCity = nowCity == null ? null : nowCity.trim();
    }

    /**
     * 现居住县或区
     * @return NOW_DISTRICT 现居住县或区
     */
    public String getNowDistrict() {
        return nowDistrict;
    }

    /**
     * 现居住县或区
     * @param nowDistrict 现居住县或区
     */
    public void setNowDistrict(String nowDistrict) {
        this.nowDistrict = nowDistrict == null ? null : nowDistrict.trim();
    }

    /**
     * 现居住地址
     * @return NOW_ADDRESS 现居住地址
     */
    public String getNowAddress() {
        return nowAddress;
    }

    /**
     * 现居住地址
     * @param nowAddress 现居住地址
     */
    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress == null ? null : nowAddress.trim();
    }

    /**
     * 现居住地址邮编
     * @return NOW_POSTALCODE 现居住地址邮编
     */
    public String getNowPostalcode() {
        return nowPostalcode;
    }

    /**
     * 现居住地址邮编
     * @param nowPostalcode 现居住地址邮编
     */
    public void setNowPostalcode(String nowPostalcode) {
        this.nowPostalcode = nowPostalcode == null ? null : nowPostalcode.trim();
    }

    /**
     * 现居住地电话
     * @return NOW_TEL 现居住地电话
     */
    public String getNowTel() {
        return nowTel;
    }

    /**
     * 现居住地电话
     * @param nowTel 现居住地电话
     */
    public void setNowTel(String nowTel) {
        this.nowTel = nowTel == null ? null : nowTel.trim();
    }

    /**
     * 现居住地时间(年)
     * @return NOW_LIVING_TIME 现居住地时间(年)
     */
    public Integer getNowLivingTime() {
        return nowLivingTime;
    }

    /**
     * 现居住地时间(年)
     * @param nowLivingTime 现居住地时间(年)
     */
    public void setNowLivingTime(Integer nowLivingTime) {
        this.nowLivingTime = nowLivingTime;
    }

    /**
     * 参加工作时间
     * @return FIRST_WORK_TIME 参加工作时间
     */
    public Date getFirstWorkTime() {
        return firstWorkTime;
    }

    /**
     * 参加工作时间
     * @param firstWorkTime 参加工作时间
     */
    public void setFirstWorkTime(Date firstWorkTime) {
        this.firstWorkTime = firstWorkTime;
    }

    /**
     * 现从事行业
     * @return NOW_INDUSTRY 现从事行业
     */
    public String getNowIndustry() {
        return nowIndustry;
    }

    /**
     * 现从事行业
     * @param nowIndustry 现从事行业
     */
    public void setNowIndustry(String nowIndustry) {
        this.nowIndustry = nowIndustry == null ? null : nowIndustry.trim();
    }

    /**
     * 现职位
     * @return NOW_POSITION 现职位
     */
    public String getNowPosition() {
        return nowPosition;
    }

    /**
     * 现职位
     * @param nowPosition 现职位
     */
    public void setNowPosition(String nowPosition) {
        this.nowPosition = nowPosition == null ? null : nowPosition.trim();
    }

    /**
     * 收入来源
     * @return INCOME_SOURCE 收入来源
     */
    public String getIncomeSource() {
        return incomeSource;
    }

    /**
     * 收入来源
     * @param incomeSource 收入来源
     */
    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource == null ? null : incomeSource.trim();
    }

    /**
     * 税后月收入
     * @return INCOME_MONTH 税后月收入
     */
    public BigDecimal getIncomeMonth() {
        return incomeMonth;
    }

    /**
     * 税后月收入
     * @param incomeMonth 税后月收入
     */
    public void setIncomeMonth(BigDecimal incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    /**
     * 其他月收入
     * @return INCOME_OTH 其他月收入
     */
    public BigDecimal getIncomeOth() {
        return incomeOth;
    }

    /**
     * 其他月收入
     * @param incomeOth 其他月收入
     */
    public void setIncomeOth(BigDecimal incomeOth) {
        this.incomeOth = incomeOth;
    }

    /**
     * 现工作单位名称
     * @return NOW_COMPANY 现工作单位名称
     */
    public String getNowCompany() {
        return nowCompany;
    }

    /**
     * 现工作单位名称
     * @param nowCompany 现工作单位名称
     */
    public void setNowCompany(String nowCompany) {
        this.nowCompany = nowCompany == null ? null : nowCompany.trim();
    }

    /**
     * 现工作单位性质
     * @return NOW_UNIT_KIND 现工作单位性质
     */
    public String getNowUnitKind() {
        return nowUnitKind;
    }

    /**
     * 现工作单位性质
     * @param nowUnitKind 现工作单位性质
     */
    public void setNowUnitKind(String nowUnitKind) {
        this.nowUnitKind = nowUnitKind == null ? null : nowUnitKind.trim();
    }

    /**
     * 现工作单位省份
     * @return NOW_UNIT_PROVINCE 现工作单位省份
     */
    public String getNowUnitProvince() {
        return nowUnitProvince;
    }

    /**
     * 现工作单位省份
     * @param nowUnitProvince 现工作单位省份
     */
    public void setNowUnitProvince(String nowUnitProvince) {
        this.nowUnitProvince = nowUnitProvince == null ? null : nowUnitProvince.trim();
    }

    /**
     * 现工作单位城市
     * @return NOW_UNIT_CITY 现工作单位城市
     */
    public String getNowUnitCity() {
        return nowUnitCity;
    }

    /**
     * 现工作单位城市
     * @param nowUnitCity 现工作单位城市
     */
    public void setNowUnitCity(String nowUnitCity) {
        this.nowUnitCity = nowUnitCity == null ? null : nowUnitCity.trim();
    }

    /**
     * 现工作单位地址
     * @return NOW_UNIT_ADDRESS 现工作单位地址
     */
    public String getNowUnitAddress() {
        return nowUnitAddress;
    }

    /**
     * 现工作单位地址
     * @param nowUnitAddress 现工作单位地址
     */
    public void setNowUnitAddress(String nowUnitAddress) {
        this.nowUnitAddress = nowUnitAddress == null ? null : nowUnitAddress.trim();
    }

    /**
     * 现工作单位规模(人数)
     * @return NOW_UNIT_SCALE 现工作单位规模(人数)
     */
    public Short getNowUnitScale() {
        return nowUnitScale;
    }

    /**
     * 现工作单位规模(人数)
     * @param nowUnitScale 现工作单位规模(人数)
     */
    public void setNowUnitScale(Short nowUnitScale) {
        this.nowUnitScale = nowUnitScale;
    }

    /**
     * 现工作单位电话
     * @return NOW_UNIT_TEL 现工作单位电话
     */
    public String getNowUnitTel() {
        return nowUnitTel;
    }

    /**
     * 现工作单位电话
     * @param nowUnitTel 现工作单位电话
     */
    public void setNowUnitTel(String nowUnitTel) {
        this.nowUnitTel = nowUnitTel == null ? null : nowUnitTel.trim();
    }

    /**
     * 现工作单位邮编
     * @return NOW_UNIT_POSTALCODE 现工作单位邮编
     */
    public String getNowUnitPostalcode() {
        return nowUnitPostalcode;
    }

    /**
     * 现工作单位邮编
     * @param nowUnitPostalcode 现工作单位邮编
     */
    public void setNowUnitPostalcode(String nowUnitPostalcode) {
        this.nowUnitPostalcode = nowUnitPostalcode == null ? null : nowUnitPostalcode.trim();
    }

    /**
     * 是否有驾驶证
     * @return IS_DRIVER_LICENSE 是否有驾驶证
     */
    public String getIsDriverLicense() {
        return isDriverLicense;
    }

    /**
     * 是否有驾驶证
     * @param isDriverLicense 是否有驾驶证
     */
    public void setIsDriverLicense(String isDriverLicense) {
        this.isDriverLicense = isDriverLicense == null ? null : isDriverLicense.trim();
    }

    /**
     * 主贷人驾驶证号码
     * @return FIRST_DRIVER_NO 主贷人驾驶证号码
     */
    public String getFirstDriverNo() {
        return firstDriverNo;
    }

    /**
     * 主贷人驾驶证号码
     * @param firstDriverNo 主贷人驾驶证号码
     */
    public void setFirstDriverNo(String firstDriverNo) {
        this.firstDriverNo = firstDriverNo == null ? null : firstDriverNo.trim();
    }

    /**
     * 驾驶员姓名
     * @return DRIVER_NAME 驾驶员姓名
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * 驾驶员姓名
     * @param driverName 驾驶员姓名
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    /**
     * 驾驶证号码
     * @return DRIVER_NO 驾驶证号码
     */
    public String getDriverNo() {
        return driverNo;
    }

    /**
     * 驾驶证号码
     * @param driverNo 驾驶证号码
     */
    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo == null ? null : driverNo.trim();
    }

    /**
     * 申请人移动电话
     * @return MOBILE 申请人移动电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 申请人移动电话
     * @param mobile 申请人移动电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 申请人邮箱
     * @return EMAIL 申请人邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 申请人邮箱
     * @param email 申请人邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 与紧急联系人关系
     * @return RELATION_E_CONTACT 与紧急联系人关系
     */
    public String getRelationEContact() {
        return relationEContact;
    }

    /**
     * 与紧急联系人关系
     * @param relationEContact 与紧急联系人关系
     */
    public void setRelationEContact(String relationEContact) {
        this.relationEContact = relationEContact == null ? null : relationEContact.trim();
    }

    /**
     * 紧急联系人手机号码
     * @return E_CONTACT_MOBILE 紧急联系人手机号码
     */
    public String geteContactMobile() {
        return eContactMobile;
    }

    /**
     * 紧急联系人手机号码
     * @param eContactMobile 紧急联系人手机号码
     */
    public void seteContactMobile(String eContactMobile) {
        this.eContactMobile = eContactMobile == null ? null : eContactMobile.trim();
    }

    /**
     * 紧急联系人
     * @return E_CONTACT 紧急联系人
     */
    public String geteContact() {
        return eContact;
    }

    /**
     * 紧急联系人
     * @param eContact 紧急联系人
     */
    public void seteContact(String eContact) {
        this.eContact = eContact == null ? null : eContact.trim();
    }

    /**
     * 申请人状态
     * @return STATUS 申请人状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 申请人状态
     * @param status 申请人状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 备注信息
     * @return REMAKES 备注信息
     */
    public String getRemakes() {
        return remakes;
    }

    /**
     * 备注信息
     * @param remakes 备注信息
     */
    public void setRemakes(String remakes) {
        this.remakes = remakes == null ? null : remakes.trim();
    }

    /**
     * 是否有担保人
     * @return IS_ASSURE 是否有担保人
     */
    public String getIsAssure() {
        return isAssure;
    }

    /**
     * 是否有担保人
     * @param isAssure 是否有担保人
     */
    public void setIsAssure(String isAssure) {
        this.isAssure = isAssure == null ? null : isAssure.trim();
    }

    /**
     * 住房所有权
     * @return HOUSE_OWNER 住房所有权
     */
    public String getHouseOwner() {
        return houseOwner;
    }

    /**
     * 住房所有权
     * @param houseOwner 住房所有权
     */
    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner == null ? null : houseOwner.trim();
    }

    /**
     * 驾驶员与主贷人关系
     * @return RELATION_D_FL 驾驶员与主贷人关系
     */
    public String getRelationDFl() {
        return relationDFl;
    }

    /**
     * 驾驶员与主贷人关系
     * @param relationDFl 驾驶员与主贷人关系
     */
    public void setRelationDFl(String relationDFl) {
        this.relationDFl = relationDFl == null ? null : relationDFl.trim();
    }

    /**
     * 信贷记录
     * @return CREDIT_RECORDS 信贷记录
     */
    public String getCreditRecords() {
        return creditRecords;
    }

    /**
     * 信贷记录
     * @param creditRecords 信贷记录
     */
    public void setCreditRecords(String creditRecords) {
        this.creditRecords = creditRecords == null ? null : creditRecords.trim();
    }

    /**
     * 申请人移动电话2
     * @return MOBILES 申请人移动电话2
     */
    public String getMobiles() {
        return mobiles;
    }

    /**
     * 申请人移动电话2
     * @param mobiles 申请人移动电话2
     */
    public void setMobiles(String mobiles) {
        this.mobiles = mobiles == null ? null : mobiles.trim();
    }

    /**
     * 与紧急联系人2关系
     * @return RELATION_E_CONTACTS 与紧急联系人2关系
     */
    public String getRelationEContacts() {
        return relationEContacts;
    }

    /**
     * 与紧急联系人2关系
     * @param relationEContacts 与紧急联系人2关系
     */
    public void setRelationEContacts(String relationEContacts) {
        this.relationEContacts = relationEContacts == null ? null : relationEContacts.trim();
    }

    /**
     * 紧急联系人2手机号码
     * @return E_CONTACTS_MOBILE 紧急联系人2手机号码
     */
    public String geteContactsMobile() {
        return eContactsMobile;
    }

    /**
     * 紧急联系人2手机号码
     * @param eContactsMobile 紧急联系人2手机号码
     */
    public void seteContactsMobile(String eContactsMobile) {
        this.eContactsMobile = eContactsMobile == null ? null : eContactsMobile.trim();
    }

    /**
     * 紧急联系人2
     * @return E_CONTACTS 紧急联系人2
     */
    public String geteContacts() {
        return eContacts;
    }

    /**
     * 紧急联系人2
     * @param eContacts 紧急联系人2
     */
    public void seteContacts(String eContacts) {
        this.eContacts = eContacts == null ? null : eContacts.trim();
    }

    /**
     * 有无聚信立报告
     * @return IS_JXL_REPORT 有无聚信立报告
     */
    public String getIsJxlReport() {
        return isJxlReport;
    }

    /**
     * 有无聚信立报告
     * @param isJxlReport 有无聚信立报告
     */
    public void setIsJxlReport(String isJxlReport) {
        this.isJxlReport = isJxlReport == null ? null : isJxlReport.trim();
    }

    /**
     * 驾驶证类型
     * @return driver_type 驾驶证类型
     */
    public String getDriverType() {
        return driverType;
    }

    /**
     * 驾驶证类型
     * @param driverType 驾驶证类型
     */
    public void setDriverType(String driverType) {
        this.driverType = driverType == null ? null : driverType.trim();
    }

    /**
     * 工作年限
     * @return now_work_year 工作年限
     */
    public String getNowWorkYear() {
        return nowWorkYear;
    }

    /**
     * 工作年限
     * @param nowWorkYear 工作年限
     */
    public void setNowWorkYear(String nowWorkYear) {
        this.nowWorkYear = nowWorkYear == null ? null : nowWorkYear.trim();
    }

    /**
     * 征信卡类型
     * @return BANK_CARD_TYPE 征信卡类型
     */
    public String getBankCardType() {
        return bankCardType;
    }

    /**
     * 征信卡类型
     * @param bankCardType 征信卡类型
     */
    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType == null ? null : bankCardType.trim();
    }

    /**
     * 征信卡号
     * @return BANK_CARD_NO 征信卡号
     */
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * 征信卡号
     * @param bankCardNo 征信卡号
     */
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    /**
     * 是否打印担保函
     * @return IS_PRINT_BOOK 是否打印担保函
     */
    public String getIsPrintBook() {
        return isPrintBook;
    }

    /**
     * 是否打印担保函
     * @param isPrintBook 是否打印担保函
     */
    public void setIsPrintBook(String isPrintBook) {
        this.isPrintBook = isPrintBook == null ? null : isPrintBook.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 营销人员代码
     * @return MARKETER_CODE 营销人员代码
     */
    public String getMarketerCode() {
        return marketerCode;
    }

    /**
     * 营销人员代码
     * @param marketerCode 营销人员代码
     */
    public void setMarketerCode(String marketerCode) {
        this.marketerCode = marketerCode == null ? null : marketerCode.trim();
    }

    /**
     * 申请卡片产品代码
     * @return CARD_PRODUCT_CODE 申请卡片产品代码
     */
    public String getCardProductCode() {
        return cardProductCode;
    }

    /**
     * 申请卡片产品代码
     * @param cardProductCode 申请卡片产品代码
     */
    public void setCardProductCode(String cardProductCode) {
        this.cardProductCode = cardProductCode == null ? null : cardProductCode.trim();
    }

    /**
     * 申请卡片产品名称
     * @return CARD_PRODUCT_NAME 申请卡片产品名称
     */
    public String getCardProductName() {
        return cardProductName;
    }

    /**
     * 申请卡片产品名称
     * @param cardProductName 申请卡片产品名称
     */
    public void setCardProductName(String cardProductName) {
        this.cardProductName = cardProductName == null ? null : cardProductName.trim();
    }

    /**
     * 申请人开卡城市代码
     * @return CITY_CODE_STR 申请人开卡城市代码
     */
    public String getCityCodeStr() {
        return cityCodeStr;
    }

    /**
     * 申请人开卡城市代码
     * @param cityCodeStr 申请人开卡城市代码
     */
    public void setCityCodeStr(String cityCodeStr) {
        this.cityCodeStr = cityCodeStr == null ? null : cityCodeStr.trim();
    }

    /**
     * 
     * @return COURT 
     */
    public String getCourt() {
        return court;
    }

    /**
     * 
     * @param court 
     */
    public void setCourt(String court) {
        this.court = court == null ? null : court.trim();
    }

    /**
     * 证件有效期
     * @return period_of_validity 证件有效期
     */
    public String getPeriodOfValidity() {
        return periodOfValidity;
    }

    /**
     * 证件有效期
     * @param periodOfValidity 证件有效期
     */
    public void setPeriodOfValidity(String periodOfValidity) {
        this.periodOfValidity = periodOfValidity == null ? null : periodOfValidity.trim();
    }

    /**
     * 
     * @return IS_CERT_ALL 
     */
    public String getIsCertAll() {
        return isCertAll;
    }

    /**
     * 
     * @param isCertAll 
     */
    public void setIsCertAll(String isCertAll) {
        this.isCertAll = isCertAll == null ? null : isCertAll.trim();
    }

    /**
     * 客户是否在现场
     * @return IS_SCENE 客户是否在现场
     */
    public String getIsScene() {
        return isScene;
    }

    /**
     * 客户是否在现场
     * @param isScene 客户是否在现场
     */
    public void setIsScene(String isScene) {
        this.isScene = isScene == null ? null : isScene.trim();
    }

    /**
     * 推荐人代码
     * @return reference_code 推荐人代码
     */
    public String getReferenceCode() {
        return referenceCode;
    }

    /**
     * 推荐人代码
     * @param referenceCode 推荐人代码
     */
    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode == null ? null : referenceCode.trim();
    }

    /**
     * 推荐人机构代码
     * @return reference_jgcode 推荐人机构代码
     */
    public String getReferenceJgcode() {
        return referenceJgcode;
    }

    /**
     * 推荐人机构代码
     * @param referenceJgcode 推荐人机构代码
     */
    public void setReferenceJgcode(String referenceJgcode) {
        this.referenceJgcode = referenceJgcode == null ? null : referenceJgcode.trim();
    }

    /**
     * 领用合约代码
     * @return applicationagreementbarcode 领用合约代码
     */
    public String getApplicationagreementbarcode() {
        return applicationagreementbarcode;
    }

    /**
     * 领用合约代码
     * @param applicationagreementbarcode 领用合约代码
     */
    public void setApplicationagreementbarcode(String applicationagreementbarcode) {
        this.applicationagreementbarcode = applicationagreementbarcode == null ? null : applicationagreementbarcode.trim();
    }

    /**
     * 户口性质
     * @return residence_nature 户口性质
     */
    public String getResidenceNature() {
        return residenceNature;
    }

    /**
     * 户口性质
     * @param residenceNature 户口性质
     */
    public void setResidenceNature(String residenceNature) {
        this.residenceNature = residenceNature == null ? null : residenceNature.trim();
    }

    /**
     * 现住址行政区编码 国标
     * @return now_district_code 现住址行政区编码 国标
     */
    public String getNowDistrictCode() {
        return nowDistrictCode;
    }

    /**
     * 现住址行政区编码 国标
     * @param nowDistrictCode 现住址行政区编码 国标
     */
    public void setNowDistrictCode(String nowDistrictCode) {
        this.nowDistrictCode = nowDistrictCode == null ? null : nowDistrictCode.trim();
    }

    /**
     * 公司地址行政区编码 国标
     * @return now_unit_district_code 公司地址行政区编码 国标
     */
    public String getNowUnitDistrictCode() {
        return nowUnitDistrictCode;
    }

    /**
     * 公司地址行政区编码 国标
     * @param nowUnitDistrictCode 公司地址行政区编码 国标
     */
    public void setNowUnitDistrictCode(String nowUnitDistrictCode) {
        this.nowUnitDistrictCode = nowUnitDistrictCode == null ? null : nowUnitDistrictCode.trim();
    }

    /**
     * 公司地址行政区  国标文字
     * @return now_unit_district 公司地址行政区  国标文字
     */
    public String getNowUnitDistrict() {
        return nowUnitDistrict;
    }

    /**
     * 公司地址行政区  国标文字
     * @param nowUnitDistrict 公司地址行政区  国标文字
     */
    public void setNowUnitDistrict(String nowUnitDistrict) {
        this.nowUnitDistrict = nowUnitDistrict == null ? null : nowUnitDistrict.trim();
    }

    /**
     * 身份证有效起始日期
     * @return id_card_valid_start_date 身份证有效起始日期
     */
    public Date getIdCardValidStartDate() {
        return idCardValidStartDate;
    }

    /**
     * 身份证有效起始日期
     * @param idCardValidStartDate 身份证有效起始日期
     */
    public void setIdCardValidStartDate(Date idCardValidStartDate) {
        this.idCardValidStartDate = idCardValidStartDate;
    }

    /**
     * 身份证有效截止日期
     * @return id_card_valid_end_date 身份证有效截止日期
     */
    public Date getIdCardValidEndDate() {
        return idCardValidEndDate;
    }

    /**
     * 身份证有效截止日期
     * @param idCardValidEndDate 身份证有效截止日期
     */
    public void setIdCardValidEndDate(Date idCardValidEndDate) {
        this.idCardValidEndDate = idCardValidEndDate;
    }

    /**
     * 户籍省份REGCODE
     * @return PROVINCE_CODE 户籍省份REGCODE
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 户籍省份REGCODE
     * @param provinceCode 户籍省份REGCODE
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    /**
     * 户籍城市REGCODE
     * @return CITY_CODE 户籍城市REGCODE
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 户籍城市REGCODE
     * @param cityCode 户籍城市REGCODE
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    /**
     * 户籍区县REGCODE
     * @return DISTRICT_CODE 户籍区县REGCODE
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * 户籍区县REGCODE
     * @param districtCode 户籍区县REGCODE
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    /**
     * 现住址省份编码
     * @return NOW_PROVINCE_CODE 现住址省份编码
     */
    public String getNowProvinceCode() {
        return nowProvinceCode;
    }

    /**
     * 现住址省份编码
     * @param nowProvinceCode 现住址省份编码
     */
    public void setNowProvinceCode(String nowProvinceCode) {
        this.nowProvinceCode = nowProvinceCode == null ? null : nowProvinceCode.trim();
    }

    /**
     * 现住址城市编码
     * @return NOW_CITY_CODE 现住址城市编码
     */
    public String getNowCityCode() {
        return nowCityCode;
    }

    /**
     * 现住址城市编码
     * @param nowCityCode 现住址城市编码
     */
    public void setNowCityCode(String nowCityCode) {
        this.nowCityCode = nowCityCode == null ? null : nowCityCode.trim();
    }

    /**
     * 现住址工作单位省份编码
     * @return now_unit_province_code 现住址工作单位省份编码
     */
    public String getNowUnitProvinceCode() {
        return nowUnitProvinceCode;
    }

    /**
     * 现住址工作单位省份编码
     * @param nowUnitProvinceCode 现住址工作单位省份编码
     */
    public void setNowUnitProvinceCode(String nowUnitProvinceCode) {
        this.nowUnitProvinceCode = nowUnitProvinceCode == null ? null : nowUnitProvinceCode.trim();
    }

    /**
     * 现住址工作单位城市编码
     * @return now_unit_city_code 现住址工作单位城市编码
     */
    public String getNowUnitCityCode() {
        return nowUnitCityCode;
    }

    /**
     * 现住址工作单位城市编码
     * @param nowUnitCityCode 现住址工作单位城市编码
     */
    public void setNowUnitCityCode(String nowUnitCityCode) {
        this.nowUnitCityCode = nowUnitCityCode == null ? null : nowUnitCityCode.trim();
    }
}