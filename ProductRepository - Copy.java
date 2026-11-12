package com.shopzone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopzone.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

	 List<Product> findByNameContainingIgnoreCase(String name);
	    
	    List<Product> findByCategoryId(Long categoryId);


	    List<Product> findByPriceBetween(double minPrice , double maxPrice);
	    
	    List<Product> findBySellerId(Long sellerId);
	    
	    @Query( """
	            SELECT p FROM Product p WHERE p.category.id = :categoryId
	            AND p.price BETWEEN :minPrice AND :maxPrice
	            AND LOWER(p.name) LIKE LOWER(CONCAT('%' , :name , '%'))
	            """)
	    List<Product> filterProduct(Long categoryId, double minPrice, double maxPrice, String name);
	}

