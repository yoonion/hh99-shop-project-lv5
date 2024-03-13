package com.sparta.shop.service.product;

import com.sparta.shop.aws.S3Uploader;
import com.sparta.shop.dto.product.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final S3Uploader s3Uploader;

    @Override
    @Transactional
    public ProductRegisterResponseDto registerProduct(ProductRegisterRequestDto requestDto) throws IOException {
        ProductCategoryEnum category = ProductCategoryEnum.getProductCategoryByString(requestDto.getCategory());
        MultipartFile image = requestDto.getImage();
        if (image.isEmpty()) {
            throw new IllegalArgumentException("이미지가 없습니다. 이미지를 함께 업로드 해주세요.");
        }

        String savedImageUrl = s3Uploader.upload(image,"images");
        Product product = new Product(category, savedImageUrl, requestDto);
        productRepository.save(product);

        return new ProductRegisterResponseDto(product);
    }

    /*@Override
    @Transactional
    public void registerProductV2(ImageTestDto imageTestDto) throws IOException {
        ProductCategoryEnum category = ProductCategoryEnum.getProductCategoryByString(imageTestDto.getCategory());
        MultipartFile image = imageTestDto.getImage();

        if (!image.isEmpty()) {
            String savedImageUrl = s3Uploader.upload(image,"images");
            Product product = new Product(category, savedImageUrl, imageTestDto);
            productRepository.save(product);
        }
    }*/

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
