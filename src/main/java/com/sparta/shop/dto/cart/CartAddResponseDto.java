package com.sparta.shop.dto.cart;

import com.sparta.shop.entity.cart.Cart;
import lombok.Getter;

@Getter
public class CartAddResponseDto {
    private final Long productId;
    private final int quantity;

    public CartAddResponseDto(Cart cart) {
        this.productId = cart.getProduct().getId();
        this.quantity = cart.getQuantity();
    }
}
