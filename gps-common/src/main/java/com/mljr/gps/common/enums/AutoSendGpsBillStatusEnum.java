package com.mljr.gps.common.enums;

import com.lyqc.base.enums.EnumValue;

/**
 * @author lingyu.shang
 */
public enum AutoSendGpsBillStatusEnum implements EnumValue {

    ON(1, "ON", "开启"), OFF(0, "OFF", "关闭");

    private int index;

    private String code;

    private String name;

    AutoSendGpsBillStatusEnum(int index, String code, String name) {
        this.index = index;
        this.code = code;
        this.name = name;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getCode() {
        return code;
    }

}
