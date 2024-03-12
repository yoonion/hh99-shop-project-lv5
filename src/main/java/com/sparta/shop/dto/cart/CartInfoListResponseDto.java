package com.sparta.shop.dto.cart;

import lombok.Getter;

import java.util.List;

@Getter
public class CartInfoListResponseDto {
    private final List<CartInfoResponseDto> products;
    private final int totalPrice;

    public CartInfoListResponseDto(List<CartInfoResponseDto> products, int totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }
}
