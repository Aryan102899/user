package com.pccw.global.user.web;

import com.pccw.global.user.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
    public final static String REQUEST_ID = "requestId";

    /**
     * 生成请求id
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = request.getHeader(REQUEST_ID);
        if (StringUtils.isBlank(requestId)) {
            requestId = UserUtils.getUuid();
        }
        request.setAttribute(REQUEST_ID, requestId);
        return true;
    }
}
