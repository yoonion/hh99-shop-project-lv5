package com.sparta.shop.service.cart;

import com.sparta.shop.dto.cart.*;
import com.sparta.shop.entity.cart.Cart;
import com.sparta.shop.entity.product.Product;
import com.sparta.shop.entity.user.User;
import com.sparta.shop.repository.CartRepository;
import com.sparta.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CartAddResponseDto addCart(CartAddRequestDto requestDto, User user) {
        Product findProduct = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));

        // TODO: 장바구니에 이미 같은 상품이 존재하면, 개수를 업데이트 해주는 방식 추가 필요

        Cart cart = new Cart(requestDto, user, findProduct);
        cartRepository.save(cart);

        return new CartAddResponseDto(cart);
    }

    @Override
    public CartInfoListResponseDto getCartProducts(User user) {
        List<Cart> findCarts = cartRepository.findAllByUserId(user.getId());
        List<CartInfoResponseDto> products = new ArrayList<>();
        if (findCarts.isEmpty()) {
            throw new NoSuchElementException("장바구니에 상품이 없습니다.");
        }

        int totalPrice = 0;
        for (Cart findCart : findCarts) {
            totalPrice += findCart.getProduct().getPrice() * findCart.getQuantity(); // 장바구니 총 가격 = 상품 가격 * 담은 수량
            products.add(new CartInfoResponseDto(findCart));
        }

        return new CartInfoListResponseDto(products, totalPrice);
    }

    @Override
    @Transactional
    public CartInfoResponseDto updateCartQuantity(CartUpdateRequestDto requestDto, Long productId, User user) {
        Cart findCart = cartRepository.findByProductIdAndUserId(productId, user.getId())
                .orElseThrow(() -> new NoSuchElementException("해당 상품이 장바구니에 존재하지 않습니다."));

        findCart.updateQuantity(requestDto.getQuantity());

        return new CartInfoResponseDto(findCart);
    }
}
