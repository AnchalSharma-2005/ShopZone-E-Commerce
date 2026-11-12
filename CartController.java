package com.shopzone.controller;

import org.springframework.web.bind.annotation.*;

import com.shopzone.entity.Cart;
import com.shopzone.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/user/{userId}")
    public Cart createOrGetCart(
            @PathVariable Long userId) {

        return cartService.getOrCreateCart(userId);
    }

    @GetMapping("/user/{userId}")
    public Cart getCartByUser(
            @PathVariable Long userId) {

        return cartService.getCartByUserId(userId);
    }
}