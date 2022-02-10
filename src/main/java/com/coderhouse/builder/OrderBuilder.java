package com.coderhouse.builder;

import com.coderhouse.model.document.*;
import com.coderhouse.model.response.OrderResponse;
import com.coderhouse.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class OrderBuilder {

    public static Order cartToOrder(Cart document, String generatedOrderCode, ProductRepository prodRepo) {
        return Order.builder()
                .orderCode(generatedOrderCode)
                .state("GENERATED")
                .email(document.getEmail())
                .creationDate(Calendar.getInstance().getTime())
                .items(CartProductsToOrderProducts(document.getItems(), prodRepo))
                .build();
    }

    public static OrderResponse documentToResponse(Order document) {
        return OrderResponse.builder()
                .orderCode(document.getOrderCode())
                .state(document.getState())
                .email(document.getEmail())
                .creationDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(document.getCreationDate()))
                .items(document.getItems())
                .build();
    }

    public static List<OrderResponse>
    listDocumentToResponse(List<Order> orders) {
        var listResponse = new ArrayList<OrderResponse>();
        orders.forEach(order -> listResponse.add(documentToResponse(order)));
        return listResponse;
    }

    private static OrderProduct
    CartProductToOrderProduct(CartProduct cartProduct, ProductRepository prodRepo) {
        var repoProduct = prodRepo.findByCode(cartProduct.getProductCode());
        if (Objects.isNull(repoProduct)) {
            repoProduct = Product.builder().description("Producto inexistente").price(0D).build();
        }

        return OrderProduct.builder()
                .quantity(cartProduct.getQuantity())
                .productCode(cartProduct.getProductCode())
                .productDescription(repoProduct.getDescription())
                .unitPrice(repoProduct.getPrice())
                .build();
    }

    private static List<OrderProduct>
    CartProductsToOrderProducts(List<CartProduct> cartProducts, ProductRepository prodRepo) {
        var listResponse = new ArrayList<OrderProduct>();
        cartProducts.forEach(cartProduct -> listResponse.add(CartProductToOrderProduct(cartProduct, prodRepo)));
        return listResponse;
    }
}
