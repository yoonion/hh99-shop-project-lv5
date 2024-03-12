package com.sparta.shop.service.product;

import com.sparta.shop.dto.product.ProductInfoResponseDto;
import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import com.sparta.shop.dto.product.ProductRegisterResponseDto;

public interface ProductService {
    ProductRegisterResponseDto registerProduct(ProductRegisterRequestDto requestDto);

    ProductInfoResponseDto getProduct(Long productId);
}
