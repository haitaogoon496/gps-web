package com.mljr.gps.common.enums;


public enum AnnexTypeEnum {
    ANNEXTYPE4(4,"申请附件"),
    ANNEXTYPE5(5,"贷前附件"),
    ANNEXTYPE6(6,"贷后附件"),
    ANNEXTYPE9(9,"电子授权书"),
    ANNEXTYPE28(28,"审批附件"),
    ANNEXTYPE29(29,"GPS安装单附件"),
    ANNEXTYPE36(36,"放款审核资料"),
    ANNEXTYPE38(38,"绿本资料"),
    ANNEXTYPE52(52,"浦发进件附件");
    
    private AnnexTypeEnum(int value, String name){
        this.name = name;
        this.value = value;
    }
    
    public static String getNameByValue(int value){
        for(AnnexTypeEnum annexTypeEnum:AnnexTypeEnum.values()){
            if(annexTypeEnum.getValue()==value){
                return annexTypeEnum.getName();
            }
        }
        return "";
    }

    private String name;
    
    private Integer value;

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public Integer getValue() {
        return value;
    }

    
    public void setValue(Integer value) {
        this.value = value;
    }
    
    
}
