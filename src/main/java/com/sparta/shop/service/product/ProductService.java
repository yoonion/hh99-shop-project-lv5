package com.sparta.shop.service.product;

import com.sparta.shop.dto.product.*;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductRegisterResponseDto registerProduct(ProductRegisterRequestDto requestDto) throws IOException;

    ProductInfoResponseDto getProduct(Long productId);

    Page<ProductInfoListResponseDto> getProducts(int page, int size, String sortBy, boolean isAsc);
}
