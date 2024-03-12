package com.sparta.shop.security.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthResultResponseDto {
    private final int statusCode;
    private final String message;
}
