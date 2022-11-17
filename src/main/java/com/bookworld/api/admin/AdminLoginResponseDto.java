package com.bookworld.api.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
