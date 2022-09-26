package com.yzk.controller;

import com.yzk.domain.R;
import com.yzk.domain.User;
import com.yzk.service.UserService;
import com.yzk.util.LocalStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LocalStorage localStorage;

    @GetMapping
    public R getAll() {
        return new R(true, userService.getAll());
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return new R(true, userService.getAllByPage(currentPage, pageSize));
    }

    @PostMapping("/login")
    public R login(@RequestBody User u, HttpSession session, HttpServletResponse response) {
        HashMap<String, Long> loginAttemptMap = localStorage.getLoginAttemptMap();
        User user = userService.login(u);
        if (user == null) {
            updateMap(loginAttemptMap, u.getUsername(), 1L);
            return new R("用户名或密码不正确");
        }
        session.setAttribute("user", user);
        Cookie cookie = new Cookie("token", UUID.randomUUID().toString().replaceAll("-", ""));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 30);
        localStorage.getLoginCookieMap().put(user.getUsername(), cookie);
        response.addCookie(cookie);
        return new R(true, user);
    }

    @GetMapping("/logout")
    public R logout(HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute("user");
        session.invalidate();
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        localStorage.getLoginCookieMap().remove(user.getUsername());
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
