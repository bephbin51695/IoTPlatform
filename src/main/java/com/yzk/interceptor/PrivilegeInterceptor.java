package com.yzk.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzk.domain.R;
import com.yzk.domain.User;
import com.yzk.util.LocalStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
@Slf4j
public class PrivilegeInterceptor implements HandlerInterceptor {

    @Autowired
    private LocalStorage localStorage;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName()) && localStorage.getLoginCookieMap().get(user.getUsername()).getValue().equals(cookie.getValue())) {
                return true;
            }
        }
        if (user == null) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(new R("未登录"));
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(json);
            return false;
        }
        return true;
    }
}
