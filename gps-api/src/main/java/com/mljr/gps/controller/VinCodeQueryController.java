package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import com.lyqc.base.vin.VehicleVO;
import com.mljr.gps.facade.VinCodeQueryFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description: vin码查询接口
 * @author: zhaoxin
 * @date: 2018/10/19 下午2:47
 **/
@RestController
@RequestMapping(value = "/vincode")
@Api(description = "V码查询车型", tags = "VinCodeQueryController")
@Slf4j
public class VinCodeQueryController {

    @Autowired
    private VinCodeQueryFacade vinCodeQueryFacade;

    @RequestMapping(value = "/getCarModels/{vinCode}",method = {RequestMethod.GET})
    @ApiOperation("根据V码查询车型")
    public Result<VehicleVO> getCarModels(@PathVariable String vinCode){
        return  vinCodeQueryFacade.getCarModels(vinCode);
    }

}
