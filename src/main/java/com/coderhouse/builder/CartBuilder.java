package com.coderhouse.builder;

import com.coderhouse.model.document.Cart;
import com.coderhouse.model.request.NewCartRequest;
import com.coderhouse.model.response.CartResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CartBuilder {

    public static Cart requestToDocument(NewCartRequest request) {
        return Cart.builder()
                .cartCode(request.getCartCode())
                .email(request.getEmail())
                .creationDate(Calendar.getInstance().getTime())
                .deliverAddress(request.getDeliverAddress())
                .build();
    }

    public static CartResponse documentToResponse(Cart document) {
        return CartResponse.builder()
                .cartCode(document.getCartCode())
                .deliverAddress(document.getDeliverAddress())
                .email(document.getEmail())
                .creationDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(document.getCreationDate()))
                .items(document.getItems())
                .build();
    }
}
