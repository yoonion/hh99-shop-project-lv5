package com.sparta.shop.dto.cart;

import lombok.Getter;

@Getter
public class CartDeleteResponseDto {
    private final Long productId;

    public CartDeleteResponseDto(Long productId) {
        this.productId = productId;
    }
}
