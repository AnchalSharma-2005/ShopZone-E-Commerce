package com.shopzone.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopzone.entity.Category;
import com.shopzone.service.CategoryService;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
