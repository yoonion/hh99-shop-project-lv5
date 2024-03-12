package com.sparta.shop.service.cart;

import com.sparta.shop.dto.cart.CartAddRequestDto;
import com.sparta.shop.dto.cart.CartAddResponseDto;
import com.sparta.shop.dto.cart.CartInfoListResponseDto;
import com.sparta.shop.entity.user.User;

public interface CartService {

    CartAddResponseDto addCart(CartAddRequestDto requestDto, User user);

    CartInfoListResponseDto getCartProducts(User user);
}
