package com.yzk.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzk.domain.Device;
import com.yzk.service.DeviceService;
import com.yzk.service.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service("mqttService")
public class MqttServiceImpl implements MqttService {
    @Autowired
    private MqttPahoMessageHandler mqttHandler;
    @Autowired
    private DeviceService deviceService;

    @Override
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void receiveMessage(String message, @Header("mqtt_receivedTopic") String topic) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Device device = mapper.readValue(message, Device.class);
        Device query = deviceService.getByDeviceId(device.getDeviceId());
        if (query == null) {
            deviceService.add(device);
            log.info("Device registered,id:{}", device.getDeviceId());
        } else {
            deviceService.renewCurrentTemperature(device);
            log.info("Device {} current temperature:{}", device.getDeviceId(), device.getCurrentTemperature());
        }
    }

    @Override
    public void sendMessage(Device device) {
        String data = "SetTemp:" + device.getTargetTemperature();
        Message<String> message = MessageBuilder.withPayload(data).setHeader(MqttHeaders.TOPIC, "ssm/setting/" + device.getDeviceId()).build();
        log.info("Device {} set temperature:{}", device.getDeviceId(), device.getTargetTemperature());
        mqttHandler.handleMessage(message);
    }

}
