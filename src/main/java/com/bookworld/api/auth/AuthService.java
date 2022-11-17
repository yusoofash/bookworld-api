package com.bookworld.api.auth;

public interface AuthService {
    AuthLoginResponseDto login(String email, String password) throws Exception;

    AuthLoginResponseDto refreshToken(String refreshToken) throws Exception;
}
