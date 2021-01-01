package com.mljr.gps.export;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.page.PageForm;
import com.mljr.excel.export.AbstractExportHandler;
import com.mljr.excel.export.Column;
import com.mljr.excel.export.Export;
import com.mljr.enums.LogTitleEnum;
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
 * @description: GPS综合查询Excel导出
 * @Date : 2018/6/3 上午11:34
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Export(value = "gpsCompositeQueryExport")
public class GpsCompositeQueryExport extends AbstractExportHandler {
    private final String LOG_TITLE = LogTitleEnum.GpsCompositeQuery.getName();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GpsCompositeQueryFacade gpsCompositeQueryFacade;
    /**
     * 实例
     */
    public GpsCompositeQueryExport() {
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
            list.addAll(gpsCompositeQueryFacade.query(pagingForm).getData().getData());
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
            count = gpsCompositeQueryFacade.queryCount(pagingForm);
        } catch (Exception e) {
            LOGGER.error("{}getCount异常,form={}",LOG_TITLE, JSON.toJSON(params),e);
        }
        return count;
    }

    @Override
    public List<Column> getColumns() {
        return ExportColumn.GPS_GENERAL_COLUMNS;
    }
}
