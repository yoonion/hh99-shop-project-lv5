package com.sparta.shop.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private static final String PASSWORD_INVALID_MESSAGE = "비밀번호는 8자 이상, 15자 이하로 입력하여야 하며, 알파벳 대소문자, 숫자, 특수문자를 포함하여야 합니다.";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$";

    @Schema(description = "이메일", defaultValue = "admin@example.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "비밀번호", defaultValue = "Qwer1234!")
    @NotBlank
    @Size(min = 8, max = 15, message = PASSWORD_INVALID_MESSAGE)
    @Pattern(regexp = PASSWORD_PATTERN, message = PASSWORD_INVALID_MESSAGE)
    private String password;

    @Schema(description = "성별", defaultValue = "man", allowableValues = {"man", "women"})
    @NotBlank
    private String gender;

    @Schema(description = "전화번호", defaultValue = "010-1234-1234")
    @NotBlank
    private String phoneNumber;

    @Schema(description = "주소", defaultValue = "서울특별시 행복동")
    @NotBlank
    private String address;

    @Schema(description = "권한", defaultValue = "admin", allowableValues = {"admin", "user"})
    @NotBlank
    private String role;
}
