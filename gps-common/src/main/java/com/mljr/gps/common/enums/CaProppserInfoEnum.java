package com.mljr.gps.common.enums;

/**
 * @Author：rongss
 * @Description
 * @Date：Created in 下午1:36 2018/2/20
 */
public class CaProppserInfoEnum {

    /**
     * 婚姻状况
     */
    public enum MarriageTypeEnum {

        UN_MARRIED(0,"未婚"),
        MARRIED(1,"已婚"),
        DIVORCE(2,"离异"),
        WIDOWED(3,"丧偶");

        private int index;
        private String name;

        MarriageTypeEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public static String getNameByIndex(int index){
            for(MarriageTypeEnum e : MarriageTypeEnum.values()){
                if(e.getIndex() == index){
                    return e.getName();
                }
            }
            return null;
        }

        public static MarriageTypeEnum getByIndex(int index){
            for(MarriageTypeEnum e : MarriageTypeEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }

        public static MarriageTypeEnum[] valuesNotNull(){
            return new MarriageTypeEnum[]{UN_MARRIED,MARRIED,DIVORCE,WIDOWED};
        }


    }

    /**
     * 电子合同类型
     */
    public enum AuthTypeEnum {

        ELECTROIC_CREDIT(2, "电子合同"),
        PAPER_CREDIT(1, "纸质合同");

        private int index;
        private String name;

        AuthTypeEnum(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public String getName() {
            return name;
        }

        public static String getNameByIndex(int index){
            for(AuthTypeEnum e : AuthTypeEnum.values()){
                if(e.getIndex() == index){
                    return e.getName();
                }
            }
            return null;
        }

        public static AuthTypeEnum getByIndex(int index){
            for(AuthTypeEnum e : AuthTypeEnum.values()){
                if(e.getIndex() == index){
                    return e;
                }
            }
            return null;
        }
    }

    public enum EducationEnum  {

        NULL("","--"),
        DAZH("0","大专"),
        BENK("1","本科"),
        SHSH("2","硕士"),
        QITA("3","其他"),
        BOSH("4","博士"),
        GAOZ("5","高中"),
        XXUE("6","小学"),
        CHUZ("7","初中"),
        ZHZH("8","中专"),
        DEXL("9","第二学士学位");


        private String name;
        private String value;

        EducationEnum(String value, String name) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public static EducationEnum[] valuesNotNull() {
            return new EducationEnum[]{DAZH, BENK, SHSH, QITA, BOSH, GAOZ, XXUE, CHUZ, ZHZH};
        }
        public static String getNameByValue(String value){
            String name="";
            for(EducationEnum educationEnum: EducationEnum.values()){
                if(educationEnum.getValue().equals(value)){
                    name = educationEnum.getName();
                    break;
                }
            }
            return name;
        }
    }

    public enum SexEnum {

        NULL("","--"),
        M("1","男"),
        F("0","女");

        private String name;
        private String value;

        SexEnum(String value,String name){
            this.name = name;
            this.value = value;
        }
        public static String getNameByVale(String value){
            for(SexEnum sexEnum: SexEnum.values()){
                if(sexEnum.getValue().equals(value)){
                    return sexEnum.getName();
                }
            }
            return "";
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public static SexEnum[] valuesNotNull(){
            return new SexEnum[]{M,F};
        }
    }

    public enum IsCreditEnum {

        FO("0","否"),
        YD("1","简版征信"),
        YY("2","详版征信"),
        QBT("3","铅笔头征信");

        private String name;
        private String value;

        IsCreditEnum(String value,String name){
            this.name = name;
            this.value = value;
        }

        public static String getOneName(String index){
            for(IsCreditEnum e: IsCreditEnum.values()){
                if(e.getValue().equals(index)){
                    return e.getName();
                }
            }
            return "";
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public static IsCreditEnum[] valuesNotNull(){
            return new IsCreditEnum[]{FO,YD,YY,QBT};
        }
    }

    public enum BankCardTypeEnum{
        XYK("0","信用卡"),JJK("1","借记卡");
        private String name;
        private String value;

        BankCardTypeEnum(String value,String name){
            this.name = name;
            this.value = value;
        }

        public static String getNameByValue(String value){
            for(BankCardTypeEnum bankCardTypeEnum: BankCardTypeEnum.values()){
                if(bankCardTypeEnum.getValue().equals(value)){
                    return bankCardTypeEnum.getName();
                }
            }
            return "";
        }

        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getValue() {
            return value;
        }


        public void setValue(String value) {
            this.value = value;
        }

    }
    /**
     * 是否有驾驶证
     */
    public enum DriverLicenseEnum {

        NO("0","否"),
        YES("1","是");

        private String value;
        private String name;

        DriverLicenseEnum(String value,String name){
            this.name = name;
            this.value = value;
        }

        public static String getOneName(String index){
            for(IsCreditEnum e: IsCreditEnum.values()){
                if(e.getValue().equals(index)){
                    return e.getName();
                }
            }
            return "";
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }

        public static DriverLicenseEnum[] valuesNotNull(){
            return new DriverLicenseEnum[]{NO,YES};
        }
    }



}
