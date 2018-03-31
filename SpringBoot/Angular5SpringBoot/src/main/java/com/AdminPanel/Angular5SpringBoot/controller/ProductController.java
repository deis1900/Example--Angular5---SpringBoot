package com.AdminPanel.Angular5SpringBoot.controller;

import com.AdminPanel.Angular5SpringBoot.dto.ProductDto;
import com.AdminPanel.Angular5SpringBoot.fileWorkspace.CsvManager;
import com.AdminPanel.Angular5SpringBoot.fileWorkspace.modelConvertors.ProductRows;
import com.AdminPanel.Angular5SpringBoot.model.Product;
import com.AdminPanel.Angular5SpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        List<Product> productList = productService.findAll();
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ProductDto wrapper = new ProductDto();
        wrapper.setListProducts(productService.findAll());
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getById(@PathVariable ("id") Long id) {
        System.out.println("Fetching Product with id " + id);
        Product product = productService.getOne(id);
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @GetMapping(value = "/typeofclothes/{typeOfClothes}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByTypeOfClothes(@PathVariable String typeOfClothes) {
        List<Product> products = productService.findByTypeOfClothes(typeOfClothes);
        if (products == null) {
            System.out.println("Unable to find. Products in category " + typeOfClothes + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List <Product>>  singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        List<String> csvRows = new CsvManager().getRows(file);
        if (csvRows == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<Product> products = new ProductRows(csvRows).convertRowsToProducts();
        if (products == null){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(productService.saveAll(products), HttpStatus.CREATED);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postProduct(@RequestBody Product product) {
        System.out.println("Creating Product " + product.toString());
        if (product.equals(productService.getOne(product.getId()))) {
            System.out.println("A Product with id " + product.getId() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productService.save(new Product(
                product.getId(),
                product.getTypeOfClothes(),
                product.getMaterial(),
                product.getSize(),
                product.getColor(),
                product.getDateOfLastChange(),
                product.getImage()));
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable ("id") Long id, @RequestBody Product product) {
        System.out.println("Update product with id " + id);
        Product prod = productService.getOne(id);
        if (prod == null) {
            System.out.println("A Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (product.equals(productService.getOne(product.getId()))) {
            System.out.println("A Product with id " + product.getId() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        productService.updateProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Product product = productService.getOne(id);
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


