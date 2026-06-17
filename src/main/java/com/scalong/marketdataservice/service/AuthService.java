package com.scalong.marketdataservice.service;

import com.scalong.marketdataservice.model.UserSession;
import com.scalong.marketdataservice.model.request.LoginRequest;
import com.scalong.marketdataservice.model.response.LoginResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/***
 *  I could have added an abstraction layer here by creating Service interface but avoided for now
 */
@Log4j2
@Service
public class AuthService {

    // Hardcoded users (as allowed in assignment)
    private final Map<String, String> users = Map.of(
            "admin", "password",
            "gautam", "1234"
    );

    // username -> session
    private final Map<String, UserSession> activeSessions =
            new ConcurrentHashMap<>();


    public LoginResponse login(LoginRequest request) {

        String expectedPassword = users.get(request.getUsername());

        if (expectedPassword == null ||
                !expectedPassword.equals(request.getPassword())) {

            throw new RuntimeException("Invalid username or password");
        }

        // Enforce single session per user
        activeSessions.remove(request.getUsername());

        String token = UUID.randomUUID().toString();

        UserSession session = new UserSession(
                request.getUsername(),
                token,
                LocalDateTime.now()
        );

        activeSessions.put(request.getUsername(), session);
        return new LoginResponse(
                token,
                "Login successful"
        );
    }


    public boolean validateToken(String token) {
        log.info("validating the token");
        return activeSessions.values()
                .stream()
                .anyMatch(session ->
                        session.getToken().equals(token));
    }
}
