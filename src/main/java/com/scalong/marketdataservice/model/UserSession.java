package com.scalong.marketdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserSession {

    private String username;
    private String token;
    private LocalDateTime loginTime;
}
