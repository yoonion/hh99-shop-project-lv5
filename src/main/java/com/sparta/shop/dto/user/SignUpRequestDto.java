package com.sparta.shop.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private static final String PASSWORD_INVALID_MESSAGE = "비밀번호는 8자 이상, 15자 이하로 입력하여야 하며, 알파벳 대소문자, 숫자, 특수문자를 포함하여야 합니다.";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 15, message = PASSWORD_INVALID_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_INVALID_MESSAGE)
    private String password;

    @NotBlank
    private String gender;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String role;
}
