package com.yzk.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzk.domain.R;
import com.yzk.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivilegeInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(new R("未登录"));
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(json);
            return false;
        }
        return true;
    }
}
