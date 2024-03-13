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
                throw new IllegalArgumentException(text + " : 해당 카테고리가 존재하지 않습니다.");
        }
    }
}
