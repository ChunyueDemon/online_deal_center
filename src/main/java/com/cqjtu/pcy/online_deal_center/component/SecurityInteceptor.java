package com.cqjtu.pcy.online_deal_center.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityInteceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Object userInfo = request.getSession().getAttribute("userName");
        if(userInfo==null) {
            try {
                request.setAttribute("msg","该功能需要登录后使用，请您先登录");
                request.getRequestDispatcher("login.html").forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
            return false;
        }else{
            return true;
        }
    }
}
