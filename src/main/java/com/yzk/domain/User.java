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
public class User implements Serializable {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
