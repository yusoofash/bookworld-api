package com.bookworld.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDto> login(
            @RequestBody Map<String, String> payload) throws Exception {
        AuthLoginResponseDto authLoginResponseDto = authService.login(payload.get("email"), payload.get("password"));
        return ResponseEntity.ok(authLoginResponseDto);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthLoginResponseDto> refreshToken(
            @RequestBody Map<String, String> payload) throws Exception {
        try {
            AuthLoginResponseDto authLoginResponseDto = authService.refreshToken(payload.get("refreshToken"));
            return ResponseEntity.ok(authLoginResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}

