package com.coderhouse.service.impl;

import com.coderhouse.builder.ProductBuilder;
import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import com.coderhouse.repository.ProductRepository;
import com.coderhouse.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        validateRequestCreate(request);
        var document = productRepository.save(ProductBuilder.requestToDocument(request));
        return ProductBuilder.documentToResponse(document);
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        try {
            if (id.equals("0")) {
                throw ApiRestException.builder().message("El identificador del user debe ser mayor a 0").build();
            }
            log.error("Id: {}", id);
            var oldProduct = productRepository.findByCode(id);
            log.error("oldProduct: {}", oldProduct);

            if (Objects.isNull(oldProduct)) {
                throw ApiRestException.builder().message("El producto con id "+ id +" no existe").build();
            }
            var oldId = oldProduct.getId();
            log.error("oldId: {}", oldId);
            var savedDocument = productRepository.save(ProductBuilder.requestToDocumentUpdate(oldId, request));
            return ProductBuilder.documentToResponse(savedDocument);
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductResponse delete(String id) {
        return ProductBuilder.documentToResponse(productRepository.deleteByCode(id));
    }

    @Override
    public List<ProductResponse> searchAll() {
        return ProductBuilder.listDocumentToResponse(productRepository.findAll());
    }

    @Override
    public ProductResponse searchById(String id) {
        try {
            if (id.equals("0")) {
                throw ApiRestException.builder().message("El identificador del user debe ser mayor a 0").build();
            }
            var dataFromDatabase = productRepository.findByCode(id);
            if (Objects.isNull(dataFromDatabase)) {
                throw ApiRestException.builder().message("El producto con id "+ id +" no existe").build();
            }
            return ProductBuilder.documentToResponse(dataFromDatabase);
        } catch (ApiRestException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductResponse> searchByCategory(String category) {
        return ProductBuilder.listDocumentToResponse(productRepository.findByCategory(category));
    }

    private void validateRequestCreate(ProductRequest request)  {
        try {
            var product = productRepository.findByCode(request.getCode());
            if (!Objects.isNull(product)) {
                throw ApiRestException.builder().message("El producto ya existe").build();
            }
         } catch (ApiRestException e) {
            e.printStackTrace();
        }
    }

}
