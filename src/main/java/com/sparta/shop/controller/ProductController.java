package com.sparta.shop.controller;

import com.sparta.shop.dto.product.ProductInfoListResponseDto;
import com.sparta.shop.dto.product.ProductInfoResponseDto;
import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import com.sparta.shop.dto.product.ProductRegisterResponseDto;
import com.sparta.shop.entity.user.UserRoleEnum;
import com.sparta.shop.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping
    public ResponseEntity<ProductRegisterResponseDto> registerProduct(@RequestBody @Valid ProductRegisterRequestDto requestDto) {
        ProductRegisterResponseDto responseDto = productService.registerProduct(requestDto);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductInfoResponseDto> getProduct(@PathVariable Long productId) {
        ProductInfoResponseDto responseDto = productService.getProduct(productId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductInfoListResponseDto>> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam boolean isAsc
    ) {
        Page<ProductInfoListResponseDto> responseDtos = productService.getProducts(page - 1, size, sortBy, isAsc);

        return ResponseEntity.ok(responseDtos);
    }
}
