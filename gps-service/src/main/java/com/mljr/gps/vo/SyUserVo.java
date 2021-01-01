package com.mljr.gps.vo;

import com.mljr.gps.base.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @description: 用户Vo
 * @Date : 上午10:10 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public class SyUserVo extends BaseVo {
    private static final long serialVersionUID = 3702763134688023608L;

    private Integer userId;

    private String userName;

    private String trueName;

    private Short cardType;

    private String cardId;

    private String password;

    private String birthday;

    private String sex;

    private String province;

    private String city;

    private String email;

    private String phone;

    private String address;

    private String postalCode;

    private String head;

    private Date createTime;

    private Date lastTime;

    private Integer loginTimes;

    private String userStatus;

    private String userType;

    @ApiModelProperty(name="nowGpsBillCount", value="当前持有待处理GPS审批单数量")
    private Integer nowGpsBillCount;

    @ApiModelProperty(name="nowGpsBillCount", value="当天系统最后自动派单时间")
    private String sendBillTime;

    @ApiModelProperty(name="autoSendBillStatus", value="是否开启自动领单")
    private Integer autoSendBillStatus;
    @ApiModelProperty(value = "是否测试账号")
    private Boolean test = false;


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
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public Short getCardType() {
        return cardType;
    }

    public void setCardType(Short cardType) {
        this.cardType = cardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getNowGpsBillCount() {
        return nowGpsBillCount;
    }

    public void setNowGpsBillCount(Integer nowGpsBillCount) {
        this.nowGpsBillCount = nowGpsBillCount;
    }

    public String getSendBillTime() {
        return sendBillTime;
    }

    public void setSendBillTime(String sendBillTime) {
        this.sendBillTime = sendBillTime;
    }

    public Integer getAutoSendBillStatus() {
        return autoSendBillStatus;
    }

    public void setAutoSendBillStatus(Integer autoSendBillStatus) {
        this.autoSendBillStatus = autoSendBillStatus;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }
}
