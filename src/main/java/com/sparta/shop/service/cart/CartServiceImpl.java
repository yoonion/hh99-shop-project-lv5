package com.sparta.shop.service.cart;

import com.sparta.shop.dto.cart.CartAddRequestDto;
import com.sparta.shop.dto.cart.CartAddResponseDto;
import com.sparta.shop.dto.cart.CartInfoListResponseDto;
import com.sparta.shop.dto.cart.CartInfoResponseDto;
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

        Cart cart = new Cart(requestDto, user, findProduct);
        cartRepository.save(cart);

        return new CartAddResponseDto(cart);
    }

    @Override
    public CartInfoListResponseDto getCartProducts(User user) {
        List<Cart> findCarts = cartRepository.findAllByUserId(user.getId());
        List<CartInfoResponseDto> products = new ArrayList<>();

        int totalPrice = 0;
        for (Cart findCart : findCarts) {
            totalPrice += findCart.getProduct().getPrice() * findCart.getQuantity(); // 장바구니 총 가격 = 상품 가격 * 담은 수량
            products.add(new CartInfoResponseDto(findCart));
        }

        return new CartInfoListResponseDto(products, totalPrice);
    }
}
