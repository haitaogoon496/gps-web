package com.mljr.gps.facade;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.gps.base.service.BaseFacade;
import com.mljr.gps.entity.SyDealer;
import com.mljr.gps.form.SyDealerForm;
import com.mljr.gps.vo.DealerVo;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Date : 2018/3/21$ 17:10$
 * @Author : liht
 */
@Service
public class SyDealerFacade implements BaseFacade<DealerVo,SyDealer,Integer,SyDealerForm> {

    @Override
    public Result<PageVO<DealerVo>> loadRecords(PageForm<SyDealerForm> form) {
        return null;
    }

    @Override
    public Result<DealerVo> queryRecord(Integer primaryKey) {
        return null;
    }

    @Override
    public Result<String> saveRecord(SyDealer record) {
        return null;
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return null;
    }
}
