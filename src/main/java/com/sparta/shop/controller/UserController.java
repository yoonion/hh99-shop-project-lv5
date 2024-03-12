package com.sparta.shop.controller;

import com.sparta.shop.dto.user.SignUpRequestDto;
import com.sparta.shop.dto.user.SignUpResponseDto;
import com.sparta.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = userService.signup(requestDto);

        return ResponseEntity.ok(responseDto);
    }
}
