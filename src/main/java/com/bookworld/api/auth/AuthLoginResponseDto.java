package com.bookworld.api.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginResponseDto {
    private String accessToken;
    private String refreshToken;
}