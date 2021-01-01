package com.mljr.gps.controller.common;

import com.mljr.excel.handler.ExcelExportHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * @description: 通用Excel 导入/导出 controller
 * @Date : 2018/6/3 上午11:20
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@RestController
@RequestMapping("/commonExcel")
@Api(description = "【Excel导入导出】相关api",tags = "CommonExcelController")
public class CommonExcelController extends ExcelExportHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(CommonExcelController.class);
	/**
	 * 导出入口
	 * exportType[xls,zip],导出问卷类型默认xls;
	 * sheetRows,excel最大行数默认5000;
	 * pageSize数据单次加载数据量默认1000;
	 * (sheetRows,limit仅type为zip时生效)
	 * @param beanName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/export/{beanName}",method = {RequestMethod.GET})
	@ApiOperation("excel导出")
    public void doExport(@PathVariable String beanName, HttpServletRequest request, HttpServletResponse response) {
		try {
			invoke(beanName,request,response);
        }catch (Exception e) {
        	LOGGER.error("下载过程出现异常", e);
			try {
				response.getWriter().print(MessageFormat.format(callbackClient(),"下载过程出现异常"));
			} catch (IOException ex) {
				LOGGER.error("下载获取bean实例异常",e);
			}
        }
    }
}
