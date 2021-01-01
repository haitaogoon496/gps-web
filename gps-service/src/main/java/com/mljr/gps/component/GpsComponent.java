package com.mljr.gps.component;
import com.mljr.facade.PdConfigParamsFacade;
import com.mljr.gps.common.consts.GpsWebConstant;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.facade.GpsQueryListFacade;
import com.mljr.gps.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * @description: GPS组件类
 * @Date : 2018/6/15 下午1:29
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class GpsComponent {
    @Autowired
    private PdConfigParamsFacade pdConfigParamsFacade;
    @Autowired
    private AppInfoService appInfoService;
    /**
     * 设置GPS审批
     * @param consumer
     */
    public void updateAppInfo(Consumer<AppInfo> consumer){
        AppInfo appInfo = new AppInfo();
        consumer.accept(appInfo);
        appInfoService.updateForCarGps(appInfo);
    }

    /**
     * 获取GPS相关查询要过滤的订单状态集合
     * @param excludeStatusScope
     * @return
     */
    public List<String> getExcludeStatusScope(ExcludeStatusScope excludeStatusScope){
        List<String> redisValue = pdConfigParamsFacade.convertValueToListByParamKey(excludeStatusScope.name());
        if(null == redisValue || redisValue.isEmpty()){
            switch (excludeStatusScope){
                case APPROVE_PULL_EXCLUDE_SCOPE:
                    return GpsWebConstant.APPROVE_PULL_EXCLUDE_SCOPE;
                case APPROVE_MINE_EXCLUDE_SCOPE:
                    return GpsWebConstant.APPROVE_MINE_EXCLUDE_SCOPE;
                case INSTALLED_GPS_EXCLUDE_SCOPE:
                    return GpsWebConstant.INSTALLED_GPS_EXCLUDE_SCOPE;
                case UNINSTALLED_GPS_EXCLUDE_SCOPE:
                    return GpsWebConstant.UNINSTALLED_GPS_EXCLUDE_SCOPE;
            }
        }
        return redisValue;
    }

    /**
     * GPS相关查询要过滤的订单状态枚举对象
     */
    public enum ExcludeStatusScope{
        //GPS审核领单 - 要过滤的订单状态
        APPROVE_PULL_EXCLUDE_SCOPE,
        //我的GPS领单 - 要过滤的订单状态
        APPROVE_MINE_EXCLUDE_SCOPE,
        //GPS(未上传/已驳回)  - 要过滤的订单状态
        UNINSTALLED_GPS_EXCLUDE_SCOPE,
        //GPS安装单已上传 - 要过滤的订单状态
        INSTALLED_GPS_EXCLUDE_SCOPE
    }
}
