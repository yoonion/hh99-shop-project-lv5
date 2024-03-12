package com.sparta.shop.repository;

import com.sparta.shop.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserId(Long id);

    Optional<Cart> findByProductIdAndUserId(Long productId, Long id);
}
