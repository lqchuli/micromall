package com.micromall.agentWeb.intercepter;

import com.micromall.agentWeb.bean.CookieHelper;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/5/18.
 */
public class AccountInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private MallAgentService agentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
        String tempRequestUrl = requestUrl.toLowerCase();
        if (tempRequestUrl.contains("login") || tempRequestUrl.contains("apply") || tempRequestUrl.contains("upload") || tempRequestUrl.contains("certificates")) {
            return true;
        }

        //��¼�ж�
        String customerId = request.getParameter("customerId").toString();

        Object loginToken = request.getSession().getAttribute("loginToken_" + customerId);

        String redirectUri = "/login?customerId=" + customerId + "&returnUrl=" + requestUrl;

        if (loginToken != null) {
            return true;
        }

        String account = CookieHelper.getCookieVal(request, "account_" + customerId);
        String password = CookieHelper.getCookieVal(request, "password_" + customerId);
        if (StringUtil.isEmpty(account) || StringUtil.isEmpty(password)) {
            response.sendRedirect(redirectUri);
            return false;
        } else {
            MallAgentBean agentBean = agentService.checkLogin(account, password, Integer.parseInt(customerId));
            if (agentBean != null) {
                CookieHelper.setCookie(response, "account_" + customerId, account);
                CookieHelper.setCookie(response, "password_" + customerId, password);
//                CookieHelper.setCookie(response, "agentId_" + customerId, String.valueOf(agentBean.getAgentId()));
                request.getSession().setAttribute("loginToken_" + customerId, account);
                request.getSession().setAttribute("agentId_" + customerId, agentBean.getAgentId());
                return true;
            } else {
                response.sendRedirect(redirectUri);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
