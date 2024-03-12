package com.sparta.shop.service.user;

import com.sparta.shop.dto.user.SignUpRequestDto;
import com.sparta.shop.dto.user.SignUpResponseDto;

public interface UserService {
    SignUpResponseDto signup(SignUpRequestDto requestDto);
}
