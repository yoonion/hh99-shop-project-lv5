package com.sparta.shop.dto.cart;

import com.sparta.shop.entity.cart.Cart;
import com.sparta.shop.entity.product.ProductCategoryEnum;
import lombok.Getter;

@Getter
public class CartInfoResponseDto {

    private final String productName;
    private final int productQuantity;
    private final int productPrice;
    private final ProductCategoryEnum productCategory;

    public CartInfoResponseDto(Cart findCart) {
        this.productName = findCart.getProduct().getName();
        this.productPrice = findCart.getProduct().getPrice();
        this.productQuantity = findCart.getQuantity();
        this.productCategory = findCart.getProduct().getCategory();
    }
}
