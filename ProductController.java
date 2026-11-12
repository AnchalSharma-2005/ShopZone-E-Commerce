package com.shopzone.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.shopzone.dto.ProductResponseDTO;
import com.shopzone.entity.Product;
import com.shopzone.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(
            @PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            return "Product deleted successfully";
        }

        return "Product not found";
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String name) {
        return productService.searchProducts(name);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(
            @PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return productService.getProductsByPriceRange(
                minPrice, maxPrice);
    }

    @GetMapping("/seller/{sellerId}")
    public List<Product> getProductsBySeller(
            @PathVariable Long sellerId) {
        return productService.getProductsBySeller(sellerId);
    }

    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam Long categoryId,
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam String name) {

        return productService.filterProducts(
                categoryId, minPrice, maxPrice, name);
    }

    @GetMapping("/page")
    public Page<Product> getProducts(Pageable pageable) {
        return productService.getProducts(pageable);
    }
}