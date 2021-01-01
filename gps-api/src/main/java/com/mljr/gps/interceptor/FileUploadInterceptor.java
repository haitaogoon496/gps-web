package com.mljr.gps.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @Date : 2018/10/25$ 16:11$
 * @Author : liht
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE+2)
@Slf4j
public class FileUploadInterceptor extends HandlerInterceptorAdapter {

    // 20MB限制
    private long maxSize = 20971520;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否文件上传
        if(request!=null && ServletFileUpload.isMultipartContent(request)) {
            log.info("文件上传request - uri:{}", request.getRequestURI());
            ServletRequestContext ctx = new ServletRequestContext(request);
            //获取上传文件尺寸大小
            long requestSize = ctx.getContentLength();

            if (requestSize > maxSize) {
                //当上传文件大小超过指定大小限制后，模拟抛出MaxUploadSizeExceededException异常
                throw new MaxUploadSizeExceededException(maxSize);
            }
        }
        return true;
    }
    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
