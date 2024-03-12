package com.sparta.shop.controller;

import com.sparta.shop.dto.cart.CartAddRequestDto;
import com.sparta.shop.dto.cart.CartAddResponseDto;
import com.sparta.shop.security.UserDetailsImpl;
import com.sparta.shop.service.cart.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping
    private ResponseEntity<CartAddResponseDto> addCart(
            @RequestBody @Valid CartAddRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        CartAddResponseDto responseDto = cartService.addCart(requestDto, userDetails.getUser());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }
}
