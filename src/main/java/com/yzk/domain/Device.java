package com.yzk.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {
    private Integer id;
    private String deviceId;
    private String currentTemperature;
    private String targetTemperature;
    private Integer ownerId;
    private String room;

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", currentTemperature='" + currentTemperature + '\'' +
                ", targetTemperature='" + targetTemperature + '\'' +
                ", ownerId=" + ownerId +
                ", room='" + room + '\'' +
                '}';
    }

    public String getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(String targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

}
