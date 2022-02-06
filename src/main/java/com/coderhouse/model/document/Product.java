package com.coderhouse.model.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class Product {

    @Id
    private String id;
    private String code;
    private String description;
    private String imageUrl;
    private Double price;
    private String category;
}
