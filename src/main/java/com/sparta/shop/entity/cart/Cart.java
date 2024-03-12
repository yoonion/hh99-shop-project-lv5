package com.sparta.shop.entity.cart;

import com.sparta.shop.dto.cart.CartAddRequestDto;
import com.sparta.shop.entity.product.Product;
import com.sparta.shop.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Cart(CartAddRequestDto requestDto, User user, Product findProduct) {
        this.quantity = requestDto.getQuantity();
        this.user = user;
        this.product = findProduct;
    }
}
