package com.AdminPanel.Angular5SpringBoot.service;

import com.AdminPanel.Angular5SpringBoot.model.Product;
import com.AdminPanel.Angular5SpringBoot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    @Override
    @Transactional
    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    @Transactional
    public List<Product> findByTypeOfClothes(String typeOfClothes) {
        return productRepository.findByTypeOfClothes(typeOfClothes);
    }

    @Override
    @Transactional
    public Product getOne(Long id) {
        for(Product product: productRepository.findAll()) {
            if(id.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
