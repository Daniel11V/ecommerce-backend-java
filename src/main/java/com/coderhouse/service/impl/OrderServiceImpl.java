package com.coderhouse.service.impl;

import com.coderhouse.builder.OrderBuilder;
import com.coderhouse.model.document.Cart;
import com.coderhouse.model.document.Order;
import com.coderhouse.model.exceptions.ApiRestException;
import com.coderhouse.model.response.OrderResponse;
import com.coderhouse.repository.CartRepository;
import com.coderhouse.repository.OrderRepository;
import com.coderhouse.repository.ProductRepository;
import com.coderhouse.service.EmailService;
import com.coderhouse.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository prodRepo;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final EmailService emailService;

    @Override
    public OrderResponse createOrder(String cartCode) throws ApiRestException {
        var finishedCart = getCart(cartCode);

        var newOrder = OrderBuilder.cartToOrder(finishedCart, generateOrderCode(), prodRepo);

        var document = orderRepository.save(newOrder);

        cartRepository.delete(finishedCart);

        emailService.sendOrderConfirmationEmail(document);

        return OrderBuilder.documentToResponse(document);
    }

    @Override
    public List<OrderResponse> showOrders() {
        return OrderBuilder.listDocumentToResponse(getOrders());
    }

    @Override
    public OrderResponse showOrder(String orderCode) throws ApiRestException{
        return OrderBuilder.documentToResponse(getOrder(orderCode));
    }
    private Cart getCart(String cartCode) throws ApiRestException {
        if (cartCode.equals("0")) {
            throw new ApiRestException("El identificador del carrito debe ser mayor a 0");
        }

        var currentCart = cartRepository.findByCartCode(cartCode).orElse(null);

        if (Objects.isNull(currentCart)) {
            throw new ApiRestException("El carrito con codigo "+ cartCode +" no existe");
        }

        return currentCart;
    }

    private Order getOrder(String orderCode) throws ApiRestException {
        if (orderCode.equals("0")) {
            throw new ApiRestException("El identificador de la orden debe ser mayor a 0");
        }

        var orders = orderRepository.findByOrderCode(orderCode).orElse(null);

        if (Objects.isNull(orders)) {
            throw new ApiRestException("La orden con codigo "+ orderCode +" no existe");
        }

        return orders;
    }

    private List<Order> getOrders(){
        return orderRepository.findAll();
    }

    private String generateOrderCode() {
        int orderNumber = getOrders().size() + 1;
        String newOrderCode;
        if (orderNumber < 10) {
            newOrderCode = "000" + orderNumber;
        } else if (orderNumber < 100) {
            newOrderCode = "00" + orderNumber;
        } else if (orderNumber < 1000) {
            newOrderCode = "0" + orderNumber;
        } else {
            newOrderCode = "" + orderNumber;
        }

        return "ORDER-" + newOrderCode;
    }

}
