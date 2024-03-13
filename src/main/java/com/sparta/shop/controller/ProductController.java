package com.sparta.shop.controller;

import com.sparta.shop.dto.product.*;
import com.sparta.shop.entity.user.UserRoleEnum;
import com.sparta.shop.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Product", description = "상품")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 추가", description = "상품 추가 입니다. ADMIN 권한만 등록이 가능합니다.")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping
    public ResponseEntity<ProductRegisterResponseDto> registerProduct(@ModelAttribute @Valid ProductRegisterRequestDto requestDto) throws IOException {
        ProductRegisterResponseDto responseDto = productService.registerProduct(requestDto);

        return ResponseEntity.ok(responseDto);
    }

    /*@PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImageTest(@ModelAttribute ImageTestDto imageTestDto) throws IOException {
        productService.registerProductV2(imageTestDto);
        return null;
    }*/

    @Operation(summary = "선택 상품 조회", description = "선택 상품 조회입니다.")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductInfoResponseDto> getProduct(
            @Parameter(required = true, description = "상품 고유번호")
            @PathVariable Long productId
    ) {
        ProductInfoResponseDto responseDto = productService.getProduct(productId);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "전체 상품 조회(페이징)", description = "전체 상품 조회입니다. 페이징이 가능합니다.")
    @Parameters({
            @Parameter(name = "page", description = "조회할 페이지"),
            @Parameter(name = "size", description = "한 페이지당 조회할 상품 개수"),
            @Parameter(name = "sortBy", description = "어떤 것을 기준으로 정렬 할 것인지?"),
            @Parameter(name = "isAsc", description = "true = 오름차순, false = 내림차순")
    })
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
