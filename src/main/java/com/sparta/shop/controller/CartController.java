package com.sparta.shop.controller;

import com.sparta.shop.dto.cart.*;
import com.sparta.shop.security.UserDetailsImpl;
import com.sparta.shop.service.cart.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartAddResponseDto> addCart(
            @RequestBody @Valid CartAddRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        CartAddResponseDto responseDto = cartService.addCart(requestDto, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<CartInfoListResponseDto> getCartProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        CartInfoListResponseDto responseDto = cartService.getCartProducts(userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity<CartInfoResponseDto> updateCartQuantity(
            @PathVariable Long productId,
            @RequestBody CartUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CartInfoResponseDto responseDto = cartService.updateCartQuantity(requestDto, productId, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<CartDeleteResponseDto> deleteCart(
            @PathVariable Long productId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        CartDeleteResponseDto responseDto = cartService.deleteCart(productId, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }
}
