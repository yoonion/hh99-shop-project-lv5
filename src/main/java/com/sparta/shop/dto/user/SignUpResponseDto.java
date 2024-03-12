package com.sparta.shop.dto.user;

import com.sparta.shop.entity.user.User;
import com.sparta.shop.entity.user.UserGenderEnum;
import com.sparta.shop.entity.user.UserRoleEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpResponseDto {
    private final String email;
    private final UserGenderEnum gender;
    private final String phoneNumber;
    private final String address;
    private final UserRoleEnum role;

    public SignUpResponseDto(User user) {
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.role = user.getRole();
    }
}
