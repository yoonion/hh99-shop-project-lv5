package com.sparta.shop.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartUpdateRequestDto {
    @NotNull
    private int quantity;
}
