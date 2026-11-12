package com.shopzone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shopzone.dto.ProductResponseDTO;
import com.shopzone.entity.Product;
import com.shopzone.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository
                .findById(id)
                .orElse(null);

        if (product == null) {
            return null;
        }

        return convertToDTO(product);
    }

    private ProductResponseDTO convertToDTO(Product product) {

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setImageUrl(product.getImgUrl());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        if (product.getSeller() != null) {
            dto.setSellerId(product.getSeller().getId());
            dto.setSellerName(product.getSeller().getName());
        }

        return dto;
    }

    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository
                .findById(id)
                .orElse(null);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImgUrl(product.getImgUrl());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setSeller(product.getSeller());

        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            return false;
        }

        productRepository.deleteById(id);
        return true;
    }

    public List<Product> searchProducts(String name) {
        return productRepository
                .findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> getProductsByPriceRange(
            double minPrice,
            double maxPrice) {

        return productRepository.findByPriceBetween(
                minPrice,
                maxPrice
        );
    }

    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    public List<Product> filterProducts(
            Long categoryId,
            double minPrice,
            double maxPrice,
            String name) {

        return productRepository.filterProduct(
                categoryId,
                minPrice,
                maxPrice,
                name
        );
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}