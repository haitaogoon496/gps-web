package com.mljr.gps.component;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.mljr.redis.service.RedisUtil;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.entity.SyUserLog;
import com.mljr.gps.service.SyUserLogService;
import com.mljr.gps.vo.SyUserLogVo;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * @description: 用户日志 工具类
 * @Date : 下午6:30 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class UserLogComponent {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SyUserLogService syUserLogService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 保存用户日志
     * @param log
     */
    public Result<String> log(SyUserLogVo log){
        try {
            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(log);
            if (CollectionsTools.isNotEmpty(violations)) {
                return Result.failWithEmptyParam(violations.get(0).getMessage());
            }
            SyUserLog record = new SyUserLog();
            record.setUserId(log.getUserId());
            record.setUserName(log.getUserName());
            record.setCreateTime(TimeTools.format4YYYYMMDDHHMISS(new Date()));
            record.setIpAddress(log.getIpAddress());
            record.setBrowser(log.getBrowser());
            record.setOperateDesc(log.getOperateDesc());
            record.setType(log.getType());
            syUserLogService.insertRecord(record);
        } catch (Exception e) {
            LOGGER.error("用户日志保存异常,form={}", JSON.toJSON(log),e);
        }
        return Result.suc();
    }
    /**
     * 保存用户日志
     * @param consumer
     */
    public Result<String> log(Consumer<SyUserLogVo> consumer){
        SyUserLogVo log = new SyUserLogVo();
        try{
            consumer.accept(log);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            SyUser user = redisUtil.get(session.getId(),SyUser.class);
            log.setUserId(user.getUserId());
            log.setUserName(user.getUserName());
            log(log);
        } catch (Exception e) {
            LOGGER.error("用户日志保存异常,form={}", JSON.toJSON(log),e);
        }
        return Result.suc();
    }
}
