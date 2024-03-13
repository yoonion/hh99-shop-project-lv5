package com.sparta.shop.controller;

import com.sparta.shop.dto.user.SignUpRequestDto;
import com.sparta.shop.dto.user.SignUpResponseDto;
import com.sparta.shop.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "회원")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 가입", description = "회원 가입 입니다. 이메일은 중복될 수 없습니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = userService.signup(requestDto);

        return ResponseEntity.ok(responseDto);
    }
}
