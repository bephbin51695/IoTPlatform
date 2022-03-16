package com.yzk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yzk.domain.Device;
import com.yzk.domain.R;
import com.yzk.service.DeviceService;
import com.yzk.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MqttService mqttService;

    @GetMapping("{userId}")
    public R getDeviceList(@PathVariable Integer userId) {
        List<Device> deviceList = deviceService.getByOwnerId(userId);
        return !deviceList.isEmpty() ? new R(true, deviceList) : new R("当前设备列表为空");
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize){
        return new R(true,deviceService.getPage(currentPage,pageSize));
    }

    @GetMapping("/mqtt/{id}")
    public R mqttSend(@PathVariable Integer id) throws JsonProcessingException {
        Device device = deviceService.getById(id);
        mqttService.sendMessage(device);
        return new R(true);
    }
}
