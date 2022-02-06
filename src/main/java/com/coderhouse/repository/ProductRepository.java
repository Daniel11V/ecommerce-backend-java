package com.coderhouse.repository;

import com.coderhouse.model.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByCode(String code);
    Product deleteByCode(String code);
    List<Product> findByCategory(String category);
}
