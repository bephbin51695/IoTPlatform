package com.yzk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzk.domain.Device;
import com.yzk.mapper.DeviceMapper;
import com.yzk.service.DeviceService;
import com.yzk.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private MqttService mqttService;

    @Override
    public Device getByDeviceId(String deviceId) {
        return deviceMapper.getByDeviceId(deviceId);
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
        PageHelper.startPage(currentPage, pageSize);
        List<Device> deviceList = deviceMapper.getAllByPage();
        PageInfo<Device> pageInfo = new PageInfo<>(deviceList);
        return pageInfo;
    }

    @Override
    public Boolean add(Device device) {
        return deviceMapper.addDevice(device) > 0;
    }

    @Override
    public Boolean renewCurrentTemperature(Device device) {
        return deviceMapper.renewCurrentTemperature(device) == 1;
    }

    @Override
    public Boolean remove(Integer id) {
        return deviceMapper.deleteDevice(id) > 0;
    }

    @Override
    public Boolean modifyById(Device device) {
        Device temp = deviceMapper.getById(device.getId());
        if (!Objects.equals(temp.getTargetTemperature(), device.getTargetTemperature())) {
            mqttService.sendMessage(device);
        }
        return deviceMapper.modifyById(device) > 0;
    }

    @Override
    public Boolean modifyOwnerIdByDeviceId(String deviceId, Integer ownerId) {
        Integer masterId = deviceMapper.getByDeviceId(deviceId).getOwnerId();
        if (masterId == null) return deviceMapper.addOwnerIdByDeviceId(deviceId, ownerId) > 0;
        return false;
    }

    @Override
    public Boolean modifyOwnerIdByDeviceId(String deviceId) {
        return deviceMapper.removeOwnerIdByDeviceId(deviceId) > 0;
    }
}
