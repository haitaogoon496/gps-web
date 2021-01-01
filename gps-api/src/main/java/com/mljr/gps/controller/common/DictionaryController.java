package com.mljr.gps.controller.common;

import com.lyqc.base.common.Result;
import com.mljr.gps.component.DictionaryComponent;
import com.mljr.gps.vo.SyArgControlVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description: 数据字典Controller
 * @Date : 下午6:25 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/dictionary")
@Api(description = "【数据字典】相关api",tags = "DictionaryController")
public class DictionaryController {
    @Autowired
    private DictionaryComponent dictionaryComponent;
    /**
     * 基于数据字典的接口
     * @param code
     * @return Result<List<SyArgControlVo>>
     */
    @RequestMapping(value = "/enums/{code}",method = {RequestMethod.GET})
    @ApiOperation("【数据字典】查询列表")
    public Result<List<SyArgControlVo>> enums(@PathVariable String code){
        return this.dictionaryComponent.enums(code);
    }
    /**
     * 基于数据字典的接口
     * @param conArgType
     * @return Result<List<SyArgControlVo>>
     */
    @RequestMapping(value = "/options/{conArgType}",method = {RequestMethod.GET})
    @ApiOperation("【数据字典】查询列表")
    public Result<List<SyArgControlVo>> options(@PathVariable Integer conArgType){
        return this.dictionaryComponent.options(conArgType);
    }

    /**
     * 批量查询基于静态枚举的接口
     * @param enumList
     * @return Result<Map<String,List<SyArgControlVo>>>
     */
    @PostMapping("/enumList")
    @ApiOperation("批量查询基于静态枚举的接口")
    public Result<Map<String,List<SyArgControlVo>>> enumList(@RequestBody List<String> enumList){
        return this.dictionaryComponent.enumsList(enumList);
    }

    /**
     * 批量查询基于数据字典的接口
     * @param optionList
     * @return Result<Map<String,List<SyArgControlVo>>>
     */
    @PostMapping("/optionList")
    @ApiOperation("批量查询基于数据字典的接口")
    public Result<Map<String,List<SyArgControlVo>>> optionList(@RequestBody List<Integer> optionList){
        return this.dictionaryComponent.optionList(optionList);
    }
}
