package com.coderhouse.controller;

import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ecommerce")
public class ProductController {

    private final ProductService service;

    @PostMapping("/products")
    public ProductResponse create(
            @Validated @RequestBody ProductRequest product) throws ApiRestException {
        return service.create(product);
    }

    @PutMapping("/products/{id}")
    public ProductResponse update(@PathVariable String id,
                                         @Validated @RequestBody ProductRequest product) {
        return service.update(id, product);
    }

    @GetMapping("/products")
    public List<ProductResponse> searchAll() {
        return service.searchAll();
    }

    @GetMapping("/products/{id}")
    public ProductResponse search(@PathVariable String id) throws ApiRestException{
        return service.searchById(id);
    }

    @GetMapping("/products/category/{category}")
    public List<ProductResponse> searchByCategory(@PathVariable String category) {
        return service.searchByCategory(category);
    }

    @DeleteMapping("/products/{id}")
    public ProductResponse delete(@PathVariable String id) {
        return service.delete(id);
    }

}
