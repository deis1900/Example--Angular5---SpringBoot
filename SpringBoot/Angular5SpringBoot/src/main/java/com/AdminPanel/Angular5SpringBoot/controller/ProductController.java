package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.dto.ProductDto;
import com.AdminPanel.Angular5SpringBoot.model.Product;
import com.AdminPanel.Angular5SpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getAll() {
        ProductDto wrapper = new ProductDto();
        wrapper.setListProducts(productService.findAll());

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postProduct(@RequestBody Product product) {
//        test exists
        productService.save(new Product(
                product.getId(),
                product.getTypeOfClothes(),
                product.getMaterial(),
                product.getSize(),
                product.getColor(),
                product.getDateOfLastChange(),
                product.getImage()));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(value = "/saveAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> saveAllProducts(@RequestBody List<Product> products) {
        productService.saveAll(products);
        return products;
    }

    @PutMapping(value = "/{id}")
    public Product updateProduct(@RequestBody Product product) {
        System.out.println(product);
        productService.updateProduct(product);
        return product;
    }

    @GetMapping(value = "/typeofclothes/{typeOfClothes}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByTypeOfClothes(@PathVariable String typeOfClothes) {

        List<Product> products = productService.findByTypeOfClothes(typeOfClothes);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}


