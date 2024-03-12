package com.sparta.shop.controller;

import com.sparta.shop.dto.cart.CartAddRequestDto;
import com.sparta.shop.dto.cart.CartAddResponseDto;
import com.sparta.shop.dto.cart.CartInfoListResponseDto;
import com.sparta.shop.security.UserDetailsImpl;
import com.sparta.shop.service.cart.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
