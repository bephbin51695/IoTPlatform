package com.yzk.controller;

import com.yzk.domain.R;
import com.yzk.domain.User;
import com.yzk.service.UserService;
import com.yzk.util.LocalStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LocalStorage localStorage;
    @Autowired
    private RememberMeServices rememberMeServices;
    @Value("${login.max-try}")
    private Long maxTry;

    @GetMapping
    public R getAll() {
        return new R(true, userService.getAll());
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return new R(true, userService.getAllByPage(currentPage, pageSize));
    }

    @PostMapping("/login")
    public R login(@RequestBody User u, HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Long> loginAttemptMap = localStorage.getLoginAttemptMap();
        if (loginAttemptMap.get(u.getUsername()) > maxTry) {
            log.warn("用户{}超过最大尝试次数", u.getUsername());
            return new R("用户名或密码不正确");
        }
        try {
            request.login(u.getUsername(), u.getPassword());
        } catch (ServletException e) {
            log.error(e.getMessage());
            updateMap(loginAttemptMap, u.getUsername(), 1L);
            return new R("用户名或密码不正确");
        }
        Authentication token = (Authentication) request.getUserPrincipal();
        rememberMeServices.loginSuccess(request, response, token);
        return new R(true);
    }

    @GetMapping("/logout")
    public R logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.invalidate();
        LogoutHandler handler = (LogoutHandler) rememberMeServices;
        handler.logout(request, response, (Authentication) request.getUserPrincipal());
        return new R(true);
    }

    @GetMapping("/find")
    public R findPasswordByPhone(String phone) {
        User user = userService.getByPhone(phone);
        return user != null ? new R(true, user) : new R("未找到用户");
    }

    @PostMapping("/signup")
    public R signup(@RequestBody User u) {
        return userService.signup(u) ? new R(true, userService.getById(u.getId())) : new R("注册失败");
    }

    @PutMapping
    public R modify(@RequestBody User u) {
        boolean flag = userService.modify(u);
        return flag ? new R(true, userService.getById(u.getId())) : new R("修改失败");
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        boolean flag = userService.delete(id);
        if (flag) {
            return new R(true);
        }
        return new R("删除失败");
    }

    @PutMapping("{id}")
    public R reset(@PathVariable Integer id) {
        return userService.reset(id) ? new R(true) : new R("重置失败");
    }

    private <T extends Number> void updateMap(Map<String, T> map, String key, T value) {
        T oldValue = map.get(key);
        if (oldValue != null) {
            T newValue = (T) Long.valueOf(oldValue.longValue() + value.longValue());
            map.put(key, newValue);
        } else {
            map.put(key, value);
        }
    }

}
