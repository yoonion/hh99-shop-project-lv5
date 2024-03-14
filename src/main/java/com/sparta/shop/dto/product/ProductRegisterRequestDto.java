package com.sparta.shop.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRegisterRequestDto {

    @Schema(description = "상품 이름", defaultValue = "무지 후드티")
    @NotBlank
    private String name;

    @Schema(description = "상품 가격", defaultValue = "89000")
    @NotNull
    private int price;

    @Schema(description = "상품 수량", defaultValue = "100")
    @NotNull
    private int quantity;

    @Schema(description = "상품 설명", defaultValue = "멋진 후드티 입니다~")
    @NotBlank
    private String introduction;

    @Schema(description = "상품 카테고리", defaultValue = "top", allowableValues = {"top", "pants"})
    @NotBlank
    private String category;

    @Schema(description = "상품 이미지")
    @NotNull
    private MultipartFile image;
}
