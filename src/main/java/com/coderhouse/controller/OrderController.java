package com.coderhouse.controller;

import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.response.OrderResponse;
import com.coderhouse.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ecommerce/order")
public class OrderController {

    private final OrderService service;

    @PostMapping("/create/{cartCode}")
    public OrderResponse createOrder(@PathVariable String cartCode) throws ApiRestException {
        return service.createOrder(cartCode);
    }

    @GetMapping("/{orderCode}")
    public OrderResponse showOrder(@PathVariable String orderCode) throws ApiRestException{
        return service.showOrder(orderCode);
    }

    @GetMapping("/")
    public List<OrderResponse> showOrders() {
        return service.showOrders();
    }

}
