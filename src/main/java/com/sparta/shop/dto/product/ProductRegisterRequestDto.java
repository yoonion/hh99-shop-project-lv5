package com.sparta.shop.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductRegisterRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @NotBlank
    private String introduction;

    @NotBlank
    private String category;
}
