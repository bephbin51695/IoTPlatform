package com.yzk.domain;

public class Device {
    private Integer id;
    private String deviceId;
    private String currentTemperature;
    private String setLowTemperature;
    private String setHighTemperature;
    private String targetTemperature;
    private Integer ownerId;
    private String room;

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", currentTemperature='" + currentTemperature + '\'' +
                ", setLowTemperature='" + setLowTemperature + '\'' +
                ", setHighTemperature='" + setHighTemperature + '\'' +
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

    public String getSetLowTemperature() {
        return setLowTemperature;
    }

    public void setSetLowTemperature(String setLowTemperature) {
        this.setLowTemperature = setLowTemperature;
    }

    public String getSetHighTemperature() {
        return setHighTemperature;
    }

    public void setSetHighTemperature(String setHighTemperature) {
        this.setHighTemperature = setHighTemperature;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
