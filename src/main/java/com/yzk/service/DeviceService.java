package com.yzk.service;

import com.github.pagehelper.PageInfo;
import com.yzk.domain.Device;

import java.util.List;

public interface DeviceService {
    Device getById(Integer id);

    List<Device> getAll();

    List<Device> getByOwnerId(Integer id);

    PageInfo<Device> getPage(Integer currentPage, Integer pageSize);

    Boolean modifyById(Device device);
}
