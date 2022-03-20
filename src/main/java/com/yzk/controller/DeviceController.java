package com.yzk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yzk.domain.Device;
import com.yzk.domain.R;
import com.yzk.service.DeviceService;
import com.yzk.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MqttService mqttService;

    @GetMapping
    public R getAll() {
        return new R(true, deviceService.getAll());
    }

    @GetMapping("{userId}")
    public R getDeviceList(@PathVariable Integer userId) {
        List<Device> deviceList = deviceService.getByOwnerId(userId);
        return !deviceList.isEmpty() ? new R(true, deviceList) : new R("当前设备列表为空");
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return new R(true, deviceService.getPage(currentPage, pageSize));
    }

    //TODO
    @GetMapping("/mqtt/{id}")
    public R mqttSend(@PathVariable Integer id) throws JsonProcessingException {
        Device device = deviceService.getById(id);
        mqttService.sendMessage(device);
        return new R(true);
    }

    @PutMapping
    public R modify(@RequestBody Device device) {
        Boolean flag = deviceService.modifyById(device);
        return flag ? new R(true) : new R("修改失败");
    }

    @PutMapping("{deviceId}/{ownerId}")
    public R bindDevice4User(@PathVariable("deviceId") String deviceId, @PathVariable("ownerId") Integer ownerId) {
        Boolean flag = deviceService.modifyOwnerIdByDeviceId(deviceId, ownerId);
        return flag ? new R(true, deviceService.getByOwnerId(ownerId)) : new R("用户绑定设备失败");
    }

    @DeleteMapping("{deviceId}/{ownerId}")
    public R unbindDevice4User(@PathVariable("deviceId") String deviceId, @PathVariable("ownerId") Integer ownerId) {
        Boolean flag = deviceService.modifyOwnerIdByDeviceId(deviceId);
        return flag ? new R(true, deviceService.getByOwnerId(ownerId)) : new R("用户解绑设备失败");
    }

}
