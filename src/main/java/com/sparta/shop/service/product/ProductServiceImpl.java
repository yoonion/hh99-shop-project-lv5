package com.sparta.shop.service.product;

import com.sparta.shop.dto.product.ProductInfoListResponseDto;
import com.sparta.shop.dto.product.ProductInfoResponseDto;
import com.sparta.shop.dto.product.ProductRegisterRequestDto;
import com.sparta.shop.dto.product.ProductRegisterResponseDto;
import com.sparta.shop.entity.product.Product;
import com.sparta.shop.entity.product.ProductCategoryEnum;
import com.sparta.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Page<ProductInfoListResponseDto> getProducts(int page, int size, String sortBy, boolean isAsc) {
        // 페이지 / 정렬
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> findAllProducts = productRepository.findAll(pageable);

        return findAllProducts.map(ProductInfoListResponseDto::new);
    }
}
