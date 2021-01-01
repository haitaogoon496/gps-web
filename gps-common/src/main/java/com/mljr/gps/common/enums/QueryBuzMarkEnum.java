package com.mljr.gps.common.enums;

import com.lyqc.base.enums.EnumValue;

/**
 * @description: SQL Mapper查询标示字段
 * @Date : 2018/6/27 下午12:06
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public enum QueryBuzMarkEnum implements EnumValue {
    TobeUploadOnApply(1,"TobeUploadOnApply"),//待上传GPS安装单
    InstalledOnApply(2, "InstalledOnApply"),//GPS安装单列表
    UnInstalledGps(3,"UnInstalledGps"),//GPS安装单未上传
    InstalledGps(4, "InstalledGps"),//GPS安装单已上传
    GpsApprovePull(5,"GpsApprovePull"),//GPS审核领单
    GpsApproveMine(6, "GpsApproveMine"),//我的GPS领单
    GpsGeneral(7, "GpsGeneral"),//GPS综合查询
    ;
    QueryBuzMarkEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    private int index;
    private String name;

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 根据索引获取名称
     * @param index 索引
     * @return
     */
    public static String getNameByIndex(int index){
        for(QueryBuzMarkEnum e : QueryBuzMarkEnum.values()){
            if(e.getIndex() == index){
                return e.getName();
            }
        }
        return null;
    }

    /**
     * 根据索引获取枚举对象
     * @param index 索引
     * @return
     */
    public static QueryBuzMarkEnum getByIndex(int index){
        for(QueryBuzMarkEnum e : QueryBuzMarkEnum.values()){
            if(e.getIndex() == index){
                return e;
            }
        }
        return null;
    }
}
