package com.coderhouse.builder;

import com.coderhouse.model.document.Product;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

    public static Product requestToDocument(ProductRequest request) {
        return Product.builder()
                .code(request.getCode())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .price(request.getPrice())
                .category(request.getCategory())
                .build();
    }

    public static Product requestToDocumentUpdate(String id, ProductRequest request) {
        return Product.builder()
                .id(id)
                .code(request.getCode())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .price(request.getPrice())
                .category(request.getCategory())
                .build();
    }

    public static ProductResponse documentToResponse(Product document) {
        return ProductResponse.builder()
                .code(document.getCode())
                .description(document.getDescription())
                .imageUrl(document.getImageUrl())
                .price(document.getPrice())
                .category(document.getCategory())
                .build();
    }

    public static List<ProductResponse>
    listDocumentToResponse(List<Product> products) {
        var listResponse = new ArrayList<ProductResponse>();
        products.forEach(product -> listResponse.add(documentToResponse(product)));
        return listResponse;
    }
}
