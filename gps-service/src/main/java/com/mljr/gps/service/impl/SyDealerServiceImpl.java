package com.mljr.gps.service.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.gps.entity.SyDealer;
import com.mljr.gps.form.SyDealerForm;
import com.mljr.gps.mapper.SyDealerMapper;
import com.mljr.gps.service.SyDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lingyu.shang
 */
@Service
public class SyDealerServiceImpl implements SyDealerService {

    @Autowired
    SyDealerMapper syDealerMapper;

    @Override
    public List<SyDealer> loadRecords(PageForm<SyDealerForm> form) {
        return syDealerMapper.pageQuery(form);
    }

}
