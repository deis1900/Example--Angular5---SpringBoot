package com.AdminPanel.Angular5SpringBoot.repository;

import com.AdminPanel.Angular5SpringBoot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTypeOfClothes(String typeOfClothes);
}
