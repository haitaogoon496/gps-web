package com.mljr.gps.common.enums;


public enum IdTypeEnum {
	NULL("","--请选择"),
	JUMIN("15","居民身份证"),
	LINSHI("16","临时身份证"),
	JUNREN("17","军人身份证件"),
	WUJING("18","武警身份证件"),
	TONGXING("19","通行证"),
	HUZHAO("20","护照"),
	QITA("21","其他"),
	LSHUKOU("22","临时户口"),
	HUKOUB("23","户口簿"),
	BJING("24","边境证"),
	FJULIU("25","外国人居留证"),
	SFZHENGM("26","身份证明"),
	ZJCF1("27","证件重复1"),
	ZJCF2("28","证件重复2"),
	LMINGH("29","联名户");
	
	private String name;
	private String value;
	
	private IdTypeEnum(String value, String name){
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public static String getNameByValue(String value){
		for(IdTypeEnum type :IdTypeEnum.values()){
			if(type.getValue().equals(value)){
				return type.getName();
			}
		}
		return "";
	}
}
