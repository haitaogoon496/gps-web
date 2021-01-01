package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.vin.VehicleVO;
import com.lyqc.base.vin.VinResponseDto;
import com.mljr.annotation.LogMonitor;
import com.mljr.gps.common.enums.PoseidonProviderApiEnum;
import com.mljr.gps.common.util.StringUtils;
import com.mljr.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @description: vin码查询
 * @author: zhaoxin
 * @date: 2018/10/19 下午3:07
 **/
@Component
public class VinCodeQueryFacade {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${vin.carModelHost}")
    private String vinUrl;

    @LogMonitor("【根据vin码查询车型】")
    public Result<VehicleVO> getCarModels(String vinCode) {
        if (StringUtils.isEmpty(vinCode)) {
            return Result.fail("查询V码不能为空");
        }
        try {
            String data = HttpUtils.doGet(vinUrl + PoseidonProviderApiEnum.TRANSIENT_VINCODE.getName()+"?vin="+vinCode,"UTF-8");
            VinResponseDto<VehicleVO> responseDto = JSONObject.parseObject(data,VinResponseDto.class);
            if (responseDto !=null && responseDto.getData() != null && responseDto.isSuccess()){
                return Result.suc(responseDto.getData());
            }
        }catch (Exception e){
            LOGGER.error("根据vin码查询车型请求失败 vinCode={}", vinCode,e);
            return Result.fail(new VehicleVO());
        }
        return Result.suc(new VehicleVO());

    }
}
