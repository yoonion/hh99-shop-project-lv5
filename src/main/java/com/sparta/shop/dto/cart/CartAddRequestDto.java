package com.sparta.shop.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartAddRequestDto {
    @NotNull
    private Long productId;

    @NotNull
    private int quantity;
}
