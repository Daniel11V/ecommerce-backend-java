package com.coderhouse.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String code;
    private String description;
    private String imageUrl;
    private Double price;
    private String category;

}
