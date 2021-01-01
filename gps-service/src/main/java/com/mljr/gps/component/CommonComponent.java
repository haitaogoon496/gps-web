package com.mljr.gps.component;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.enums.UserOperateLogConstant.AuthModelEnum;
import com.lyqc.base.enums.UserOperateLogConstant.AuthTypeEnum;
import com.mljr.gps.base.entity.BaseEntity;
import com.mljr.gps.base.service.BaseService;
import com.mljr.gps.entity.SyRole;
import com.mljr.gps.form.SyRoleForm;
import com.mljr.gps.service.SyRoleService;
import com.mljr.gps.service.SyUserService;
import com.mljr.gps.vo.SyUserVo;
import com.mljr.util.CollectionsTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @description: 共同处理facade
 * @Date : 2018/3/9$ 11:17$
 * @Author : liht
 */
@Component
public class CommonComponent {

    private static Logger logger = LoggerFactory.getLogger(CommonComponent.class);
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;

    @Autowired
    SyUserService syUserService;
    @Autowired
    SyRoleService syRoleService;

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param authModelEnum
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnum authModelEnum, String logTitle, BaseService service, Consumer<BaseEntity> c1){
        return saveRecord(record,authModelEnum,logTitle,service,c1,c -> {},p -> record.isInsert());
    }

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param authModelEnum
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @param predicate 鉴定回调，判断当前是否新增操作
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnum authModelEnum, String logTitle, BaseService service,
                                     Consumer<BaseEntity> c1, Predicate<BaseEntity> predicate){
        return saveRecord(record,authModelEnum,logTitle,service,c1,c -> {},predicate);
    }

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param authModelEnum
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnum authModelEnum, String logTitle, BaseService service,
                                     Consumer<BaseEntity> c1,Consumer<BaseEntity> c2){
        return saveRecord(record,authModelEnum,logTitle,service,c1,c2,x -> record.isInsert());
    }

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param authModelEnum
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @param c2 回调函数，后置处理
     * @param predicate 鉴定回调，判断当前是否新增操作
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnum authModelEnum, String logTitle, BaseService service,
                                     Consumer<BaseEntity> c1, Consumer<BaseEntity> c2, Predicate<BaseEntity> predicate){
        logger.info("{} - saveRecord 参数record:{}",logTitle, JSON.toJSON(record));
        try {
            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(record);
            if (CollectionsTools.isNotEmpty(violations)) {
                return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
            }
            c1.accept(record);
            if (predicate.test(record)) {
                service.insertRecord(record);
                this.saveLog(AuthTypeEnum.CREATE, authModelEnum, MessageFormat.format("新增record={0}", JSON.toJSON(record)));
            } else {
                service.updateRecord(record);
                this.saveLog(AuthTypeEnum.UPDATE, authModelEnum, MessageFormat.format("修改record={0}", JSON.toJSON(record)));
            }
            c2.accept(record);
        } catch (DuplicateKeyException e) {
            throw e;
        } catch (Exception e) {
            logger.error("{}保存异常,record={}", logTitle, JSON.toJSON(record), e);
            return Result.failInServer("保存异常");
        }
        return Result.suc();
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public void saveLog(AuthTypeEnum authTypeEnum,AuthModelEnum authModelEnum,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authModelEnum.getName()+authDetail);
            log.setAuthModel(UserOperateLogConstant.AuthModelEnumForProduct.PD_CALC_MODEL);
            log.setAuthType(authTypeEnum);
        });
    }
    /**
     * 通用删除操作
     * @param primaryKey 主键ID
     * @param logTitle 日志标题
     * @param service service
     * @param consumer
     * @param <PK> 主键
     * @return
     */
    public <PK> Result<String> deleteRecord(PK primaryKey,String logTitle, BaseService service, Consumer consumer){
        logger.info("{} - deleteRecord 参数:{}",logTitle,primaryKey);
        try {
            int row = service.deleteRecord(primaryKey);
            consumer.accept(primaryKey);
            if(row == 0){
                return Result.failInEmptyRecord("没找到相关记录");
            }
        } catch (Exception e) {
            logger.error("{}删除异常,id={}",logTitle,primaryKey,e);
            return Result.failInServer("删除异常");
        }
        return Result.suc();
    }

    public Result<List<SyUserVo>> listApprovalUserList() {
        List<SyUserVo> syUsers = syUserService.listApprovalUserList();
        if (CollectionsTools.isEmpty(syUsers)) {
            return Result.suc(Collections.emptyList());
        }
        SyRoleForm form = new SyRoleForm();
        form.setRoleName("GPS审核领单测试人员");
        List<SyRole> roleList = this.syRoleService.queryList(form);
        logger.info("listApprovalUserList - userRole:{}", JSON.toJSON(roleList));
        if (CollectionsTools.isNotEmpty(roleList)) {
            List<Integer> userIds = roleList.stream().map(role -> role.getUserId()).collect(Collectors.toList());
            syUsers.forEach(user->{
                if (userIds.contains(user.getUserId())) {
                    user.setTest(true);
                }
            });
        }
        return Result.suc(syUsers);
    }

}
