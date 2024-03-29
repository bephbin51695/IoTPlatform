package com.yzk.service;

import com.github.pagehelper.PageInfo;
import com.yzk.domain.Device;

import java.util.List;

public interface DeviceService {
    Device getByDeviceId(String deviceId);

    Device getById(Integer id);

    List<Device> getAll();

    List<Device> getByOwnerId(Integer id);

    PageInfo<Device> getPage(Integer currentPage, Integer pageSize);

    Boolean add(Device device);

    Boolean renewCurrentTemperature(Device device);

    Boolean remove(Integer id);

    Boolean modifyById(Device device);

    //bind
    Boolean modifyOwnerIdByDeviceId(String deviceId, Integer ownerId);

    //unbind
    Boolean modifyOwnerIdByDeviceId(String deviceId);
}
