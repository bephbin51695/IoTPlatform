package com.yzk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzk.domain.Device;
import com.yzk.mapper.DeviceMapper;
import com.yzk.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public Device getById(Integer id) {
        return deviceMapper.getById(id);
    }

    @Override
    public List<Device> getAll() {
        return deviceMapper.getAll();
    }

    @Override
    public List<Device> getByOwnerId(Integer id) {
        return deviceMapper.getByOwnerId(id);
    }

    @Override
    public PageInfo<Device> getPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<Device> deviceList = deviceMapper.getAllByPage();
        PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
        return pageInfo;
    }

    @Override
    public Boolean modifyById(Device device) {
        return deviceMapper.modifyById(device)>0;
    }
}
