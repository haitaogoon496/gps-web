package com.mljr.gps.controller;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.mljr.annotation.LogMonitor;
import com.mljr.carrier.CarrierComposable;
import com.mljr.gps.entity.AppAnnex;
import com.mljr.gps.facade.AppOrderInfoFacade;
import com.mljr.gps.service.AppAnnexService;
import com.mljr.gps.vo.VehicleVo;
import com.mljr.gps.vo.orderVo.OrderAllInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @description:订单详情
 * @Date : 2018/9/29$ 16:55$
 * @Author : liht
 */
@RestController
@RequestMapping("appOrder/")
@Api(description = "【订单详情】相关api",tags = "AppOrderInfoController")
@Slf4j
public class AppOrderInfoController {

    @Autowired
    private AppOrderInfoFacade appOrderInfoFacade;

    @Autowired
    private AppAnnexService appAnnexService;

    @Autowired
    private CarrierComposable carrierComposable;

    @GetMapping("/getOrderAllInfo/{appCode}")
    @ApiOperation("查询订单详情所有数据")
    public Result<OrderAllInfoVo> getOrderAllInfo(@PathVariable String appCode) {
        return appOrderInfoFacade.getOrderAllInfo(appCode);
    }

    @GetMapping("/getCsByVinCode/{vinCode}")
    @ApiOperation("查询订单详情所有数据")
    public Result<VehicleVo> getCsByVinCode(@PathVariable String vinCode) {
        return appOrderInfoFacade.getCsByVinCode(vinCode);
    }

    @GetMapping(value = "/downloadAppAnnex/{id}")
    @ApiOperation(value = "下载审批附件")
    public void downloadAttach(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        AppAnnex annex = appAnnexService.findAnnexById(id);
        log.info("附件下载-id:{},详情:{}", id, JSON.toJSON(annex));
        // 循环取出流中的数据
        byte[] b = new byte[1024];
        int len;
        PrintWriter out = null;

        Result<Object> result = new Result<Object>();
        result.setCode(RemoteEnum.FAILURE.getIndex());
        result.setMsg("下载文件不存在,请核实后操作！");
        // 读到流中
        InputStream inStream = null;// 文件的存放路径
        try {
            response.setContentType("application/json; charset=UTF-8");
            out = response.getWriter();
            if (annex == null) {
                out.print(JSON.toJSON(result));
                return;
            }
            String fileName = annex.getAnnexName();
            // 相对路径
            String fileAddress = annex.getAnnexAddress();

            if (!StringUtils.isEmpty(annex.getImgKey())) {
                fileAddress = carrierComposable.getUrl(annex.getImgKey());
            }
            log.info("gps订单详情附件下载-id:{},fileAddress:{}",id,fileAddress);
            // 根据文件路径获取文件后缀 /data/filedoc/app_pdf/xx.pdf(.doc ..)
            File file = null;
            String suffix = "";

            if (fileAddress.contains("http")) {
                suffix = "pdf";
                URL url = new URL(fileAddress);
                // 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
                URLConnection uc = url.openConnection();
                // 打开的流连接读取的输入。
                inStream = uc.getInputStream();

            } else {
                if (fileAddress != null && fileAddress.split("\\.").length > 0) {
                    suffix = fileAddress.split("\\.")[1];
                } else {
                    suffix = "pdf";
                }

                file = new File(fileAddress);
                if (!file.exists()) {
                    out.print(JSON.toJSON(result));
                    return;
                }
                inStream = new FileInputStream(file);
            }

            // 设置输出的格式
            response.reset();
            response.setContentType("bin");
            // 解决不同浏览器中文乱码问题
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) { // IE
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "." + suffix + "\"");
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (FileNotFoundException e) {
            log.error("下载失败 attachId:{}", id, e);
            out.print(JSON.toJSON(result));

        } catch (Exception e) {
            if (e.getClass().getName().contains("ClientAbortException")) {
                log.warn("客户端取消下载 attachId:{} msg:{}", id, e.getMessage());
                result.setMsg("客户端取消下载 attachId:{}" + id);
                out.print(JSON.toJSON(result));
            } else {
                log.error("下载失败 attachId:{}", id, e);
                result.setMsg("下载失败 attachId:" + id);
                out.print(JSON.toJSON(result));
            }

        } finally {
            log.info("附件下载-id:{},所耗时间:{}",id,System.currentTimeMillis()-start);
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (Exception e) {
                result.setMsg("下载失败 attachId:" + id);
                out.print(JSON.toJSON(result));
            }
        }
    }
}
