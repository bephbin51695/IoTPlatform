package com.yzk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzk.domain.User;
import com.yzk.mapper.UserMapper;
import com.yzk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

    @Override
    public Boolean signup(User user) {
        if(userMapper.getCount(user) > 0){
            return false;
        }
        return userMapper.addUser(user) > 0;
    }

    @Override
    public Boolean modify(User user) {
        return userMapper.modifyUser(user) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return userMapper.deleteUser(id) > 0;
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User getByPhone(String phone) {
        return userMapper.getByPhone(phone);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public PageInfo<User> getAllByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<User> userList = userMapper.getUserByPage();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
