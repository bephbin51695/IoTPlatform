package com.yzk.controller;

import com.yzk.domain.Device;
import com.yzk.domain.R;
import com.yzk.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

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

    @PostMapping
    public R add(@RequestBody Device device) {
        boolean flag = deviceService.add(device);
        return flag ? new R(true) : new R("添加设备失败");
    }

    @DeleteMapping("{id}")
    public R remove(@PathVariable Integer id) {
        boolean flag = deviceService.remove(id);
        return flag ? new R(true) : new R("删除设备失败");
    }

    @PutMapping
    public R modify(@RequestBody Device device) {
        boolean flag = deviceService.modifyById(device);
        return flag ? new R(true) : new R("修改失败");
    }

    @PutMapping("{deviceId}/{ownerId}")
    public R bindDevice4User(@PathVariable("deviceId") String deviceId, @PathVariable("ownerId") Integer ownerId) {
        boolean flag = deviceService.modifyOwnerIdByDeviceId(deviceId, ownerId);
        return flag ? new R(true, deviceService.getByOwnerId(ownerId)) : new R("用户绑定设备失败");
    }

    @DeleteMapping("{deviceId}/{ownerId}")
    public R unbindDevice4User(@PathVariable("deviceId") String deviceId, @PathVariable("ownerId") Integer ownerId) {
        boolean flag = deviceService.modifyOwnerIdByDeviceId(deviceId);
        return flag ? new R(true, deviceService.getByOwnerId(ownerId)) : new R("用户解绑设备失败");
    }

}
