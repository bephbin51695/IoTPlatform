package com.yzk.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.HashMap;

@Component
@Data
public class LocalStorage {
    private HashMap<String, Cookie> loginCookieMap = new HashMap<>();
    private HashMap<String, Long> loginAttemptMap = new HashMap<>();

}
