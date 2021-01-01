package com.mljr.gps.vo.statistic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @Date : 2018/12/25$ 11:18$
 * @Author : liht
 */
@Data
public class GpsFailTimesOfDeviceVo {

    @ApiModelProperty(value = "gps失败次数")
    private Integer times;
    @ApiModelProperty(value = "gps设备集合")
    private Set<String> gpsNos;

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false ;
        else{
            if (obj instanceof GpsFailTimesOfDeviceVo){
                GpsFailTimesOfDeviceVo c = (GpsFailTimesOfDeviceVo) obj;
                if(c.times == this.times){
                    return true ;
                }
            }
        }
        return false ;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + times.hashCode();
        return result;
    }

}
