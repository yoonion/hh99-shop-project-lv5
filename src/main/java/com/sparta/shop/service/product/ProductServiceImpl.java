package com.sparta.shop.service.product;

import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import com.sparta.shop.dto.product.ProductRegisterResponseDto;
import com.sparta.shop.entity.product.Product;
import com.sparta.shop.entity.product.ProductCategoryEnum;
import com.sparta.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductRegisterResponseDto registerProduct(ProductRegisterRequestDto requestDto) {
        ProductCategoryEnum category = ProductCategoryEnum.getProductCategoryByString(requestDto.getCategory());

        Product product = new Product(category, requestDto);
        productRepository.save(product);

        return new ProductRegisterResponseDto(product);
    }
}
