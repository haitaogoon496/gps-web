package com.mljr.gps.service;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.SyDealer;
import com.mljr.gps.form.SyDealerForm;

import java.util.List;

/**
 * 门店Service
 * @author lingyu.shang
 */
public interface SyDealerService {

    /**
     * 分页加载门店数据
     * @param form
     * @return
     */
    List<SyDealer> loadRecords(PageForm<SyDealerForm> form);

}
