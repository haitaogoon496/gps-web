package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.SyUserConstant.UserStatusEnum;
import com.mljr.enums.LogTitleEnum;
import com.mljr.gps.common.consts.CodeConst;
import com.mljr.gps.common.enums.UserLevelEnum;
import com.mljr.gps.common.exception.AlertException;
import com.mljr.gps.common.util.SystemUtil;
import com.mljr.gps.component.UserLogComponent;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.entity.SyUser;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.param.LoginParam;
import com.mljr.gps.service.SyRoleService;
import com.mljr.gps.service.SyUserService;
import com.mljr.gps.vo.LoginVo;
import com.mljr.gps.vo.SyItemVo;
import com.mljr.redis.service.RedisUtil;
import com.mljr.util.CollectionsTools;
import com.mljr.util.DESUtil;
import com.mljr.util.MD5;
import eu.bitwalker.useragentutils.UserAgent;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @description: 登录Facade
 * @Date : 上午9:57 2018/3/4
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class LoginFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.USER_LOGIN.getName();

    private final int USER_LOGIN = 1;
    private final int USER_LOGOUT= 2;
    private final List<String> PRODUCT_MANAGER = Arrays.asList("产品相关","系统管理员");

    @Autowired
    private SyUserService syUserService;
    @Autowired
    private SyRoleService syRoleService;
    @Autowired
    private UserLogComponent userLogComponent;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SyItemFacade syItemFacade;

    /**
     * 登录
     * @param request
     * @param param
     * @return
     */
    public Result<LoginVo> login(HttpServletRequest request, LoginParam param){
        LOGGER.info("{} - login param:{}",LOG_TITLE,JSON.toJSON(param));
        try {
            HttpSession session = request.getSession();
            String username = param.getUsername();
            String password = param.getPassword();
            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(param);
            if (CollectionsTools.isNotEmpty(violations)) {
                return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
            }

            SyUser syUser = this.syUserService.queryByUsername(username);
            if (syUser == null) {
                return Result.fail(RemoteEnum.USER_NOT_EXISTS);
            } else {
                UserStatusEnum userStatusEnum = UserStatusEnum.getByCode(syUser.getUserStatus());
                if (UserStatusEnum.N != userStatusEnum) {
                    return Result.fail(601, "此用户无效");
                } else {
                    String pwd = syUser.getPassword();
                    if (!(pwd).equals(MD5.getMD5Str(password))) {
                        return Result.fail(602, "用户名或者密码错误");
                    } else {
                        String systemCode = SystemUtil.SYSTEM_CODE;
                        session.setAttribute("systemCode", systemCode);
                        session.setAttribute("user", syUser);
                        session.setAttribute("userId", syUser.getUserId());
                        LOGGER.info("{}Login-sessionId={}", LOG_TITLE, session.getId());
                        SyUser record = new SyUser();
                        record.setUserId(syUser.getUserId());
                        record.setLoginTimes(syUser.getLoginTimes() + 1);
                        record.setLastTime(new Date());
                        redisUtil.set(session.getId(), syUser, 12 * 60 * 60);//把用户sesssion存储到redis，设置30分钟过期时间
                        this.syUserService.updateRecord(record);//更新登录次数和最后时间
                        this.saveUserLog(request, "用户登录", USER_LOGIN);//保存用户登录日志
                        String userLevel = userLevel(syUser);
                        LOGGER.info("userLevel - :{}", userLevel);
                        LoginVo loginVo = new LoginVo();
                        loginVo.setUserLevel(userLevel);
                        Result<List<SyItemVo>> itemResult = syItemFacade.querySyItemsByRoles(syUser);
                        if (!itemResult.isSuccess()) {
                            throw new AlertException("获取权限失败,请联系管理员!");
                        }
                        if (CollectionUtils.isEmpty(itemResult.getData())) {
                            throw new AlertException("当前用户没有菜单权限,请联系管理员");
                        }
                        loginVo.setSyItemVoList(itemResult.getData());
                        loginVo.setUserSign(DESUtil.encrypt(String.valueOf(syUser.getUserId().intValue()), LoginVo.DES_ENCRYPT_KEY));
                        loginVo.setTrueName(syUser.getTrueName());
                        return Result.suc(loginVo, RemoteEnum.SUCCESS, "登录成功");
                    }
                }
            }
        } catch (AlertException e) {
            LOGGER.error("{}异常,form={}", LOG_TITLE, JSON.toJSON(param), e);
            return Result.fail(603, e.getMessage());
        } catch (Exception e) {
            LOGGER.error("{}异常,form={}", LOG_TITLE, JSON.toJSON(param), e);
            return Result.fail(603, "登录失败，请联系管理员");
        }
    }
    /**
     * 获取用户的级别
     * @param syUser
     * @return
     */
    private String userLevel(SyUser syUser) {
        LOGGER.info("userLevel - syUser:{}",JSON.toJSON(syUser));
        SyRoleForm form = new SyRoleForm();
        form.setUserId(syUser.getUserId());
        List<SyRole> roleList = this.syRoleService.queryList(form);
        LOGGER.info("userLevel - userRole:{}", JSON.toJSON(roleList));

        if (CollectionUtils.isEmpty(roleList)) {
            return UserLevelEnum.GUEST.name();
        }
        if (roleList.stream().filter(role -> PRODUCT_MANAGER.contains(role.getRoleName())).findFirst().isPresent()) {
            return UserLevelEnum.SA.name();
        }
        return UserLevelEnum.NORMAL.name();
    }

    /**
     * 用户退回
     * @param request
     * @return
     */
    public Result<String> logout(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user == null) {
                user = redisUtil.get(session.getId(),SyUser.class);
            }
            if (user == null) {
                return Result.suc("退出成功");
            }
            LOGGER.info("用户退出-userId:{}",((SyUser)user).getUserName());
            this.saveUserLog(request,"用户退回",USER_LOGOUT);
            session.invalidate();
            this.redisUtil.del(session.getId());
        } catch (Exception e) {
            LOGGER.error("{}异常",LOG_TITLE,e);
            return Result.fail(CodeConst.E_400,"退出异常,请联系管理员");
        }
        return Result.suc();
    }

    /**
     * 保存用户日志
     * @param request
     * @param desc
     * @param type
     */
    public void saveUserLog(HttpServletRequest request,String desc,Integer type){
        final String ipAddress = SystemUtil.getRemoteIP(request);
        UserAgent userAgent = SystemUtil.getUserAgent(request);
        this.userLogComponent.log(log -> {
            log.setIpAddress(ipAddress);
            log.setType(type);
            log.setOperateDesc(desc);
            log.setBrowser(JSON.toJSONString(userAgent));
        });
    }
}
