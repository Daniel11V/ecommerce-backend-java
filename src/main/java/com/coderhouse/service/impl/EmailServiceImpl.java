package com.coderhouse.service.impl;

import com.coderhouse.config.ApplicationProperties;
import com.coderhouse.model.document.Order;
import com.coderhouse.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final ApplicationProperties properties;

    public void sendOrderConfirmationEmail(Order order) {
        String orderInfo = order.getItems().stream()
                .map(op -> String.format("Product: %s \nDescription: %s \nPrice: %s \nQuantity: %s",
                    op.getProductDescription(),
                    op.getProductDescription(),
                    op.getUnitPrice(),
                    op.getQuantity()))
                .reduce((s1, s2) -> s1 + "\n" + s2 )
                .orElse("");
        orderInfo +=  "\n" +String.format("Status: %s" , order.getState());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getConfigurableEmail());
        message.setTo(order.getEmail());
        message.setSubject("Order confirmation");
        message.setText(String.format("Order confirmation\n%s", orderInfo));
        emailSender.send(message);

        SimpleMailMessage messageToMe = new SimpleMailMessage();
        messageToMe.setFrom(properties.getConfigurableEmail());
        messageToMe.setTo(properties.getConfigurableEmail());
        messageToMe.setSubject("Order "+order.getOrderCode()+" created");
        messageToMe.setText(String.format("Order confirmation\n%s", orderInfo));
        emailSender.send(messageToMe);
    }

    public void sendNewRegisterEmail() {
        SimpleMailMessage messageToMe = new SimpleMailMessage();
        messageToMe.setFrom(properties.getConfigurableEmail());
        messageToMe.setTo(properties.getConfigurableEmail());
        messageToMe.setSubject("New User Register");
        messageToMe.setText("Congratulations!!");
        emailSender.send(messageToMe);
    }
}
