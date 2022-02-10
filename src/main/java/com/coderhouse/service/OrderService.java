package com.coderhouse.service;

import com.coderhouse.model.document.Order;
import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.request.CartRequest;
import com.coderhouse.model.request.NewCartRequest;
import com.coderhouse.model.response.CartResponse;
import com.coderhouse.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(String cartCode) throws ApiRestException;

    List<OrderResponse> showOrders();

    OrderResponse showOrder(String orderCode) throws ApiRestException;

//    CartResponse showCart(String cartCode) throws ApiRestException;
//
//    CartResponse addToCart(String cartCode, CartRequest request) throws ApiRestException;
//
//    CartResponse deleteItem(String cartCode, String productCode) throws ApiRestException;
//
//    CartResponse updateItem(String cartCode, CartRequest updatedItem) throws ApiRestException;

}
