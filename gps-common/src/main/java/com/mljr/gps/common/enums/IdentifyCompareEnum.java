package com.mljr.gps.common.enums;

/**
 * 车况枚举
 */
public enum IdentifyCompareEnum {
	
	NULL("","--请选择--"),
	JH("0","不一致"),
	LH("1","一致"),
	PJ("2","无记录");
	
	private String name;
	private String value;
	
	private IdentifyCompareEnum(String value, String name){
		this.name = name;
		this.value = value;
	}

	public static String getNameByValue(String value){
	    for(IdentifyCompareEnum identifyCompareEnum:IdentifyCompareEnum.values()){
	        if(identifyCompareEnum.getValue().equals(value)){
	            return identifyCompareEnum.getName();
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
	
}
