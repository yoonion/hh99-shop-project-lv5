package com.sparta.shop.dto.product;

import com.sparta.shop.entity.product.Product;
import com.sparta.shop.entity.product.ProductCategoryEnum;
import lombok.Getter;

@Getter
public class ProductRegisterResponseDto {
    private final String name;
    private final int price;
    private final int quantity;
    private final String introduction;
    private final ProductCategoryEnum category;

    public ProductRegisterResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.introduction = product.getIntroduction();
        this.category = product.getCategory();
    }
}
