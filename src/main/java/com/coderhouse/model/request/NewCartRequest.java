package com.coderhouse.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCartRequest {

    @NotNull
    private String cartCode;
    @NotNull
    private String email;
    private String deliverAddress;

}
