package com.wzh.interceptor;

import com.wzh.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzh
 * @ClassName: LoginInterceptor
 * @Description:
 * @Date: 2020/3/29 18:11
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("拦截的url:" + url);
        User user = (User) request.getSession().getAttribute(
                "user");
        if (null == user) {
            //重定向到登陆界面
            response.sendRedirect("/crm/mes");
            return false;
        }
        return true;
    }
}
