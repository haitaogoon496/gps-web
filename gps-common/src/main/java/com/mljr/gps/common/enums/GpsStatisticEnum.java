package com.mljr.gps.common.enums;

/**
 * @description:
 * @Date : 2018/12/25$ 11:01$
 * @Author : liht
 */
public enum  GpsStatisticEnum {

    GPS_AUDIT_DEVICE(1, "以设备为基准统计"),
    GPS_AUDIT_TIMES(2, "以次数为基准进行统计");

    private int index;
    private String desc;

    GpsStatisticEnum(Integer index,String desc) {
        this.index = index;
        this.desc = desc;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
