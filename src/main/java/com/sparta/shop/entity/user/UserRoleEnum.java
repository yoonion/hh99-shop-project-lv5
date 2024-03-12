package com.sparta.shop.entity.user;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserRoleEnum {
    ADMIN(Authority.ADMIN),
    USER(Authority.USER);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static UserRoleEnum getRoleByString(String text) {
        String upperText = "ROLE_" + text.toUpperCase();
        return Arrays.stream(UserRoleEnum.values())
                .filter(userRoleEnum -> userRoleEnum.authority.equals(upperText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 권한이 존재하지 않습니다."));
    }

    public static class Authority {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String USER = "ROLE_USER";
    }
}
