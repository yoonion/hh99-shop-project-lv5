package com.sparta.shop.service.cart;

import com.sparta.shop.dto.cart.*;
import com.sparta.shop.entity.user.User;

public interface CartService {

    CartAddResponseDto addCart(CartAddRequestDto requestDto, User user);

    CartInfoListResponseDto getCartProducts(User user);

    CartInfoResponseDto updateCartQuantity(CartUpdateRequestDto requestDto, Long productId, User user);
}
