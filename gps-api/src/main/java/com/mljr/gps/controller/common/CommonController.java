package com.mljr.gps.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.mljr.gps.facade.CommonFacade;
import com.mljr.model.PdConfigParams;
import com.mljr.facade.PdConfigParamsFacade;
import com.mljr.redis.service.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 通用模块 Controller
 * @Date : 2018/4/18 下午7:59
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/common")
@Api(description = "公共模块API",tags = "公共模块API")
public class CommonController {
    @Autowired
    private PdConfigParamsFacade pdConfigParamsFacade;
    @Autowired
    private CommonFacade commonFacade;
    @Autowired
    RedisUtil redisUtil;
    /**
     * 全局参数刷新Redis
     * @return Result<Boolean>
     */
    @ApiOperation("【通用模块】全局参数刷新Redis")
    @GetMapping("/flushRedisForParams")
    public Result<Boolean> flushRedis(){
        pdConfigParamsFacade.flushRedis();
        return Result.suc(Boolean.TRUE);
    }

    /**
     * 按参数key刷新Redis
     * @return Result<PdConfigParams>
     */
    @ApiOperation("【通用模块】按参数key刷新Redis")
    @GetMapping("/flushRedisForParams/{paramKey}")
    public Result<PdConfigParams> flushRedis(@PathVariable String paramKey){
        return Result.suc(pdConfigParamsFacade.flushRedis(paramKey));
    }

    /**
     * 获取全局参数开关
     * @return Result<Boolean>
     */
    @ApiOperation("【通用模块】获取全局参数开关")
    @GetMapping("/getSwitch/{paramKey}")
    public Result<Boolean> getSwitch(@PathVariable String paramKey){
        return Result.suc(pdConfigParamsFacade.getSwitchValue(paramKey));
    }

    /**
     * 获取全局参数开关
     * @return Result<Boolean>
     */
    @ApiOperation("【通用模块】获取全局参数开关")
    @GetMapping("/getSwitchFromCache/{paramKey}")
    public Result<String> getSwitchFromCache(@PathVariable String paramKey){
        return Result.suc(pdConfigParamsFacade.getValueByParamKey(paramKey,String.class));
    }

    /**
     * 删除redis数据
     * @return Result<Boolean>
     */
    @ApiOperation("【通用模块】删除redis数据")
    @PostMapping("/delRedisByKey")
    public Result<Object> delRedisByKey(@RequestBody JSONObject jsonObject){
        return commonFacade.delRedisByKey(jsonObject);
    }

    /**
     * 获取调用方法缓存参数
     * @return Result<Object>
     */
    @ApiOperation("【通用模块】获取调用方法缓存参数")
    @GetMapping("/getCacheParam/{redisKey}")
    public Result<Object> getCacheParam(@PathVariable String redisKey){
        return Result.suc(redisUtil.hashOperations().entries(redisKey));
    }

    /**
     * 获取调用方法缓存结果
     * @return Result<Object>
     */
    @ApiOperation("【通用模块】获取调用方法缓存结果")
    @GetMapping("/getCacheResult/{redisKey}")
    public Result<Object> getCacheResult(@PathVariable String redisKey){
        return Result.suc(redisUtil.get(redisUtil.getKeyWithSystemCode(redisKey),String.class));
    }

    /**
     * 删除Redis缓存
     * @return Result<Object>
     */
    @ApiOperation("【通用模块】删除Redis缓存")
    @GetMapping("/deleteRedis/{redisKey}")
    public Result<Object> deleteRedis(@PathVariable String redisKey){
        return Result.suc(redisUtil.del(redisKey));
    }

}
