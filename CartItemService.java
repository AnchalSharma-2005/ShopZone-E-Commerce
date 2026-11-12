package com.shopzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shopzone.entity.Cart;
import com.shopzone.entity.CartItem;
import com.shopzone.entity.Product;
import com.shopzone.repository.CartItemRepository;
import com.shopzone.repository.CartRepository;
import com.shopzone.repository.ProductRepository;

@Service
public class CartItemService {

    private CartItemRepository cartItemRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartItemService(
            CartItemRepository cartItemRepository,
            CartRepository cartRepository,
            ProductRepository productRepository) {

        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }
    public CartItem addCartItem(
            Long cartId,
            Long productId,
            int quantity) {

        Cart cart = cartRepository
                .findById(cartId)
                .orElse(null);

        if (cart == null) {
            return null;
        }

        Product product = productRepository
                .findById(productId)
                .orElse(null);

        if (product == null) {
            return null;
        }

        if (quantity <= 0 || quantity > product.getStock()) {
            return null;
        }

        Optional<CartItem> existingItem =
                cartItemRepository.findByCartIdAndProductId(
                        cartId, productId);

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();

            int newQuantity = item.getQuantity() + quantity;

            if (newQuantity > product.getStock()) {
                return null;
            }

            item.setQuantity(newQuantity);

            return cartItemRepository.save(item);
        }

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(Long cartId) {

        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem updateQuantity(
            Long cartItemId,
            int quantity) {

        CartItem item = cartItemRepository
                .findById(cartItemId)
                .orElse(null);

        if (item == null) {
            return null;
        }

        if (quantity <= 0) {
            cartItemRepository.deleteById(cartItemId);
            return null;
        }

        if (quantity > item.getProduct().getStock()) {
            return null;
        }

        item.setQuantity(quantity);

        return cartItemRepository.save(item);
    }

    public boolean removeCartItem(Long cartItemId) {

        if (!cartItemRepository.existsById(cartItemId)) {
            return false;
        }

        cartItemRepository.deleteById(cartItemId);

        return true;
    }
}