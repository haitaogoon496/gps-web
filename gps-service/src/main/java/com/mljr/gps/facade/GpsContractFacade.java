package com.mljr.gps.facade;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.gps.component.CommonComponent;
import com.mljr.gps.component.UserOperateLogComponent;
import com.mljr.gps.entity.GpsContract;
import com.mljr.gps.form.GpsContractForm;
import com.mljr.gps.service.GpsContractService;
import com.mljr.gps.vo.GpsContractVo;
import com.mljr.gps.voconvertor.GpsContractVoConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description:GPS安装联系人
 * @Date : 2018/8/21 15:00
 * @Author : lihaitao
 */
@Component
public class GpsContractFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.GPS_CONTRACT.getName();
    private final UserOperateLogConstant.AuthModelEnum authModelEnum = UserOperateLogConstant.AuthModelEnum.FUND;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private UserOperateLogComponent userOperateLogComponent;

    @Autowired
    private GpsContractService gpsContractService;

    @LogMonitor("车辆GPS安装联系人[列表查询]")
    public Result<PageVO<GpsContractVo>> loadRecords(PageForm<GpsContractForm> form) {
        List<GpsContractVo> gpsContractVos = Collections.emptyList();
        int count = 0;
        try {
            List<GpsContract> gpsContracts = gpsContractService.queryByPage(form);
            gpsContractVos = new GpsContractVoConvertor().convertList(gpsContracts);
            count = gpsContractService.queryCount(form);
        }catch (Exception e){
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.fail(new PageVO<>(form.getDraw(), count, gpsContractVos));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,gpsContractVos));
    }


    public Result<GpsContractVo> queryRecord(Integer primaryKey) {
        GpsContractVo vo = null;
        try {
            GpsContract gpsContract = this.gpsContractService.queryRecord(primaryKey);
            if(null != gpsContract ){
                vo = new GpsContractVoConvertor().convert(gpsContract);
            }else{
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }
    @LogMonitor("GPS安装单联系人")
    public Result<GpsContractVo> queryRecordByAppCode(String appCode) {
        GpsContractVo vo = null;
        try {
            GpsContract gpsContract = this.gpsContractService.queryRecordByAppCode(appCode);
            if(null != gpsContract ){
                vo = new GpsContractVoConvertor().convert(gpsContract);
            }else{
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,appCode,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @LogMonitor("车辆GPS安装联系人[更新]")
    public Result<String> saveRecord(GpsContract record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,gpsContractService, baseEntity -> {
            GpsContract res = (GpsContract)baseEntity;
            res.setCreateTime(new Date());
        });
    }


    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey, LOG_TITLE, gpsContractService, (PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK)));
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            //log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }
}
