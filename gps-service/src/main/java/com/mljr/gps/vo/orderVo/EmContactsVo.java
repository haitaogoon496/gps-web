package com.mljr.gps.vo.orderVo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午3:31 2018/7/8
 */
public class EmContactsVo {

    private String appCode;
    @ApiModelProperty(name="relationEContact",value="与紧急联系人关系",dataType="String")
    @JsonProperty(value = "relationEContact")
    private String relationEContact;
    @ApiModelProperty(name="relationEContactStr",value="与紧急联系人关系Str",dataType="String")
    @JsonProperty(value = "relationEContactStr")
    private String relationEContactStr;
    @ApiModelProperty(name="relationEContacts",value="与紧急联系人关系2",dataType="String")
    @JsonProperty(value = "relationEContacts")
    private String relationEContacts;
    @ApiModelProperty(name="relationEContactsStr",value="与紧急联系人关系2Str",dataType="String")
    @JsonProperty(value = "relationEContactsStr")
    private String relationEContactsStr;
    @ApiModelProperty(name="eContact",value="紧急联系人",dataType="String")
    @JsonProperty(value = "eContact")
    private String eContact;
    @ApiModelProperty(name="eContacts",value="紧急联系人2",dataType="String")
    @JsonProperty(value = "eContacts")
    private String eContacts;
    @ApiModelProperty(name="eContactMobile",value="紧急联系人电话",dataType="String")
    @JsonProperty(value = "eContactMobile")
    private String eContactMobile;
    @ApiModelProperty(name="eContactMobile2",value="紧急联系人电话2",dataType="String")
    @JsonProperty(value = "eContactMobile2")
    private String eContactMobile2;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getRelationEContact() {
        return relationEContact;
    }

    public void setRelationEContact(String relationEContact) {
        this.relationEContact = relationEContact;
    }

    public String getRelationEContacts() {
        return relationEContacts;
    }

    public void setRelationEContacts(String relationEContacts) {
        this.relationEContacts = relationEContacts;
    }

    public String geteContact() {
        return eContact;
    }

    public void seteContact(String eContact) {
        this.eContact = eContact;
    }

    public String geteContacts() {
        return eContacts;
    }

    public void seteContacts(String eContacts) {
        this.eContacts = eContacts;
    }

    public String geteContactMobile() {
        return eContactMobile;
    }

    public void seteContactMobile(String eContactMobile) {
        this.eContactMobile = eContactMobile;
    }

    public String geteContactMobile2() {
        return eContactMobile2;
    }

    public void seteContactMobile2(String eContactMobile2) {
        this.eContactMobile2 = eContactMobile2;
    }

    public String getRelationEContactStr() {
        return relationEContact;
    }

    public void setRelationEContactStr(String relationEContactStr) {
        this.relationEContactStr = relationEContactStr;
    }

    public String getRelationEContactsStr() {
        return relationEContacts;
    }

    public void setRelationEContactsStr(String relationEContactsStr) {
        this.relationEContactsStr = relationEContactsStr;
    }
}
