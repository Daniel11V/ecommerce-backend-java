package com.coderhouse.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    @NotNull
    private String email;
    @NotNull
    private String productCode;
    @NotNull
    private int quantity;

}
