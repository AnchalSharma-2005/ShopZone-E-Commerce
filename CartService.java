package com.shopzone.service;

import org.springframework.stereotype.Service;

import com.shopzone.entity.Cart;
import com.shopzone.entity.User;
import com.shopzone.repository.CartRepository;
import com.shopzone.repository.UserRepository;

@Service
public class CartService {

    private CartRepository cartRepository;
    private UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public Cart getOrCreateCart(Long userId) {

        Cart cart = cartRepository
                .findByUserId(userId)
                .orElse(null);

        if (cart != null) {
            return cart;
        }

        User user = userRepository
                .findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        Cart newCart = new Cart();
        newCart.setUser(user);

        return cartRepository.save(newCart);
    }

    public Cart getCartByUserId(Long userId) {

        return cartRepository
                .findByUserId(userId)
                .orElse(null);
    }
}