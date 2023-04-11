package com.yzk.util;

import jakarta.servlet.http.Cookie;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Data
public class LocalStorage {
    private HashMap<String, Cookie> loginCookieMap = new HashMap<>();
    private HashMap<String, Long> loginAttemptMap = new HashMap<>();

}
