package com.yzk.domain;

public class Device {
    private String id;
    private String deviceId;
    private String currentTemperature;
    private String setLowTemperature;
    private String setHighTemperature;
    private Integer ownerId;
    private String room;

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", currentTemperature='" + currentTemperature + '\'' +
                ", setLowTemperature='" + setLowTemperature + '\'' +
                ", setHighTemperature='" + setHighTemperature + '\'' +
                ", ownerId=" + ownerId +
                ", room='" + room + '\'' +
                '}';
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
