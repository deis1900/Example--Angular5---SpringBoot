package com.AdminPanel.Angular5SpringBoot.service;


import com.AdminPanel.Angular5SpringBoot.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    void save(Product product);

    void saveAll(List<Product> product);

    void updateProduct(Product product);

    Product getOne(Long id);

    List<Product> findByTypeOfClothes(String typeOfClothes);

    void deleteProduct(Long id);
}
