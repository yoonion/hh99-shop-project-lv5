package com.sparta.shop.service.product;

import com.sparta.shop.dto.product.ProductInfoResponseDto;
import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import com.sparta.shop.dto.product.ProductRegisterResponseDto;
import com.sparta.shop.entity.product.Product;
import com.sparta.shop.entity.product.ProductCategoryEnum;
import com.sparta.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

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

    @Override
    public ProductInfoResponseDto getProduct(Long productId) {
        Product findProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("상품 정보가 없습니다."));
        return new ProductInfoResponseDto(findProduct);
    }
}
