package com.yzk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yzk.domain.Device;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

public interface MqttService {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    void receiveMessage(String message, @Header(MqttHeaders.TOPIC) String topic) throws JsonProcessingException;

    void sendMessage(Device device) throws JsonProcessingException;
}
