package com.mljr.gps.component;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.AppInfo;
import com.mljr.gps.form.GpsQueryListForm;
import com.mljr.gps.vo.GpsQueryListVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @Date : 2019/4/24$ 19:03$
 * @Author : liht
 */
@Component
public class CreditClientComponent {

    @Resource
    private RestTemplate restTemplate;

    @Value("{credit.server.url}")
    private String creditUrl;

    public List<AppInfo> getAppInfos(PageForm<GpsQueryListForm> gpsQueryListFormPageForm) {

        String url = "creditUrl" + "";
        ResponseEntity<GpsQueryListVo> gpsQueryListVo = restTemplate.postForEntity(url, new HashMap<>().put("",""), GpsQueryListVo.class);

        return null;
    }
}
