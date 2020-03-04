package com.example.demo.controller;

import com.example.demo.product.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public void create(@RequestBody Product product) {
        productService.create(product);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<ArrayList<Product>> selectAllProducts() {
        ArrayList<Product> products = productService.selectAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/product/{id}")
    public @ResponseBody
    void update(@PathVariable int id, @RequestBody Product product) {
        productService.update(id, product);
    }

    @DeleteMapping(value = "/product/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @GetMapping(value = "/product/search/{search}")
    public ResponseEntity<ArrayList<Product>> search(@PathVariable String search) {
        ArrayList<Product> products = productService.serch(search);
        return ResponseEntity.ok(products);
    }
}
