package com.yzk.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzk.domain.Device;
import com.yzk.service.DeviceService;
import com.yzk.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

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
            System.out.println("new device registered:" + device.getDeviceId());
        } else {
            deviceService.renewCurrentTemperature(device);
            System.out.println("temperature updated:" + device.getDeviceId());
        }
    }

    @Override
    public void sendMessage(Device device) {
        String data = "SetTemp:" + device.getTargetTemperature();
        Message<String> message = MessageBuilder.withPayload(data).setHeader(MqttHeaders.TOPIC, "ssm/setting/" + device.getDeviceId()).build();
        System.out.println(data);
        mqttHandler.handleMessage(message);
    }

}
