package com.mljr.gps.facade;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.mljr.annotation.LogMonitor;
import com.mljr.gps.common.consts.HeilCode;
import com.mljr.gps.common.util.ValidateUtils;
import com.mljr.redis.service.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @Date : 2019/1/8$ 18:41$
 * @Author : liht
 */
@Component
@Slf4j
public class CommonFacade {

    @Autowired
    private RedisUtil redisUtil;

    @LogMonitor("redis操作")
    public Result<Object> delRedisByKey(JSONObject jsonObject) {
        ValidateUtils.notNull(jsonObject, HeilCode.E_500, "参数不能为空");
        String type = jsonObject.getString("type");
        String key = jsonObject.getString("key");
        if (!StringUtils.isEmpty(key) && key.contains("*")) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM, "不支持*操作");
        }
        ValidateUtils.notEmpty(type, HeilCode.E_500, "type不能为空");
        Object[] values = jsonObject.getJSONArray("value") == null ? null : jsonObject.getJSONArray("value").toArray();
        log.info("values:{}", values);
        Object o = null;
        switch (type) {
            case "string":
                o = redisUtil.del(jsonObject.getString("key"));
                break;
            case "hash":
                o = redisUtil.hashOperations().delete(key, values);
                break;
            case "set":
                o = redisUtil.setOperations().remove(key,values);
                break;
            case "list":
                o = redisUtil.listOperations().remove(key,0,values[0]);
                break;
            case "zset":
                o = redisUtil.zSetOperations().remove(key,values);
                break;
            default:
                log.info("目前没有该类型的操作");
        }
        log.info("删除结果为:{}", o);
        return Result.suc(o);
    }
}
