package com.sparta.shop.controller;

import com.sparta.shop.dto.ApiResponse;
import com.sparta.shop.dto.product.ProductInfoListResponseDto;
import com.sparta.shop.dto.product.ProductInfoResponseDto;
import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import com.sparta.shop.dto.product.ProductRegisterResponseDto;
import com.sparta.shop.entity.user.UserRoleEnum;
import com.sparta.shop.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Product", description = "상품")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 추가", description = "상품 추가 입니다. ADMIN 권한만 등록이 가능합니다.")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping(consumes = "multipart/form-data")
    public ApiResponse<ProductRegisterResponseDto> registerProduct(@ModelAttribute @Valid ProductRegisterRequestDto requestDto) throws IOException {
        ProductRegisterResponseDto responseDto = productService.registerProduct(requestDto);

        return ApiResponse.createSuccess(responseDto);
    }

    @Operation(summary = "선택 상품 조회", description = "선택 상품 조회입니다.")
    @GetMapping("/{productId}")
    public ApiResponse<ProductInfoResponseDto> getProduct(
            @Parameter(required = true, description = "상품 고유번호")
            @PathVariable Long productId
    ) {
        ProductInfoResponseDto responseDto = productService.getProduct(productId);

        return ApiResponse.createSuccess(responseDto);
    }

    @Operation(summary = "전체 상품 조회(페이징)", description = "전체 상품 조회입니다. 페이징이 가능합니다.")
    @Parameters({
            @Parameter(name = "page", description = "조회할 페이지"),
            @Parameter(name = "size", description = "한 페이지당 조회할 상품 개수"),
            @Parameter(name = "sortBy", description = "어떤 것을 기준으로 정렬 할 것인지?"),
            @Parameter(name = "isAsc", description = "true = 오름차순, false = 내림차순")
    })
    @GetMapping
    public ApiResponse<Page<ProductInfoListResponseDto>> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc
    ) {
        Page<ProductInfoListResponseDto> responseDtos = productService.getProducts(page - 1, size, sortBy, isAsc);

        return ApiResponse.createSuccess(responseDtos);
    }
}
