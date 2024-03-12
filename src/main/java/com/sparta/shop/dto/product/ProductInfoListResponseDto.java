package com.sparta.shop.dto.product;

import com.sparta.shop.entity.product.Product;
import com.sparta.shop.entity.product.ProductCategoryEnum;
import lombok.Getter;

@Getter
public class ProductInfoListResponseDto {

    private final String name;
    private final int price;
    private final int quantity;
    private final String introduction;
    private final ProductCategoryEnum category;

    public ProductInfoListResponseDto(Product findProduct) {
        this.name = findProduct.getName();
        this.price = findProduct.getPrice();
        this.quantity = findProduct.getQuantity();
        this.introduction = findProduct.getIntroduction();
        this.category = findProduct.getCategory();
    }
}
