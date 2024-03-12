package com.sparta.shop.service.product;

import com.sparta.shop.dto.product.ProductInfoListResponseDto;
import com.sparta.shop.dto.product.ProductInfoResponseDto;
import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import com.sparta.shop.dto.product.ProductRegisterResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductRegisterResponseDto registerProduct(ProductRegisterRequestDto requestDto);

    ProductInfoResponseDto getProduct(Long productId);

    Page<ProductInfoListResponseDto> getProducts(int page, int size, String sortBy, boolean isAsc);

}
