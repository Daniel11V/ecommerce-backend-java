package com.coderhouse.service;

import com.coderhouse.model.document.Order;

public interface EmailService {
    void sendOrderConfirmationEmail(Order order);
    void sendNewRegisterEmail();
}
