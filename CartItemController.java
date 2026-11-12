package com.shopzone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shopzone.dto.CartItemRequestDTO;
import com.shopzone.entity.CartItem;
import com.shopzone.service.CartItemService;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private CartItemService cartItemService;

    public CartItemController(
            CartItemService cartItemService) {

        this.cartItemService = cartItemService;
    }

    @PostMapping
    public CartItem addCartItem(@RequestBody CartItemRequestDTO request) {

        return cartItemService.addCartItem(
                request.getCartId(),
                request.getProductId(),
                request.getQuantity()
        );
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItem> getCartItems(
            @PathVariable Long cartId) {

        return cartItemService.getCartItems(cartId);
    }

    @PutMapping("/{cartItemId}")
    public CartItem updateQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {

        return cartItemService.updateQuantity(
                cartItemId,
                quantity
        );
    }

    @DeleteMapping("/{cartItemId}")
    public String removeCartItem(
            @PathVariable Long cartItemId) {

        boolean removed =
                cartItemService.removeCartItem(cartItemId);

        if (removed) {
            return "Cart item removed successfully";
        }

        return "Cart item not found";
    }
}