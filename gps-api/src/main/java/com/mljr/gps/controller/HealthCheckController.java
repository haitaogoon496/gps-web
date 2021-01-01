package com.mljr.gps.controller;

import com.lyqc.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 健康检查
 * @Date : 2017/12/29 下午3:08
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@Api(description = "健康检查",tags = "HealthCheckController")
public class HealthCheckController {
    /**
     * 健康检查
     * @return
     */
    @RequestMapping(path = "/healthCheck", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> invoke() {
        return Result.suc();
    }
    
}
