package com.yzk.controller;

import com.yzk.domain.R;
import com.yzk.domain.User;
import com.yzk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return new R(true, userService.getAllByPage(currentPage, pageSize));
    }

    @PostMapping("/login")
    public R login(@RequestBody User u, HttpSession session) {
        User user = userService.login(u);
        if (user != null) {
            session.setAttribute("user", user);
            return new R(true, user);
        }
        return new R("用户名或密码不正确");
    }

    @GetMapping("/logout")
    public R logout(HttpSession session) {
        session.invalidate();
        return new R(true);
    }

    @GetMapping("/find")
    public R findPasswordByPhone(String phone) {
        User user = userService.getByPhone(phone);
        return user != null ? new R(true, user) : new R("未找到用户");
    }

    @PostMapping("/signup")
    public R signup(@RequestBody User u) {
        return userService.signup(u) ? new R(true) : new R("注册失败");
    }

    @PutMapping
    public R modify(@RequestBody User u, HttpSession session) {
        User user = (User) session.getAttribute("user");
        u.setId(user.getId());
        Boolean modify = userService.modify(u);
        return modify ? new R(true, userService.getById(u.getId())) : new R("修改失败");
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id, ServletContext context) {
        Boolean delete = userService.delete(id);
        if (delete) {
            return new R(true);
        }
        return new R("删除失败");
    }

}
