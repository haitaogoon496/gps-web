package com.mljr.gps.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyqc.base.enums.EnumValue;

/**
 * 
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BooEnum implements EnumValue {

	NO(0,"否"),
	YES(1,"是");
	
	private String name;
	private int value;
	
	private BooEnum(int value, String name){
		this.name = name;
		this.value = value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getIndex() {
		return this.value;
	}
	
	public static BooEnum[] valuesNotNull(){
		return new BooEnum[]{YES,NO};
	}
	
	public static String getNameByValue(Integer value){
	    String name = "";
	    for(BooEnum booEnum:BooEnum.values()){
	        if(booEnum.value==value){
	            name=booEnum.getName();
	        }
	    }
	    return name;
	}
}
