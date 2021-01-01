package com.mljr.gps.component;

import com.lyqc.base.common.Result;
import com.lyqc.base.enums.DealerTypeEnum;
import com.lyqc.base.enums.EnumDesc;
import com.lyqc.base.enums.EnumValue;
import com.mljr.gps.common.enums.DictionaryEnum;
import com.mljr.gps.entity.SyArgControl;
import com.mljr.gps.form.SyArgControlForm;
import com.mljr.gps.service.SyArgControlService;
import com.mljr.gps.vo.SyArgControlVo;
import com.mljr.util.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:数据字典组件
 * @Date : 下午6:27 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class DictionaryComponent{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SyArgControlService syArgControlService;
    /**
     * 是否直营
     */
    private final boolean isZy = "100000".equals(PropertiesReader.getAppointPropertiesAttribute("config","system_code",String.class));

    /**
     * 基于枚举的接口
     * @param code 数据字典code
     * @return Result<List<SyArgControlVo>>
     */
    public Result<List<SyArgControlVo>> enums(String code){
        logger.info("enums方法 - code:{}", code);
        List<SyArgControlVo> list = new ArrayList<>();
        EnumValue[] enums = DictionaryEnum.getEnumsByCode(code);
        if(null != enums){
            for(EnumValue value : enums){
                if(filter(value)){
                    SyArgControlVo argVo = new SyArgControlVo(value.getIndex(),value.getName(),String.valueOf(value.getIndex()),String.valueOf(value.getName()));
                    specialReset(value,argVo);
                    list.add(argVo);
                }
            }
        }
        return Result.suc(list);
    }

    /**
     * 特殊重置
     * @param value
     * @param argVo
     * @return
     */
    protected void specialReset(EnumValue value,SyArgControlVo argVo){
        if(value instanceof EnumDesc){
            EnumDesc enumDesc = (EnumDesc)value;
            argVo.setValue(enumDesc.getDesc());
        }
    }

    /**
     * 针对枚举的业务场景的特殊过滤处理
     * @param value
     * @return
     */
    protected boolean filter(EnumValue value){
        boolean filter = true;
        // 门店类型，对于直营 需要过滤掉
        if(value instanceof DealerTypeEnum && isZy){
            filter = false;
            DealerTypeEnum[] enums = DealerTypeEnum.valuesBySystem(isZy);
            List<Integer> list = new ArrayList<>(enums.length);
            for(DealerTypeEnum each : enums){
                list.add(each.getIndex());
            }
            if(list.contains(value.getIndex())){
                filter = true;
            }
        }
        return filter;
    }

    /**
     * 基于数据字典的接口
     * @param conArgType
     * @return Result<List<SyArgControlVo>>
     */
    public Result<List<SyArgControlVo>> options(Integer conArgType){
        logger.info("options方法 - conArgType:{}", conArgType);
        SyArgControlForm form = new SyArgControlForm();
        form.setConArgType(conArgType);
        form.setConArgStatus(1);
        List<SyArgControl> args = this.syArgControlService.queryList(form);
        List<SyArgControlVo> list = new ArrayList<>();
        args.forEach(each ->
            list.add(new SyArgControlVo(each.getrId(),each.getConArgName(),each.getConArgCode(),each.getConArgValue()))
        );
        return Result.suc(list);
    }

    /**
     * 批量查询基于静态枚举的接口
     * @param enumList
     * @return Result<Map<String,List<SyArgControlVo>>>
     */
    public Result<Map<String,List<SyArgControlVo>>> enumsList(@RequestBody List<String> enumList){
        Map<String,List<SyArgControlVo>> resultMap = new HashMap<>(enumList.size());
        for(DictionaryEnum dictionaryEnum : DictionaryEnum.values()){
            String enumCode = dictionaryEnum.getCode();
            if(enumList.contains(dictionaryEnum.getCode())){
                resultMap.put(enumCode,enums(enumCode).getData());
            }
        }
        return Result.suc(resultMap);
    }
    /**
     * 批量查询基于数据字典的接口
     * @param optionList
     * @return Result<Map<String,List<SyArgControlVo>>>
     */
    public Result<Map<String,List<SyArgControlVo>>> optionList(@RequestBody List<Integer> optionList){
        Map<String,List<SyArgControlVo>> resultMap = new HashMap<>(optionList.size());
        for(Integer option : optionList){
            resultMap.put(option.toString(),options(option).getData());
        }
        return Result.suc(resultMap);
    }

}
