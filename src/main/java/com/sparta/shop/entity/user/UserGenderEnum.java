package com.sparta.shop.entity.user;

public enum UserGenderEnum {
    MAN, WOMEN;

    public static UserGenderEnum getGenderByString(String text) {
        switch (text.toLowerCase()) {
            case "man":
                return MAN;
            case "woman":
                return WOMEN;
            default:
                throw new IllegalArgumentException("해당 성별이 존재하지 않습니다.");
        }
    }
}
