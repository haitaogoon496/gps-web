package com.mljr.gps.export;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.mljr.enums.LogTitleEnum;
import com.mljr.excel.export.AbstractExportHandler;
import com.mljr.excel.export.Column;
import com.mljr.excel.export.Export;
import com.mljr.gps.component.SessionUserComponent;
import com.mljr.gps.entity.GpsCompositeQuery;
import com.mljr.gps.facade.GpsCompositeQueryFacade;
import com.mljr.gps.form.GpsCompositeQueryForm;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:   我的gps审核领单导出
 * @Date : 2018/9/5 17:26
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Export(value = "gpsMineApprExport")
public class GpsMineApprExport extends AbstractExportHandler {
    private final String LOG_TITLE = LogTitleEnum.GPS_APPLY_FORM.getName();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GpsCompositeQueryFacade gpsCompositeQueryFacade;
    @Autowired
    private SessionUserComponent sessionUserComponent;

    public GpsMineApprExport() {
        super(MessageFormat.format("export_{0}", String.valueOf(System.currentTimeMillis())));
    }

    @Override
    public List<?> getList(Map<String, ? extends Object> params) {
        List<GpsCompositeQuery> list = new ArrayList<>();
        try {
            GpsCompositeQueryForm form = new GpsCompositeQueryForm();
            BeanUtils.populate(form, params);
            PageForm<GpsCompositeQueryForm> pagingForm = new PageForm<>(getStart(),getLimit(),form);
            LOGGER.info("{}getCount异常,params={},pagingForm={}",LOG_TITLE, JSON.toJSON(params),JSON.toJSON(pagingForm));
            list.addAll(gpsCompositeQueryFacade.mineApprExportQuery(pagingForm).getData().getData());
        } catch (Exception e) {
            LOGGER.error("{}getList异常,form={}",LOG_TITLE, JSON.toJSON(params),e);
        }
        return list;
    }

    @Override
    public int getCount(Map<String, ? extends Object> params) {
        int count = 0;
        try {
            GpsCompositeQueryForm form = new GpsCompositeQueryForm();
            BeanUtils.populate(form, params);
            PageForm<GpsCompositeQueryForm> pagingForm = new PageForm<>(getStart(),getLimit(),form);
            LOGGER.info("{}getCount异常,params={},pagingForm={}",LOG_TITLE, JSON.toJSON(params),JSON.toJSON(pagingForm));
            count = gpsCompositeQueryFacade.queryMineApprExportCount(pagingForm);
        } catch (Exception e) {
            LOGGER.error("{}getCount异常,form={}",LOG_TITLE, JSON.toJSON(params),e);
        }
        return count;
    }

    @Override
    public List<Column> getColumns() {
        return ExportColumn.GPS_MINE_COLUMNS;
    }
}
