package com.mljr.gps.component;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.AppPushDTO;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.PushConstant;
import com.mljr.util.OkHttpUtil;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @description: APP Push 组件类
 * @Date : 2018/7/28 下午12:10
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class PushComponent {
    private final String TITLE = "推送客户端PUSH";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    /**
     * 调用地址
     */
    @Value("${crmPushApi}")
    private String crmPushApi;
    /**
     * 推送Push Message接口
     * @param appCode
     * @param consumer
     */
    @Async
    public void pushMessage(String appCode, Consumer<AppPushDTO> consumer){
        AppPushDTO pushDTO = new AppPushDTO();
        try {
            pushDTO.setAppCode(appCode);
            pushDTO.setMsgTime(TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime()));
            pushDTO.setBizType(PushConstant.BizTypeEnum.GPS_APPROVAL.getIndex());
            pushDTO.setPushSource(PushConstant.PushSourceEnum.LYQC_CAS.getIndex());
            pushDTO.setPushType(PushConstant.PushTypeEnum.MESSAGE.getIndex());
            consumer.accept(pushDTO);
            LOGGER.info("{}:appCode={},pushUrl={},pushDTO={}",TITLE,appCode,crmPushApi, JSON.toJSON(pushDTO));
            String msg = OkHttpUtil.post(JSON.toJSONString(pushDTO),crmPushApi);
            LOGGER.info("{}:appCode={},msg={}",TITLE,appCode, msg);
        } catch (Exception e) {
            LOGGER.error("{}异常:appCode={},pushUrl={},pushDTO={}",TITLE,appCode,crmPushApi,JSON.toJSON(pushDTO),e);
        }
    }

    public static void main(String[] args) {
        AppPushDTO pushDTO = new AppPushDTO();
        try {
            final String url = "http://10.250.1.116:8098/pushMsm/";
            pushDTO.setAppCode("F1810181174800260001");
            pushDTO.setMsgTime(TimeTools.format4YYYYMMDDHHMISS(TimeTools.createNowTime()));
            pushDTO.setBizType(PushConstant.BizTypeEnum.GPS_APPROVAL.getIndex());
            pushDTO.setPushSource(PushConstant.PushSourceEnum.LYQC_CAS.getIndex());
            pushDTO.setPushType(PushConstant.PushTypeEnum.MESSAGE.getIndex());
            pushDTO.setStaffId(5273);
            pushDTO.setTitle("GPS审批管理");
            pushDTO.setContent("亲！订单号：F1810161800100360009，GPS审批主管【已审批通过】，请查收。");
            //System.out.println("AAA:"+HttpUtils.doPost("http://spweb.mljr.com/pushMsm/",JSON.toJSONString(pushDTO),"UTF-8"));
            //System.out.println("BBB:"+HttpUtils.post("http://spweb.mljr.com/pushMsm/",JSON.toJSONString(pushDTO),null));
            Result result = OkHttpUtil.post(Result.class,pushDTO,url);
            System.out.println(JSON.toJSON(result));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
