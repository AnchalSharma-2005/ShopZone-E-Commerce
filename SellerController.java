package com.shopzone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shopzone.entity.Product;
import com.shopzone.entity.User;
import com.shopzone.service.ProductService;
import com.shopzone.service.UserService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	// fetch seller by id
	@GetMapping("/{sellerId}")
	public User getSeller(@PathVariable Long sellerId) {
		return userService.getUserById(sellerId);
	}

	// get product seller is wise
	@GetMapping("/{sellerId}/products")
	public List<Product> getSellerProducts(@PathVariable Long sellerId) {
		return productService.getProductsBySeller(sellerId);
	}
}