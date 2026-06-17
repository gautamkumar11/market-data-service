package com.scalong.marketdataservice.controller;

import com.scalong.marketdataservice.model.request.LoginRequest;
import com.scalong.marketdataservice.model.response.LoginResponse;
import com.scalong.marketdataservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
