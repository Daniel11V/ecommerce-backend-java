package com.coderhouse.model.response;

import com.coderhouse.model.document.CartProduct;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {

    private String cartCode;
    private String email;
    private String deliverAddress;
    private String creationDate;
    private List<CartProduct> items;
}
