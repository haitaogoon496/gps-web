package com.mljr.gps.export;

import com.mljr.excel.export.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Excel导出列
 * @Date : 2018/9/25 上午11:53
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public final class ExportColumn {
    /**
     * GPS综合查询
     */
    public static final List<Column> GPS_GENERAL_COLUMNS = new ArrayList<Column>(){{
        add(new Column("province").title("省份"));
        add(new Column("companyName").title("经销商单位"));
        add(new Column("auditDate").title("审核时间"));
        add(new Column("appCode").title("订单号"));
        add(new Column("carIdentify").title("车架号"));
        add(new Column("wireDeviceNo").title("有线设备号"));
        add(new Column("wirelessInstallType").title("无线安装方式"));
        add(new Column("customerName").title("客户姓名"));
        add(new Column("carSeries").title("车型"));
        add(new Column("carColor").title("车辆颜色"));
        add(new Column("wirelessDeviceNo").title("无线设备号"));
        add(new Column("loanPeriods").title("贷款年限"));
        add(new Column("wirelessGpsPosition").title("无线安装位置"));
        add(new Column("saleName").title("销售人"));
        add(new Column("approvalUserName").title("审核员"));
        add(new Column("auditResult").title("审核结果"));
    }};

    /**
     * 我的领单
     */
    public static final List<Column> GPS_MINE_COLUMNS = GPS_GENERAL_COLUMNS;
}
