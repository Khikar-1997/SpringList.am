package com.example.demo.controller;

import com.example.demo.product.Product;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public void create(@RequestBody Product product){
        productService.create(product);
    }

    @GetMapping(value = "/product")
    public void selectAllProducts(){
        for (Product product : productService.selectAllProducts()) {
            System.out.println("name :" + product.getName() + " ," + "prise :" + product.getPrise() +
                    " ," + "type :" + product.getType() + " ," + "uuid :" +
                    product.getUuid());
        }
    }

    @GetMapping(value = "/product/{id}")
    public void findProductById(@PathVariable int id){
        System.out.println(productService.findProductById(id));
    }

    @PutMapping(value = "/product/{id}")
    public @ResponseBody void update(@PathVariable int id,@RequestBody Product product){
        productService.update(id,product);
    }

    @DeleteMapping(value = "/product/{id}")
    public void delete(@PathVariable int id){
        productService.delete(id);
    }

    @GetMapping(value = "/product/search/{search}")
    public void search(@PathVariable String search){
        for (Product product : productService.serch(search)) {
            System.out.println(product.getName());
        }
    }
}
