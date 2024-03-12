package com.sparta.shop.entity.product;

import lombok.Getter;

@Getter
public enum ProductCategoryEnum {
    TOP, PANTS;

    public static ProductCategoryEnum getProductCategoryByString(String text) {
        switch (text.toLowerCase()) {
            case "top":
                return TOP;
            case "pants":
                return PANTS;
            default:
                throw new IllegalArgumentException("해당 성별이 존재하지 않습니다.");
        }
    }
}
