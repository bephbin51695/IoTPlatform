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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
