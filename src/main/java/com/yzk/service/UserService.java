package com.yzk.service;

import com.github.pagehelper.PageInfo;
import com.yzk.domain.User;

import java.util.List;

public interface UserService {
    User login(User user);

    Boolean signup(User user);

    Boolean modify(User user);

    Boolean delete(Integer id);

    Boolean reset(Integer id);

    List<User> getAll();

    User getByPhone(String phone);

    User getById(Integer id);

    PageInfo<User> getAllByPage(Integer currentPage, Integer pageSize);
}
