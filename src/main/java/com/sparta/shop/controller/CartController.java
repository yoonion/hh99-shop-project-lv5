package com.sparta.shop.controller;

import com.sparta.shop.dto.cart.*;
import com.sparta.shop.security.UserDetailsImpl;
import com.sparta.shop.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
@Tag(name = "Cart", description = "장바구니")
public class CartController {

    private final CartService cartService;

    @Operation(summary = "장바구니 추가", description = "장바구니에 상품을 추가합니다.")
    @PostMapping
    public ResponseEntity<CartAddResponseDto> addCart(
            @RequestBody @Valid CartAddRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        CartAddResponseDto responseDto = cartService.addCart(requestDto, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "장바구니 조회", description = "회원 자기자신의 장바구니 상품 전체 조회입니다.")
    @GetMapping
    public ResponseEntity<CartInfoListResponseDto> getCartProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        CartInfoListResponseDto responseDto = cartService.getCartProducts(userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "장바구니 선택 상품 개수 변경", description = "장바구니에 담은 상품의 개수를 업데이트 합니다.")
    @PatchMapping("/products/{productId}")
    public ResponseEntity<CartInfoResponseDto> updateCartQuantity(
            @PathVariable Long productId,
            @RequestBody @Valid CartUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CartInfoResponseDto responseDto = cartService.updateCartQuantity(requestDto, productId, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "장바구니 삭제", description = "장바구니를 삭제합니다.")
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<CartDeleteResponseDto> deleteCart(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CartDeleteResponseDto responseDto = cartService.deleteCart(productId, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }
}
