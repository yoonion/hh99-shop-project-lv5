package com.sparta.shop.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @Schema(name = "email")
    private String email;
    private String password;
}
