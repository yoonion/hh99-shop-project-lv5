package com.sparta.shop.entity.product;

import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduction;

    private String imageUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    public Product(ProductCategoryEnum category, String savedImageUrl, ProductRegisterRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.quantity = requestDto.getQuantity();
        this.introduction = requestDto.getIntroduction();
        this.imageUrl = savedImageUrl;
        this.category = category;
    }
}
